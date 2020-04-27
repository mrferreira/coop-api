package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.SessaoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class SessaoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoController.class.getName());

    public SessaoRepository sessaoRepository;
    public PautaRepository pautaRepository;
    private ErrorMessage errorMessage;

    public SessaoController(SessaoRepository sessaoRepository,
                           PautaRepository pautaRepository,
                           ErrorMessage errorMessage) {
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.errorMessage = errorMessage;
    }

    private MappingUtil mappingUtil = new MappingUtil();

    @PostMapping("/sessao")
    public ResponseEntity<SessaoVO> create(@RequestBody SessaoVO payload) {
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
                .body(mappingUtil.convertObject(saved, SessaoVO.class));
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

    @GetMapping("/sessao")
    public ResponseEntity<List<SessaoVO>> getAll() {
        return ResponseEntity.ok().body(sessaoRepository.findByActiveTrue().stream()
                .map(m -> mappingUtil.convertObject(m, SessaoVO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/sessao/{id}")
    public ResponseEntity<SessaoVO> get(@PathVariable Long id) {
        Sessao found = sessaoRepository.findById(id).get();
        if(found != null) {
            return ResponseEntity.ok(mappingUtil.convertObject(found, SessaoVO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
