package com.mferreira.coopapi.service;

import com.mferreira.coopapi.configuration.MessagesConfiguration;
import com.mferreira.coopapi.configuration.SpringTestConfiguration;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.vo.SessaoVotacaoEntryVO;
import com.mferreira.coopapi.vo.VotacaoEntryVO;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {SpringTestConfiguration.class})
public class VotacaoServiceTest {

    @InjectMocks
    VotacaoService votacaoService;
    @Mock
    ErrorMessage errorMessage;
    @Mock
    MessagesConfiguration messagesConfiguration;
    @Mock
    RequestService requestService;

    private VotacaoEntryVO voto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(messagesConfiguration.get(Mockito.any())).thenReturn("");

    }

    @Test
    public void testVoteWithInvalidPayload() {

        when(requestService.isAbleToVote(Mockito.any())).thenReturn(false);
        when(errorMessage.sessaoInvalida()).thenReturn(errorMessage);
        when(errorMessage.sessaoInvalida()).thenReturn(errorMessage);
        when(errorMessage.votacaoInvalida()).thenReturn(errorMessage);
        when(errorMessage.cannotVote()).thenReturn(errorMessage);
        when(errorMessage.opcaoInvalida()).thenReturn(errorMessage);
        voto = createVoteEntity();

        assertThrows(BusinessException.class, () -> votacaoService.votar(null, voto), "Saving vote without a session id");

        voto.setSessao(null);
        assertThrows(BusinessException.class, () -> votacaoService.votar(1000L, voto), "Saving vote without a SESSAO object");

        voto.setCpf("");
        assertThrows(BusinessException.class, () -> votacaoService.votar(1000L, voto), "Saving vote with a blank CPF");

        voto.setCpf(null);
        assertThrows(BusinessException.class, () -> votacaoService.votar(1000L, voto), "Saving vote without a CPF");

        voto.setOpcao("");
        assertThrows(BusinessException.class, () -> votacaoService.votar(1000L, voto), "Saving vote without a blank OPCAO");

        voto.setOpcao("TALVEZ...");
        assertThrows(BusinessException.class, () -> votacaoService.votar(1000L, voto), "Saving vote without invalid OPCAO string");

        voto.setOpcao(null);
        assertThrows(BusinessException.class, () -> votacaoService.votar(1000L, voto), "Saving vote without a null field OPCAO");
    }

    private VotacaoEntryVO createVoteEntity() {
        String cpf = "19839091069";
        String opcao = "SIM";
        SessaoVotacaoEntryVO sessao = new SessaoVotacaoEntryVO(1000L);
        return new VotacaoEntryVO(cpf, opcao, sessao);

    }
}