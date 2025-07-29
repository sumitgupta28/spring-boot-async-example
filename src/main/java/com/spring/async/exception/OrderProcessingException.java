package com.spring.async.exception;

import lombok.Getter;

@Getter
public class OrderProcessingException extends  RuntimeException {

    private String message;
    private String oderId;
    public OrderProcessingException(String message,final String orderId) {
        super(message);
        this.message = message;
        this.oderId = orderId;
    }
}
