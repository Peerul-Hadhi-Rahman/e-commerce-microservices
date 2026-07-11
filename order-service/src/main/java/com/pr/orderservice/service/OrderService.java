package com.pr.orderservice.service;

import com.pr.orderservice.client.ProductClient;
import com.pr.orderservice.dto.OrderRequest;
import com.pr.orderservice.dto.ProductResponse;
import com.pr.orderservice.entity.Order;
import com.pr.orderservice.enums.OrderStatus;
import com.pr.orderservice.event.OrderCreatedEvent;
import com.pr.orderservice.exception.OrderNotFoundException;
import com.pr.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final KafkaProducerService kafkaProducerService;

    public Order placeOrder(OrderRequest request) {

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .totalPrice(BigDecimal.ZERO)
                .status(OrderStatus.PENDING)
                .build();

        ProductResponse product = productClient.getProductById(order.getProductId());
        System.out.println("Product = " + product);

       Order savedOrder = orderRepository.save(order);

       kafkaProducerService.send(
               new OrderCreatedEvent(
               savedOrder.getId(),
               savedOrder.getProductId(),
               savedOrder.getQuantity()
               ));
       return savedOrder;
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
