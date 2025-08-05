package com.spring.async.services.subservice;

import com.spring.async.model.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "order-fulfilment-service-client", url = "http://localhost:8080")
public interface OrderFulfilmentService {

    @Async("threadPoolTaskExecutor")
    @PostMapping(value = "/order-fulfilment-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<OrderRequest> orderFulfilmentService(OrderRequest orderRequest);


}
