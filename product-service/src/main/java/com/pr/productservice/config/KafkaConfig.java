package com.pr.productservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic stockReservedTopic() {
        return TopicBuilder.name("stock-reserved").build();
    }

    @Bean
    public NewTopic outOfStockTopic() {
        return TopicBuilder.name("out-of-stock").build();
    }
}
