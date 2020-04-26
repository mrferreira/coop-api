package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.SessaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class SessaoController {
    @Autowired
    public SessaoRepository sessaoRepository;

    private MappingUtil mappingUtil = new MappingUtil();

    @PostMapping("/sessao")
    public ResponseEntity<SessaoVO> create(@RequestBody SessaoVO payload) {
        Sessao vo = mappingUtil.convertObject(payload, Sessao.class);
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
