package com.spring.async.model;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderRequest {

    private String orderId;
    private String itemName;
    private String quantity;
    private String price;

}
