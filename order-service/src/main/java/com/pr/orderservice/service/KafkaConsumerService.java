package com.pr.orderservice.service;

import com.pr.orderservice.entity.Order;
import com.pr.orderservice.enums.OrderStatus;
import com.pr.orderservice.exception.OrderNotFoundException;
import com.pr.orderservice.repository.OrderRepository;

import com.pr.orderservice.event.OutOfStockEvent;
import com.pr.orderservice.event.StockReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "stock-reserved", groupId = "order-group")
    public void handleStockReserved(StockReservedEvent event) {

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
        log.info("Order {} confirmed", order.getId());
    }

    @KafkaListener(topics = "out-of-stock", groupId = "order-group")
    public void handleOutOfStock(OutOfStockEvent event) {

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        log.info("Order {} cancelled", order.getId());
    }

}
