package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.controller.documentation.SessaoControllerDocumentation;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.exception.NullValueException;
import com.mferreira.coopapi.service.SessaoService;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.SessaoEntryVO;
import com.mferreira.coopapi.vo.SessaoOutVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseBody
public class SessaoController implements SessaoControllerDocumentation {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoController.class.getName());

    private final SessaoService sessaoService;
    private final ErrorMessage errorMessage;
    private final MappingUtil mappingUtil;

    public SessaoController(SessaoService sessaoService,
                            ErrorMessage errorMessage) {
        this.sessaoService = sessaoService;
        this.errorMessage = errorMessage;
        this.mappingUtil = new MappingUtil();
    }

    @Override
    @PostMapping("/sessao")
    public ResponseEntity<String> create(@RequestBody SessaoEntryVO payload) {
        return sessaoService.create(payload).map(res -> ResponseEntity.ok().body("CREATED"))
                .orElseThrow(() -> new BusinessException(errorMessage.insertException()));
    }

    @Override
    @GetMapping("/sessao")
    public ResponseEntity<List<SessaoOutVO>> getAll() {
        return sessaoService.getAll().map(ResponseEntity::ok)
                .orElseThrow(NullValueException::new);
    }

    @Override
    @GetMapping("/sessao/{id}")
    public ResponseEntity<SessaoOutVO> get(@PathVariable Long id) {
        return sessaoService.get(id)
                .map(ResponseEntity::ok)
                .orElseThrow(NullValueException::new);
    }
}
