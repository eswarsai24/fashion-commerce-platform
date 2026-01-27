package com.fashion.order.config;


import com.fashion.order.events.OrderCreatedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerConfig {


    @Bean
    public ProducerFactory<String, OrderCreatedEvent> orderCreatedProducerFactory() {
        String bootstrap = System.getenv().getOrDefault("KAFKA_BOOTSTRAP_SERVERS", "localhost:9092");


        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


// optional reliability defaults (fine for learning)
        config.put(ProducerConfig.ACKS_CONFIG, "all");


        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, OrderCreatedEvent> orderCreatedKafkaTemplate(
            ProducerFactory<String, OrderCreatedEvent> pf
    ) {
        return new KafkaTemplate<>(pf);
    }
}