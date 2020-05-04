package com.mferreira.coopapi.service;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.SessaoEntryVO;
import com.mferreira.coopapi.vo.SessaoOutVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class SessaoService {

    private final MappingUtil mappingUtil;
    private final SessaoRepository sessaoRepository;
    public PautaRepository pautaRepository;


    public SessaoService(SessaoRepository sessaoRepository,
                         PautaRepository pautaRepository) {
        this.mappingUtil = new MappingUtil();
        this.pautaRepository = pautaRepository;
        this.sessaoRepository = sessaoRepository;
    }

    Optional<SessaoOutVO> create(SessaoEntryVO payload) {
        Sessao vo = mappingUtil.convertObject(payload, Sessao.class);
        validarPauta(payload.getPauta());
        if(vo.getFim() == null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(vo.getInicio());
            //add default 1min to session duration
            cal.add(Calendar.MINUTE, 1);
            vo.setFim(cal.getTime());
        }
        Sessao saved = sessaoRepository.save(vo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mappingUtil.convertObject(saved, SessaoOutVO.class));
    }

    private void validarPauta(Pauta pauta) {
        if(pauta == null || pauta.getId() == null) {
            throw new BusinessException(errorMessage.pautaInvalida());
        }
        Optional<Pauta> opt = pautaRepository.findById(pauta.getId());
        if(!opt.isPresent()) {
            throw new BusinessException(errorMessage.pautaInvalida());
        }
    }
}
