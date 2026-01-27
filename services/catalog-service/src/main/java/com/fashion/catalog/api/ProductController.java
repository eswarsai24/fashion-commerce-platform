
package com.fashion.catalog.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Map<String, Object>> listProducts() {
        return List.of(
                Map.of("id", "p1", "name", "Denim Jacket", "price", 79.99),
                Map.of("id", "p2", "name", "Sneakers", "price", 59.99),
                Map.of("id", "p3", "name", "Hoodie", "price", 49.99)
        );
    }
}
