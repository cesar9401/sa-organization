package com.cesar31.organization.infrastructure.adapters.input.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorField {

    private String field;
    private String message;
    private Object rejectedValue;
}
