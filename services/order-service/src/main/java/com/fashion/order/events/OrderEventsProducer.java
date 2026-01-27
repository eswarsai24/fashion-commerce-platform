package com.fashion.order.events;


import java.time.Instant;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class OrderEventsProducer {


    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final String topic;


    public OrderEventsProducer(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate,
            @Value("${app.kafka.topics.orderCreated}") String topic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }


    public void publishOrderCreated(String orderId, String userId, String productId, int quantity) {
        var event = new OrderCreatedEvent(
                UUID.randomUUID().toString(),
                orderId,
                userId,
                productId,
                quantity,
                Instant.now()
        );
        kafkaTemplate.send(topic, orderId, event);
    }
}