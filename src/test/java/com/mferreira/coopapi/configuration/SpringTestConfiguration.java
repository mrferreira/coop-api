package com.mferreira.coopapi.configuration;

import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.service.RequestService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringTestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public RequestService requestService() {
        return new RequestService(restTemplate());
    }

    @Bean
    public ErrorMessage errorMessage() {
        return new ErrorMessage();
    }
}
