
package com.fashion.catalog.events;

import java.time.Instant;

public record OrderCreatedEvent(
        String eventId,
        String orderId,
        String userId,
        String productId,
        int quantity,
        Instant createdAt
) {}
