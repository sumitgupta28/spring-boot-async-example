package com.spring.async.completable.service.subservice;

import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompletableInventoryService {


    public CompletableFuture<Boolean> validateInventory(OrderRequest orderRequest)  {
        return CompletableFuture.supplyAsync(() -> {
            CommonUtil.sleepService(Duration.ofMillis(300));
            return Boolean.TRUE;
        }, Executors.newSingleThreadExecutor()); // Use a dedicated executor for this task
    }

}
