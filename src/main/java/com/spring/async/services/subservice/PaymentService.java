package com.spring.async.services.subservice;

import com.spring.async.model.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service-client", url = "http://localhost:8080")
public interface PaymentService {

    @PostMapping(value = "/payment-service", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    OrderRequest paymentService(OrderRequest orderRequest);

}
