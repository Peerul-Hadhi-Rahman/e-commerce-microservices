package com.pr.productservice.service;

import com.pr.productservice.entity.Product;
import com.pr.productservice.event.OrderCreatedEvent;
import com.pr.productservice.event.OutOfStockEvent;
import com.pr.productservice.event.StockReservedEvent;
import com.pr.productservice.exception.ProductNotFoundException;
import com.pr.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    private final ProductRepository productRepository;
    private final KafkaProducerService kafkaProducerService;

    public KafkaConsumerService(ProductRepository productRepository, KafkaProducerService kafkaProducerService) {
        this.productRepository = productRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "order-created", groupId = "product-group")
    public void consume(OrderCreatedEvent event) {

        Product product = productRepository.findById(event.getProductId())
                .orElseThrow(() ->new ProductNotFoundException("Product not found"));

        if(product.getStock() >= event.getQuantity()) {
            product.setStock(product.getStock() - event.getQuantity());

            productRepository.save(product);

            StockReservedEvent reservedEvent = new StockReservedEvent(
                    event.getOrderId(),
                    event.getProductId(),
                    event.getQuantity());

            kafkaProducerService.sendStockReservedEvent(reservedEvent);

            log.info("Stock reserved fpr order {}", event.getOrderId());
        }
        else {
            OutOfStockEvent outOfStockEvent = new OutOfStockEvent(
                    event.getOrderId(),
                    event.getProductId(),
                    event.getQuantity(),
                    "Insufficient stock"
            );
            kafkaProducerService.sendOutOfStockEvent(outOfStockEvent);

            log.info("Out of stock for order {}", event.getOrderId());
        }
    }
}
