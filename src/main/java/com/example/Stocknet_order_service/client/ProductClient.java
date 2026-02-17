package com.example.Stocknet_order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Stocknet-product-service")
public interface ProductClient {

    @GetMapping("/api/productos/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
    
    @PutMapping("/api/productos/{id}/reducir-stock")
    void reducirStock(@PathVariable("id") Long id,
                      @RequestParam("cantidad") int cantidad);

}
