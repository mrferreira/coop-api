package com.mferreira.coopapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingUtil {

    public <T> T convertObject(Object from, Class<T> to) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(from, to);
    }
}
