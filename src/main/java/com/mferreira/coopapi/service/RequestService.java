package com.mferreira.coopapi.service;

import com.mferreira.coopapi.vo.ValidacaoCPFResultVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RequestService {

    RestTemplate restTemplate;
    @Value("${url.validador.cpf}")
    private String validadorCPFUrl;
    @Value("${msg.able.to.vote}")
    private String msgAbleToVote;

    public RequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isAbleToVote(String cpf) {
        if(cpf == null || cpf.isEmpty()) {
            return false;
        }

        String result = restTemplate.getForEntity(validadorCPFUrl+cpf, ValidacaoCPFResultVO.class).getBody().getStatus();
        return result != null && result.equals(msgAbleToVote);
    }
}
