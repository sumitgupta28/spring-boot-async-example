package com.spring.async.controller;

import com.spring.async.completable.service.CompletableOrderProcessService;
import com.spring.async.completable.service.subservice.CompletableFutureOrderFulfilmentService;
import com.spring.async.completable.service.subservice.CompletableNotificationService;
import com.spring.async.exception.OrderProcessingException;
import com.spring.async.model.OrderRequest;
import com.spring.async.model.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("process-order")
@RequiredArgsConstructor
public class CartServiceWithCompletableFuture {

    private final CompletableOrderProcessService completableOrderProcessService;
    private final CompletableNotificationService completableNotificationService;
    private final CompletableFutureOrderFulfilmentService completableFutureOrderFulfilmentService;

    @ExceptionHandler(OrderProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OrderResponse manageOrderProcessingException(OrderProcessingException orderProcessingException) {
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> processOrder(@RequestBody OrderRequest orderRequest) {

        try {
            MDC.put("orderId", orderRequest.getOrderId());
            Optional<OrderRequest> processedOrder = completableOrderProcessService.processOrder(orderRequest);
            if (processedOrder.isPresent() && processedOrder.get().getPaymentStatus()) {
                completableNotificationService.sendNotification(orderRequest);
                completableFutureOrderFulfilmentService.orderFulfilmentService(orderRequest);
                OrderResponse orderResponse = OrderResponse.builder()
                        .orderConfirmationId(processedOrder.get().getPaymentConfirmationId())
                        .orderId(orderRequest.getOrderId())
                        .message("Processed Successfully").build();
                return ResponseEntity.ok().body(orderResponse);
            } else {
                throw new OrderProcessingException("Error while processing order ", orderRequest.getOrderId());
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            MDC.clear();
        }
    }


}
