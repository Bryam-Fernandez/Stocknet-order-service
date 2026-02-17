package com.example.Stocknet_order_service.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.Stocknet_order_service.model.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


public class OrderItemDTO {
	
    private Long id;

    private Long productId;  
    private String name;
    private Double unitPrice;
    private int quantity = 0;



    public OrderItemDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderItemDTO(Long id, Long productId, String name, Double unitPrice, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
    
    

}