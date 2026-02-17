package com.example.Stocknet_order_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Stocknet_order_service.enums.OrderStatus;
import com.example.Stocknet_order_service.model.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

public class OrderDTO {
	private Long id;              
	private Long clientId;    
	private List<OrderItemDTO> items = new ArrayList<>(); 
	private Double subtotal;      
	private Double igv;           
	private Double costoEnvio;      
	private Double total;         
	private OrderStatus status;     
	private LocalDateTime createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public List<OrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
	
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getIgv() {
		return igv;
	}
	public void setIgv(Double igv) {
		this.igv = igv;
	}
	public Double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(Double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public OrderDTO(Long id, Long clientId, List<OrderItemDTO> items, Double subtotal, Double igv, Double costoEnvio,
			Double total, OrderStatus status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.items = items;
		this.subtotal = subtotal;
		this.igv = igv;
		this.costoEnvio = costoEnvio;
		this.total = total;
		this.status = status;
		this.createdAt = createdAt;
	}
	public OrderDTO() {
	}
}
