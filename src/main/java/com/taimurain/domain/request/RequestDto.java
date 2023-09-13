package com.taimurain.domain.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taimurain.exception.TaimurainApplicationException;

public interface RequestDto {

    boolean isRequiredFieldsAvailable();

    default boolean isNonEmpty(String field) {
        return field != null && !field.trim().isEmpty();
    }

    default String toLogJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new TaimurainApplicationException("Json conversion was failed.", e);
        }
    }
}
