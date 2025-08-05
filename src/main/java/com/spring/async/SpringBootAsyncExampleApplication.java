package com.spring.async;

import com.spring.async.services.subservice.InventoryService;
import com.spring.async.services.subservice.NotificationService;
import com.spring.async.services.subservice.OrderFulfilmentService;
import com.spring.async.services.subservice.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {InventoryService.class, NotificationService.class, OrderFulfilmentService.class, PaymentService.class})
public class SpringBootAsyncExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAsyncExampleApplication.class, args);
	}

}
