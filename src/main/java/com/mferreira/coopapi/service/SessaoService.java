package com.mferreira.coopapi.service;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.exception.NullValueException;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.PautaSessaoEntryVO;
import com.mferreira.coopapi.vo.SessaoEntryVO;
import com.mferreira.coopapi.vo.SessaoOutVO;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    private final MappingUtil mappingUtil;
    private final SessaoRepository sessaoRepository;
    private PautaRepository pautaRepository;
    private ErrorMessage errorMessage;


    public SessaoService(SessaoRepository sessaoRepository,
                         PautaRepository pautaRepository,
                         ErrorMessage errorMessage) {
        this.mappingUtil = new MappingUtil();
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
        this.errorMessage = errorMessage;
    }

    public Optional<SessaoOutVO> create(SessaoEntryVO payload) {
        validarPauta(payload.getPauta());
        Sessao vo = mappingUtil.convertObject(payload, Sessao.class);
        prepararSalvar(vo);
        Sessao saved = sessaoRepository.save(vo);
        return Optional.of(mappingUtil.convertObject(saved, SessaoOutVO.class));
    }

    public Optional<List<SessaoOutVO>> getAll() {
        List result = sessaoRepository.findByActiveTrue().stream()
                .map(m -> mappingUtil.convertObject(m, SessaoOutVO.class))
                .collect(Collectors.toList());
        return Optional.of(result);
    }

    public Optional<SessaoOutVO> get(Long id) {
        Optional<SessaoOutVO> result = sessaoRepository.findById(id)
                .map(found -> mappingUtil.convertObject(found, SessaoOutVO.class));
        return Optional.of(result.orElseThrow(NullValueException::new));
    }

    private void prepararSalvar(Sessao vo) {
        if(vo.getFim() == null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(vo.getInicio());
            //add default 1min to session duration
            cal.add(Calendar.MINUTE, 1);
            vo.setFim(cal.getTime());
        }
        vo.setActive(true);
    }

    private void validarPauta(PautaSessaoEntryVO pauta) {
        if(pauta == null || pauta.getId() == null) {
            throw new BusinessException(errorMessage.pautaInvalida());
        }
        Optional<Pauta> opt = pautaRepository.findById(pauta.getId());
        if(!opt.isPresent()) {
            throw new BusinessException(errorMessage.pautaInvalida());
        }
    }
}
