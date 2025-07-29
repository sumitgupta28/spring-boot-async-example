package com.spring.async.exception;

import com.spring.async.model.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OrderResponse manageOrderProcessingException(OrderProcessingException orderProcessingException)
    {
         return OrderResponse.builder()
                 .message(orderProcessingException.getMessage())
                 .orderId(orderProcessingException.getOderId())
                 .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "An error occurred");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
