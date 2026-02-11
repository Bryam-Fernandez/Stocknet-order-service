package com.example.Stocknet_order_service.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

import com.example.Stocknet_order_service.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;              
	private Long clientId;    
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderItem> items = new ArrayList<>(); 
	private BigDecimal subtotal;      
	private BigDecimal igv;           
	private BigDecimal costoEnvio;      
	private BigDecimal total;         
	@Enumerated(EnumType.STRING)
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



	public List<OrderItem> getItems() {
		return items;
	}



	public void setItems(List<OrderItem> items) {
		this.items = items;
	}



	public BigDecimal getSubtotal() {
		return subtotal;
	}



	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}



	public BigDecimal getIgv() {
		return igv;
	}



	public void setIgv(BigDecimal igv) {
		this.igv = igv;
	}



	public BigDecimal getCostoEnvio() {
		return costoEnvio;
	}



	public void setCostoEnvio(BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
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

	

	public Order(Long id, Long clientId, List<OrderItem> items, BigDecimal subtotal, BigDecimal igv,
			BigDecimal costoEnvio, BigDecimal total, OrderStatus status, LocalDateTime createdAt) {
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



	public Order() {
		
	}
}