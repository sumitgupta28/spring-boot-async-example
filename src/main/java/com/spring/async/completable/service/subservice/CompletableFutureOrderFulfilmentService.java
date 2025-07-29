package com.spring.async.completable.service.subservice;

import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CompletableFutureOrderFulfilmentService {

    @Async("threadPoolTaskExecutor")
    public void orderFulfilmentService(OrderRequest orderRequest) {
        CommonUtil.sleepService(Duration.ofSeconds(2));
    }

}
