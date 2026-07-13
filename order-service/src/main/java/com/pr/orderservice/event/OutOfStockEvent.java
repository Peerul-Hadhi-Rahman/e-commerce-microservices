package com.pr.orderservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutOfStockEvent {

    private Long orderId;
    private UUID productId;
    private Integer quantity;
    private  String reason;
}
