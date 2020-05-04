package com.mferreira.coopapi.controller;

import com.mferreira.coopapi.configuration.MessagesConfiguration;
import com.mferreira.coopapi.controller.documentation.VotoControllerDocumentation;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.model.Sessao;
import com.mferreira.coopapi.model.Voto;
import com.mferreira.coopapi.repository.SessaoRepository;
import com.mferreira.coopapi.repository.VotoRepository;
import com.mferreira.coopapi.service.RequestService;
import com.mferreira.coopapi.service.VotacaoService;
import com.mferreira.coopapi.utils.MappingUtil;
import com.mferreira.coopapi.vo.ResultadoVotacaoVO;
import com.mferreira.coopapi.vo.VotacaoEntryVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/sessao")
public class VotacaoController implements VotoControllerDocumentation {

    private final VotacaoService votacaoService;
    private final ErrorMessage errorMessage;
    private final String votacaoSalva;
    private final MessagesConfiguration messagesConfiguration;

    public VotacaoController(VotacaoService votacaoService,
                             ErrorMessage errorMessage,
                             MessagesConfiguration messagesConfiguration) {
        this.votacaoService = votacaoService;
        this.errorMessage = errorMessage;
        this.messagesConfiguration = messagesConfiguration;
        this.votacaoSalva = messagesConfiguration.get("msg.votacao.salva");
    }


    @PostMapping("/{sessionId}/votar")
    public ResponseEntity<String> votar(@PathVariable Long sessionId,
                                  @RequestBody VotacaoEntryVO payload) {
        return votacaoService.votar(sessionId, payload)
                .map(result -> ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS!"))
                .orElseThrow(() -> new BusinessException(errorMessage.insertException()));
    }

    @GetMapping("/{sessionId}/resultado")
    public ResponseEntity<ResultadoVotacaoVO> contabilizarResultado(@PathVariable Long sessionId) {
        return votacaoService.contabilizarVoto(sessionId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new BusinessException(errorMessage.searchException()));
    }

}
