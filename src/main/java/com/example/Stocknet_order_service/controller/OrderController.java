package com.example.Stocknet_order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Stocknet_order_service.client.ProductDTO;
import com.example.Stocknet_order_service.dto.OrderDTO;
import com.example.Stocknet_order_service.model.Order;
import com.example.Stocknet_order_service.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	@PostMapping("/create")
	public Order createOrder(@RequestBody OrderDTO orderDTO) {
		return orderService.crearOrder(orderDTO);
	}
	
	
	@GetMapping
	public List<Order> listarOrders() {
		return orderService.listarOrders();
	}
	
	
	@GetMapping("/{id}")
	public Order obtenerOrder(@PathVariable Long id) {
		return orderService.ObtenerOrder(id);
	}
	
	
	@PutMapping("/{id}")
	public Order actualizarOrder(@PathVariable Long id,
	                             @RequestBody OrderDTO dto) {
		return orderService.ActualizarOrder(id, dto);
	}
	
	
	@DeleteMapping("/{id}")
	public void eliminarOrder(@PathVariable Long id) {
		orderService.EliminarOrder(id);
	}
}
