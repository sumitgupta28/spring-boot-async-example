package com.spring.async.services;

import com.spring.async.common.CommonUtil;
import com.spring.async.model.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class PaymentService {

    public boolean processPayment(OrderRequest orderRequest)  {
        CommonUtil.sleepService(Duration.ofSeconds(1));
        return true;
    }

}
