package com.spring.async.completable.service;

import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Service
public class CompletableNotificationService {

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Boolean> sendNotification(OrderRequest orderRequest)
    {
        CommonUtil.sleepService(Duration.ofSeconds(2));
        return CompletableFuture.completedFuture(true);

    }

}
