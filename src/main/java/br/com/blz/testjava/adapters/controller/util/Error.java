package br.com.blz.testjava.adapters.controller.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
public class Error {
    private String field;
    private String cause;
    private String value;
}
