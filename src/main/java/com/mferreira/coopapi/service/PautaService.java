package com.mferreira.coopapi.service;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.exception.NullValueException;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.PautaEntryVO;
import com.mferreira.coopapi.vo.PautaOutVO;
import com.mferreira.coopapi.vo.SessaoEntryVO;
import com.mferreira.coopapi.vo.SessaoOutVO;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PautaService {

    private final MappingUtil mappingUtil;
    private final SessaoRepository sessaoRepository;
    private PautaRepository pautaRepository;
    private ErrorMessage errorMessage;


    public PautaService(SessaoRepository sessaoRepository,
                        PautaRepository pautaRepository,
                        ErrorMessage errorMessage) {
        this.mappingUtil = new MappingUtil();
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
        this.errorMessage = errorMessage;
    }

    public Optional<PautaOutVO> save(PautaEntryVO payload) {
        Pauta vo = mappingUtil.convertObject(payload, Pauta.class);
        prepararSalvar(vo);
        Pauta saved = pautaRepository.save(vo);
        return Optional.of(mappingUtil.convertObject(saved, PautaOutVO.class));
    }

    public Optional<List<PautaOutVO>> getAll() {
        List<PautaOutVO> result = pautaRepository.findByActiveTrue().stream()
                .map(m -> mappingUtil.convertObject(m, PautaOutVO.class))
                .collect(Collectors.toList());
        return Optional.of(result);
    }

    public Optional<PautaOutVO> get(Long id) {
        return pautaRepository.findById(id)
                .map(found -> mappingUtil.convertObject(found, PautaOutVO.class))
                .map(Optional::of)
                .orElseThrow(NullValueException::new);
    }

    private void prepararSalvar(Pauta vo) {
        validarSalvar(vo);
        vo.setActive(true);
    }

    private void validarSalvar(Pauta vo) {
        if(vo == null || vo.getNome() == null || vo.getNome().isEmpty()) {
            throw new BusinessException(errorMessage.pautaInvalida());
        }
        Pauta pauta = pautaRepository.findByNome(vo.getNome());
        if(pauta != null) {
            throw new BusinessException(errorMessage.pautaJaExisteComNome());
        }
    }
}
