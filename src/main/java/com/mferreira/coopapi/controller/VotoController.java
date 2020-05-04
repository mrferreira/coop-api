package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.controller.documentation.VotoControllerDocumentation;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.model.Voto;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.repository.VotoRepository;
import com.mferreira.coopapi.service.RequestService;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.ResultadoVotacaoVO;
import com.mferreira.coopapi.vo.VotacaoEntryVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/sessao")
public class VotoController implements VotoControllerDocumentation {

    private SessaoRepository sessaoRepository;
    private VotoRepository votoRepository;
    private RequestService requestService;
    private ErrorMessage errorMessage;
    @Value("${msg.votacao.salva}")
    private String votacaoSalva;

    public VotoController(SessaoRepository sessaoRepository,
                          VotoRepository votoRepository,
                          ErrorMessage errorMessage,
                          RequestService requestService) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.errorMessage = errorMessage;
        this.requestService = requestService;
    }


    @PostMapping("/{sessionId}/votar")
    public ResponseEntity<String> votar(@PathVariable Long sessionId,
                                  @RequestBody VotacaoEntryVO payload) {
        validarPayload(payload);
        Sessao sessao = sessaoRepository.getOne(sessionId);
        validarSessao(sessao);
        validarJaVotou(payload.getCpf(),sessao);
        Voto voto = new MappingUtil().convertObject(payload, Voto.class);
        voto.setSessao(sessao);
        votoRepository.save(voto);
        return ResponseEntity.ok(votacaoSalva);
    }

    @GetMapping("/{sessionId}/resultado")
    public ResponseEntity<ResultadoVotacaoVO> contabilizarResultado(@PathVariable Long sessionId) {
        List<Voto> votos = votoRepository.findBySessaoId(sessionId);
        ResultadoVotacaoVO resultado = prepararResultado(votos, sessionId);
        return ResponseEntity.ok(resultado);
    }

    private ResultadoVotacaoVO prepararResultado(List<Voto> votos, Long sessionId) {
        Sessao sessao = sessaoRepository.getOne(sessionId);
        if(votos == null || votos.isEmpty()) {
            if(sessao == null) {
                throw new BusinessException(errorMessage.sessionInexistent());
            }
        }

        long sim = votos.stream()
                .filter(f -> f.getOpcao().toUpperCase().equals("SIM"))
                .count();
        long nao = votos.size() - sim;
        return new ResultadoVotacaoVO(!isSessaoOpen(sessao),sim,nao);

    }

    private boolean isSessaoOpen(Sessao sessao) {
        long now = new Date().getTime();
        return sessao != null && sessao.getInicio().getTime() <= now
                && sessao.getFim().getTime() >= now;
    }

    private void validarJaVotou(String cpf, Sessao sessao) {
        if(votoRepository.findByCpfAndSessaoId(cpf, sessao.getId()) != null) {
            throw new BusinessException(errorMessage.javotou());
        }
    }

    public void validarSessao(Sessao sessao) {
        Long now = Calendar.getInstance().getTimeInMillis();
        if(sessao == null) {
            throw new BusinessException(errorMessage.sessionInexistent());
        }
        if(sessao.getInicio().getTime() > now ||
                sessao.getFim().getTime() < now) {
            throw new BusinessException(errorMessage.sessionClosed());
        }
    }

    public void validarPayload(VotacaoEntryVO payload) {
        String compare = payload.getOpcao().toUpperCase();
        if(payload == null ||
                !Arrays.asList("SIM", "NÃO").stream()
                        .anyMatch(f -> f.equals(compare))) {
            throw new BusinessException(errorMessage.votacaoInvalida());
        }
        if(!canVote(payload.getCpf())) {
            throw new BusinessException(errorMessage.cannotVote());
        }
    }

    private boolean canVote(String cpf) {
        return requestService.isAbleToVote(cpf);
    }

}
