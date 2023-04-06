package com.crowdfunding.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class ConvertJSON {

    private static volatile ObjectMapper ourObjectMapper;

    private static final Object mutex = new Object();

    static {
        try {
            ObjectMapper instance = ourObjectMapper;

            if (instance == null) {
                synchronized (mutex) {
                    instance = ourObjectMapper;

                    if (instance == null) {
                        ourObjectMapper = instance = new ObjectMapper().registerModule(new JavaTimeModule());
                        instance.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                    }
                }
            }
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }

    }

    // access singleton
    public static final ObjectMapper getObjectMapper() {
        return ourObjectMapper;
    }

    public static final String writeBro(Object value) throws JsonProcessingException {

        return ourObjectMapper.writeValueAsString(value);

    }

    public static final <T> T readBro(String value, Class<T> valueType) throws JsonProcessingException {
        return ourObjectMapper.readValue(value, valueType);
    }



}
