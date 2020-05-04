package com.mferreira.coopapi.service;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.exception.NullValueException;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.model.Voto;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.repository.VotoRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VotacaoService {

    private final RequestService requestService;
    private final MappingUtil mappingUtil;
    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    private final ErrorMessage errorMessage;


    public VotacaoService(
            RequestService requestService,
            SessaoRepository sessaoRepository,
            PautaRepository pautaRepository,
            VotoRepository votoRepository,
            ErrorMessage errorMessage) {
        this.requestService = requestService;
        this.mappingUtil = new MappingUtil();
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
        this.votoRepository = votoRepository;
        this.errorMessage = errorMessage;
    }

    public Optional<VotacaoOutVO> votar(Long sessionId, VotacaoEntryVO payload) {
        validarPayload(sessionId, payload);
        Sessao sessao = sessaoRepository.getOne(sessionId);
        validarSessao(sessao);
        validarJaVotou(payload.getCpf(),sessao);
        Voto voto = new MappingUtil().convertObject(payload, Voto.class);
        voto.setSessao(sessao);
        Voto saved = votoRepository.save(voto);
        return Optional.of(mappingUtil.convertObject(saved, VotacaoOutVO.class));
    }

    public Optional<ResultadoVotacaoVO> contabilizarVoto(Long sessionId) {
        List<Voto> votos = votoRepository.findBySessaoId(sessionId);
        ResultadoVotacaoVO resultado = prepararResultado(votos, sessionId);
        return Optional.of(resultado);
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

    public void validarPayload(Long sessionId, VotacaoEntryVO payload) {
        if(sessionId == null) {
            throw new BusinessException(errorMessage.sessaoInvalida());
        }

        if(payload.getOpcao() == null || payload.getOpcao().trim().isEmpty()) {
            throw new BusinessException(errorMessage.opcaoInvalida());
        }
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
