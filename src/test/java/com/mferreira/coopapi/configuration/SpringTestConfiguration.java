package com.mferreira.coopapi.configuration;

import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.service.RequestService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class SpringTestConfiguration {

    @Bean("restTemplateTest")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean("requestServiceTest")
    public RequestService requestService() {
        return new RequestService(restTemplate());
    }

    @Bean("errorMessageTest")
    public ErrorMessage errorMessage() {
        return new ErrorMessage();
    }
}
