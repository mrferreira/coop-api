package com.mferreira.coopapi.service;

import com.mferreira.coopapi.configuration.SpringTestConfiguration;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.vo.ValidacaoCPFResultVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringTestConfiguration.class})
class RequestServiceTest {

    @Autowired
    private RequestService requestService;

    @Test
    public void shouldThrowNotFoundStatusExceptionOnInvalidCPF() {
        assertThrows(BusinessException.class, () -> requestService.getResponse("https://user-info.herokuapp.com/users/", ""));
        assertThrows(BusinessException.class, () -> requestService.getResponse("https://user-info.herokuapp.com/users/", "11111111111"));
    }

    @Test
    public void shouldReturnResponseType() {
        ValidacaoCPFResultVO vo = requestService.getResponse("https://user-info.herokuapp.com/users/", "19839091069");
        assertTrue(Arrays.asList("UNABLE_TO_VOTE", "ABLE_TO_VOTE").contains(vo.getStatus()));
    }
}