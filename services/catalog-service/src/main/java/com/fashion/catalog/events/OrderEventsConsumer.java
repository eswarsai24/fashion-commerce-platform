
package com.fashion.catalog.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsConsumer {

    @KafkaListener(topics = "${app.kafka.topics.orderCreated}", groupId = "catalog-service")
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("[catalog-service] Received OrderCreated: " + event);
    }
}
