package com.spring.async.completable.service;

import com.spring.async.exception.OrderProcessingException;
import com.spring.async.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompletableOrderProcessService {

    final CompletableInventoryService inventoryService;
    final CompletablePaymentService paymentService;


    public Optional<String> processOrder(OrderRequest orderRequest) {

        String orderConfirmationNumber = null;
        CompletableFuture<Boolean> inventoryStatus = inventoryService.validateInventory(orderRequest);
          CompletableFuture<String> paymentStatus= paymentService.processPayment(orderRequest);
          return Optional.empty();
    }



}
