package com.spring.async.controller;


import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ExternalServices {

    @PostMapping(value = "inventory-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderRequest> validateInventoryExternalServices(@RequestBody OrderRequest orderRequest) {
        orderRequest.setInventoryConfirmation(Boolean.TRUE);
        CommonUtil.sleepService(Duration.ofMillis(300));
        return ResponseEntity.ok(orderRequest);
    }

    @PostMapping(value = "payment-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderRequest> paymentProcessingExternalServices(@RequestBody OrderRequest orderRequest) {
        orderRequest.setPaymentStatus(Boolean.TRUE);
        orderRequest.setPaymentConfirmationId(UUID.randomUUID().toString());
        CommonUtil.sleepService(Duration.ofMillis(300));
        return ResponseEntity.ok(orderRequest);
    }

    @PostMapping(value = "notification-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderRequest> notificationServiceExternalServices(@RequestBody OrderRequest orderRequest) {
        CommonUtil.sleepService(Duration.ofMillis(300));
        return ResponseEntity.ok(orderRequest);
    }


    @PostMapping(value = "order-fulfilment-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderRequest> orderFulfilmentServiceExternalServices(@RequestBody OrderRequest orderRequest) {
        CommonUtil.sleepService(Duration.ofMillis(300));
        return ResponseEntity.ok(orderRequest);
    }


}
