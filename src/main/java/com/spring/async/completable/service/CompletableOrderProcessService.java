package com.spring.async.completable.service;

import com.spring.async.completable.service.subservice.CompletableInventoryService;
import com.spring.async.completable.service.subservice.CompletablePaymentService;
import com.spring.async.exception.OrderProcessingException;
import com.spring.async.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompletableOrderProcessService {

    final CompletableInventoryService inventoryService;
    final CompletablePaymentService paymentService;

    public Optional<OrderRequest> processOrder(OrderRequest orderRequest) throws ExecutionException, InterruptedException {

        String orderConfirmationNumber = null;
        CompletableFuture<Boolean> inventoryStatus = inventoryService.validateInventory(orderRequest);
        Boolean inventoryStatusResult = inventoryStatus.get();
        if (inventoryStatusResult) {
            CompletableFuture<OrderRequest> paymentStatus = paymentService.processPayment(orderRequest);
            try {
                OrderRequest processedOrderRequest = paymentStatus.get();
                if (processedOrderRequest.getPaymentStatus()) {
                    return Optional.of(processedOrderRequest);
                } else {
                    throw new OrderProcessingException("Payment Unsuccessful !!", orderRequest.getOrderId());
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new OrderProcessingException("Item not available !!", orderRequest.getOrderId());
        }
    }



}
