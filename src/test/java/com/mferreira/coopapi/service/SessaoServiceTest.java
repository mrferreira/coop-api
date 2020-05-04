package com.mferreira.coopapi.service;

import com.mferreira.coopapi.configuration.MessagesConfiguration;
import com.mferreira.coopapi.exception.BusinessException;
import com.mferreira.coopapi.exception.ErrorMessage;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.repository.PautaRepository;
import com.mferreira.coopapi.vo.PautaSessaoEntryVO;
import com.mferreira.coopapi.vo.SessaoEntryVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class SessaoServiceTest {

    @InjectMocks
    SessaoService sessaoService;
    @Mock
    ErrorMessage errorMessage;
    @Mock
    MessagesConfiguration messagesConfiguration;
    @Mock
    PautaRepository pautaRepository;

    private SessaoEntryVO sessao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(messagesConfiguration.get(Mockito.any())).thenReturn("");
        when(errorMessage.sessaoInvalida()).thenReturn(errorMessage);
        when(errorMessage.pautaInvalida()).thenReturn(errorMessage);

        sessao = createSessionEntity();
    }

    @Test
    public void testSaveSessionWithInvalidPayload() {
        sessao.getPauta().setId(null);
        assertThrows(BusinessException.class, () -> sessaoService.create(sessao), "Insert SESSAO without pauta.id");

        sessao.setPauta(null);
        assertThrows(BusinessException.class, () -> sessaoService.create(sessao), "Insert SESSAO without PAUTA object");
    }

    @Test
    public void testSaveWithNonExistingPauta() {
        when(pautaRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> sessaoService.create(sessao), "Insert SESSAO with existing PAUTA in DB.");
    }

    private SessaoEntryVO createSessionEntity() {
        PautaSessaoEntryVO pauta = new PautaSessaoEntryVO(1L);
        return new SessaoEntryVO(pauta, new Date(), new Date());
    }
}