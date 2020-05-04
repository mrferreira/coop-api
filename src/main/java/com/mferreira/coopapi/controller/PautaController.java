package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.controller.documentation.PautaControllerDocumentation;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.service.PautaService;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.PautaEntryVO;
import com.mferreira.coopapi.vo.PautaOutVO;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class PautaController implements PautaControllerDocumentation {

    private final PautaService pautaService;
    private final MappingUtil mappingUtil;
    private ErrorMessage errorMessage;

    public PautaController(PautaService service,
                           ErrorMessage errorMessage) {
        this.pautaService = service;
        this.errorMessage = errorMessage;
        this.mappingUtil = new MappingUtil();
    }

    @PostMapping("/pauta")
    public ResponseEntity<String> cadastrar(@RequestBody PautaEntryVO payload) {
        return pautaService.save(payload)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED).body("CREATED"))
                .orElseThrow(() -> new BusinessException(errorMessage.insertException()));
    }

    @PostMapping(value = "/pauta",
        produces = "application/vnd.mferreira.coopapi.v1+json")
    public ResponseEntity<PautaOutVO> cadastrarV2(@RequestBody PautaEntryVO payload) {
        return pautaService.save(payload)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res))
                .orElseThrow(() -> new BusinessException(errorMessage.insertException()));
    }

    @GetMapping("/pauta")
    public ResponseEntity<List<PautaOutVO>> listActive() {
        return pautaService.getAll()
                .map(list ->
                        ResponseEntity.ok().body(
                            list.stream()
                                    .map(m -> mappingUtil.convertObject(m, PautaOutVO.class))
                                    .collect(Collectors.toList())
                        )
                )
                .orElseThrow(() -> new BusinessException(errorMessage.searchException()));
    }

    @GetMapping(value = "/pauta/{id}",
            produces = "application/vnd.mferreira.coopapi.v1+json")
    public ResponseEntity<PautaOutVO> get(@PathVariable Long id) {
        return pautaService.get(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException(errorMessage.searchException()));
    }

    @GetMapping(value = "/pauta/{id}",
            produces = "application/vnd.mferreira.coopapi.v2+json")
    public ResponseEntity<String> getV2(@PathVariable Long id) {
        return pautaService.get(id)
            .map(r -> ResponseEntity.ok().body(r.getNome()))
            .orElseThrow(() -> new BusinessException(errorMessage.searchException()));
    }
}
