package com.spring.async.completable.service.subservice;

import com.spring.async.common.CommonUtil;
import com.spring.async.exception.OrderProcessingException;
import com.spring.async.model.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class CompletablePaymentService {

    public CompletableFuture<String> processPayment(OrderRequest orderRequest)  {
        boolean status =true;
        CommonUtil.sleepService(Duration.ofSeconds(1));
        if(status)
        {
            final String orderConfirmationNumber = UUID.randomUUID().toString();
            return CompletableFuture.completedFuture(orderConfirmationNumber);
        }else
        {
            throw new OrderProcessingException("Payment Unsuccessful !!",orderRequest.getOrderId());
        }
    }

}
