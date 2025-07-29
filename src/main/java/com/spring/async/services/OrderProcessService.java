package com.spring.async.services;

import com.spring.async.exception.OrderProcessingException;
import com.spring.async.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProcessService {

    final InventoryService inventoryService;
    final PaymentService paymentService;


    public Optional<String> processOrder(OrderRequest orderRequest) {
        String orderConfirmationNumber = null;
       boolean inventoryStatus = inventoryService.validateInventory(orderRequest);
       if(inventoryStatus)
       {
          boolean paymentStatus= paymentService.processPayment(orderRequest);
          if (paymentStatus)
          {
              orderConfirmationNumber = UUID.randomUUID().toString();
              return Optional.of(orderConfirmationNumber);
          } else
          {
             throw new OrderProcessingException("Payment Unsuccessful !!",orderRequest.getOrderId());
          }
       } else {
           throw new OrderProcessingException("Item not available !!",orderRequest.getOrderId());
       }
    }



}
