package com.mferreira.coopapi.utils;

import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.vo.PautaVO;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MappingUtilTest {

    @Test
    public void shouldConvertToType() {
        MappingUtil mappingUtil = new MappingUtil();
        Pauta pauta = new Pauta("Pauta Teste XPTO");
        pauta.setCreatedAt(new Date());
        pauta.setUpdatedAt(new Date());
        pauta.setId(1000L);
        pauta.setActive(true);

        PautaVO vo = mappingUtil.convertObject(pauta, PautaVO.class);

        assertEquals(pauta.getId(), vo.getId());
        assertEquals(pauta.getNome(), vo.getNome());
        assertEquals(pauta.getCreatedAt(), vo.getCreatedAt());
        assertEquals(pauta.getUpdatedAt(), vo.getUpdatedAt());
        assertEquals(pauta.getActive(), vo.getActive());
    }
}