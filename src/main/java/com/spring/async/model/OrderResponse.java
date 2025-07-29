package com.spring.async.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
   private String orderConfirmationId;
   private String message;
   private String orderId;
}
