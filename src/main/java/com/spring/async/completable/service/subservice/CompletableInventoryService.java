package com.spring.async.completable.service.subservice;

import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompletableInventoryService {


    public CompletableFuture<Boolean> validateInventory(OrderRequest orderRequest)  {
        CommonUtil.sleepService(Duration.ofSeconds(1));
        return CompletableFuture.completedFuture(true);
    }

}
