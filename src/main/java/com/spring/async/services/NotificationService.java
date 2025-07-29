package com.spring.async.services;

import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class NotificationService {

    @Async("threadPoolTaskExecutor")
    public void sendNotification(OrderRequest orderRequest)
    {
        CommonUtil.sleepService(Duration.ofSeconds(2));
    }

}
