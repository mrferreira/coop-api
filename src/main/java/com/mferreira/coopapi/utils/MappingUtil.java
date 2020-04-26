package com.mferreira.coopapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mferreira.coopapi.model.Pauta;
import com.mferreira.coopapi.vo.PautaVO;

public class MappingUtil {

    public <T> T convertObject(Object from, Class<T> to) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(from, to);
    }
}
