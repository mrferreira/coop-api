package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.model.Voto;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.repository.VotoRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.VotacaoEntryVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;

@RestController
@ResponseBody
@RequestMapping("/sessao")
public class VotoController {

    private SessaoRepository sessaoRepository;
    private VotoRepository votoRepository;
    private ErrorMessage errorMessage;
    @Value("${msg.votacao.salva}")
    private String votacaoSalva;

    public VotoController(SessaoRepository sessaoRepository,
                          VotoRepository votoRepository,
                          ErrorMessage errorMessage) {
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.errorMessage = errorMessage;
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

    private void validarJaVotou(String cpf, Sessao sessao) {
        if(votoRepository.findByCpfAndSessaoId(cpf, sessao.getId()) != null) {
            throw new BusinessException(errorMessage.javotou());
        }
    }

    public void validarSessao(Sessao sessao) {
        Long now = Calendar.getInstance().getTimeInMillis();
        if(sessao == null ||
                sessao.getInicio().getTime() > now ||
                sessao.getFim().getTime() < now) {
            throw new BusinessException(errorMessage.sessionClosed());
        }
    }

    public void validarPayload(VotacaoEntryVO payload) {
        String compare = payload.getOpcao().toUpperCase();
        if(payload == null ||
        payload.getCpf() == null || payload.getCpf().isEmpty() ||
                !Arrays.asList("SIM", "NAO").stream()
                        .anyMatch(f -> f.equals(compare))) {
            throw new BusinessException(errorMessage.votacaoInvalida());
        }
    }

}
