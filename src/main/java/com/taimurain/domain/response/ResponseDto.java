package com.taimurain.domain.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taimurain.exception.TaimurainApplicationException;

public interface ResponseDto {
    default String toLogJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new TaimurainApplicationException("Json conversion was failed.", e);
        }
    }
}
