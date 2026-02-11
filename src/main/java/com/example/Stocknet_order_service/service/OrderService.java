package com.example.Stocknet_order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Stocknet_order_service.dto.OrderDTO;
import com.example.Stocknet_order_service.enums.OrderStatus;
import com.example.Stocknet_order_service.model.Order;
import com.example.Stocknet_order_service.repository.OrderRepository;

public class OrderService {
	
	@Autowired
	private OrderRepository OrderR;
		
	public Order CrearOrder(OrderDTO dto) {
		Order order = new Order();
		order.setId(dto.getId());
		order.setClientId(dto.getClientId());
		order.setItems(dto.getItems());
		order.setSubtotal(dto.getSubtotal());
		order.setIgv(dto.getIgv());
		order.setCostoEnvio(dto.getCostoEnvio());
		order.setTotal(dto.getTotal());
		order.setStatus(OrderStatus.CREATED);
		order.setCreatedAt(dto.getCreatedAt());
	
		return OrderR.save(order);
}
	public Order ObtenerOrder(Long id) {
		return OrderR.findById(id).orElse(null);
	}
	
	public Order ActualizarOrder(Long id, OrderDTO dto) {
		Order order = OrderR.findById(id).orElse(null);
		if (order != null) {
			order.setClientId(dto.getClientId());
			order.setItems(dto.getItems());
			order.setSubtotal(dto.getSubtotal());
			order.setIgv(dto.getIgv());
			order.setCostoEnvio(dto.getCostoEnvio());
			order.setTotal(dto.getTotal());
			order.setStatus(dto.getStatus());
			return OrderR.save(order);
		}
		return null;
	}
	
	public List<Order> listarOrders(){
		return OrderR.findAll();
	}
	
}
