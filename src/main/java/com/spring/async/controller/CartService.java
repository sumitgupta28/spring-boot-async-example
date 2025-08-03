package com.spring.async.controller;

import com.spring.async.exception.OrderProcessingException;
import com.spring.async.model.OrderRequest;
import com.spring.async.model.OrderResponse;
import com.spring.async.services.OrderProcessService;
import com.spring.async.services.subservice.NotificationService;
import com.spring.async.services.subservice.OrderFulfilmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class CartService {

    private final OrderProcessService orderProcessService;
    private final NotificationService notificationService;
    private final OrderFulfilmentService orderFulfilmentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> processOrder(@RequestBody OrderRequest orderRequest)  {

        try{
            MDC.put("orderId",orderRequest.getOrderId());
            Optional<String> orderConfirmationId = orderProcessService.processOrder(orderRequest);
            if(orderConfirmationId.isPresent()){
                notificationService.sendNotification(orderRequest);
                orderFulfilmentService.orderFulfilmentService(orderRequest);
                OrderResponse orderResponse= OrderResponse.builder()
                        .orderConfirmationId(orderConfirmationId.get())
                        .orderId(orderRequest.getOrderId())
                        .message("Processed Successfully").build();
                return ResponseEntity.ok().body(orderResponse);
            }
            else
            {
                throw new OrderProcessingException("Error while processing order ",orderRequest.getOrderId());
            }
        }finally {
            MDC.clear();
        }
    }


}
