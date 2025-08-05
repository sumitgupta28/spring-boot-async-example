package com.spring.async.completable.service.subservice;

import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class CompletablePaymentService {

    public CompletableFuture<OrderRequest> processPayment(OrderRequest orderRequest) {


        return CompletableFuture.supplyAsync(() -> {
            CommonUtil.sleepService(Duration.ofMillis(300));
            orderRequest.setPaymentConfirmationId(UUID.randomUUID().toString());
            orderRequest.setPaymentStatus(Boolean.TRUE);
            return orderRequest;
        }, Executors.newSingleThreadExecutor()); // Use a dedicated executor for this task

    }

}
