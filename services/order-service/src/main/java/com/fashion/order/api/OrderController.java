package com.fashion.order.api;


import java.util.UUID;


import com.fashion.order.api.dto.CreateOrderRequest;
import com.fashion.order.api.dto.OrderResponse;
import com.fashion.order.events.OrderEventsProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderEventsProducer producer;


    public OrderController(OrderEventsProducer producer) {
        this.producer = producer;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody @Valid CreateOrderRequest req) {
        String orderId = "ord-" + UUID.randomUUID();
        producer.publishOrderCreated(orderId, req.userId(), req.productId(), req.quantity());
        return new OrderResponse(orderId, "CREATED");
    }
}