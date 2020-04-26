package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.PautaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class PautaController {

    @Autowired
    PautaRepository pautaRepository;

    MappingUtil mappingUtil = new MappingUtil();

    @PostMapping("/pauta")
    public ResponseEntity<PautaVO> cadastrar(@RequestBody PautaVO payload) {
        Pauta saved = pautaRepository.save(mappingUtil.convertObject(payload, Pauta.class));
        return ResponseEntity.ok().body(mappingUtil.convertObject(saved, PautaVO.class));
    }

    @GetMapping("/pauta")
    public ResponseEntity<List<PautaVO>> listActive() {
        List<Pauta> active = pautaRepository.findByActiveTrue();
        return ResponseEntity.ok().body(active.stream()
                .map(m -> mappingUtil.convertObject(m, PautaVO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/pauta/{id}")
    public ResponseEntity<PautaVO> get(@PathVariable Long id) {
        Pauta found = pautaRepository.findById(id).get();
        if(found != null) {
            return ResponseEntity.ok().body(mappingUtil.convertObject(found, PautaVO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
