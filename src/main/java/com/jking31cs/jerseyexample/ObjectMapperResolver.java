package com.jking31cs.jerseyexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by jking31cs on 10/13/15.
 */
@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
    private final ObjectMapper objectMapper;

    public ObjectMapperResolver() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new GuavaModule());
    }


    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}
