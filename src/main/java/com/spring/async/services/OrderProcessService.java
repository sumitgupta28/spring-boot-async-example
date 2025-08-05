package com.spring.async.services;

import com.spring.async.exception.OrderProcessingException;
import com.spring.async.model.OrderRequest;
import com.spring.async.services.subservice.InventoryService;
import com.spring.async.services.subservice.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderProcessService {

    final InventoryService inventoryService;
    final PaymentService paymentService;


    public Optional<OrderRequest> processOrder(OrderRequest orderRequest) {
        String orderConfirmationNumber = null;
        orderRequest = inventoryService.validateInventory(orderRequest);
        if (orderRequest.getInventoryConfirmation())
       {
           orderRequest = paymentService.paymentService(orderRequest);
           if (orderRequest.getPaymentStatus())
          {
              return Optional.of(orderRequest);
          } else
          {
             throw new OrderProcessingException("Payment Unsuccessful !!",orderRequest.getOrderId());
          }
       } else {
           throw new OrderProcessingException("Item not available !!",orderRequest.getOrderId());
       }
    }



}
