package com.spring.async.services.subservice;

import com.spring.async.model.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.CompletableFuture;


@FeignClient(name = "notification-service-client", url = "http://localhost:8080")
public interface NotificationService {

    @Async("threadPoolTaskExecutor")
    @PostMapping(value = "/notification-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<OrderRequest> notificationService(OrderRequest orderRequest);


}
