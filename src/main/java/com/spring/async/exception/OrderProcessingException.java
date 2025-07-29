package com.spring.async.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OrderProcessingException extends  RuntimeException {
    private final String message;
    private final String oderId;
}
