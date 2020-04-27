package com.mferreira.coopapi.service;

import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.vo.ValidacaoCPFResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RequestService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RequestService.class.getName());

    RestTemplate restTemplate;
    @Value("${url.validador.cpf}")
    private String validadorCPFUrl;
    @Value("${msg.able.to.vote}")
    private String msgAbleToVote;
    @Autowired
    private ErrorMessage errorMessage;

    public RequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isAbleToVote(String cpf) {
        if(cpf == null || cpf.isEmpty()) {
            return false;
        }

        String result = getResponse(validadorCPFUrl, cpf).getStatus();
        return result != null && msgAbleToVote.equals(result);
    }

    public ValidacaoCPFResultVO getResponse(String url, String cpf) {
        if(cpf == null) {
            return new ValidacaoCPFResultVO();
        }

        LOGGER.info("Making GET request to " + url);
        try {
            return restTemplate.getForEntity(url + cpf, ValidacaoCPFResultVO.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new BusinessException(errorMessage.cpfInvalido());
        }
    }
}
