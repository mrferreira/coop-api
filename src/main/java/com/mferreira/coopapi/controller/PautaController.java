package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.PautaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class PautaController {

    @Autowired
    PautaRepository pautaRepository;

    MappingUtil mappingUtil = new MappingUtil();

    @PostMapping("/pauta")
    public ResponseEntity cadastrar(@RequestBody PautaVO payload) {
        Pauta saved = pautaRepository.save(mappingUtil.convertObject(payload, Pauta.class));
        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado!");
    }

    @PostMapping(value = "/pauta",
        produces = "application/vnd.mferreira.coopapi.v1+json")
    public ResponseEntity<PautaVO> cadastrarV2(@RequestBody PautaVO payload) {
        Pauta saved = pautaRepository.save(mappingUtil.convertObject(payload, Pauta.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mappingUtil.convertObject(saved, PautaVO.class));
    }

    @GetMapping("/pauta")
    public ResponseEntity<List<PautaVO>> listActive() {
        List<Pauta> active = pautaRepository.findByActiveTrue();
        return ResponseEntity.ok().body(active.stream()
                .map(m -> mappingUtil.convertObject(m, PautaVO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/pauta/{id}",
            produces = "application/vnd.mferreira.coopapi.v1+json")
    public ResponseEntity<PautaVO> get(@PathVariable Long id) {
        Optional<Pauta> found = pautaRepository.findById(id);
        if(found.isPresent()) {
            return ResponseEntity.ok().body(mappingUtil.convertObject(found.get(), PautaVO.class));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/pauta/{id}",
            produces = "application/vnd.mferreira.coopapi.v2+json")
    public ResponseEntity getV2(@PathVariable Long id) {
        Optional<Pauta> found = pautaRepository.findById(id);
        if(found.isPresent()) {
            return ResponseEntity.ok().body(found.get().getNome());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
