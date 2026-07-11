package com.pr.productservice.service;

import com.pr.productservice.event.OutOfStockEvent;
import com.pr.productservice.event.StockReservedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockReservedEvent(StockReservedEvent event) {
        kafkaTemplate.send("stock-reserved", event);
    }

    public void sendOutOfStockEvent(OutOfStockEvent event) {
        kafkaTemplate.send("out-of-stock", event);
    }
}
