package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.controller.documentation.SessaoControllerDocumentation;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.service.SessaoService;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.SessaoEntryVO;
import com.mferreira.coopapi.vo.SessaoOutVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class SessaoController implements SessaoControllerDocumentation {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoController.class.getName());

    private SessaoService sessaoService;
    private ErrorMessage errorMessage;

    public SessaoController(SessaoService sessaoService,
                            SessaoRepository sessaoRepository,
                           PautaRepository pautaRepository,
                           ErrorMessage errorMessage) {
        this.sessaoService = sessaoService;
        this.sessaoRepository = sessaoRepository;
        this.pautaRepository = pautaRepository;
        this.errorMessage = errorMessage;
    }

    private MappingUtil mappingUtil = new MappingUtil();

    @Override
    @PostMapping("/sessao")
    public ResponseEntity create(@RequestBody SessaoEntryVO payload) {

    }



    @Override
    @GetMapping("/sessao")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(sessaoRepository.findByActiveTrue().stream()
                .map(m -> mappingUtil.convertObject(m, SessaoOutVO.class))
                .collect(Collectors.toList()));
    }

    @Override
    @GetMapping("/sessao/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        Sessao found = sessaoRepository.findById(id).get();
        if(found != null) {
            return ResponseEntity.ok(mappingUtil.convertObject(found, SessaoOutVO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
