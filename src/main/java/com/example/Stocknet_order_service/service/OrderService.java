package com.example.Stocknet_order_service.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Stocknet_order_service.client.ProductClient;
import com.example.Stocknet_order_service.client.ProductDTO;
import com.example.Stocknet_order_service.dto.OrderDTO;
import com.example.Stocknet_order_service.dto.OrderItemDTO;
import com.example.Stocknet_order_service.enums.OrderStatus;
import com.example.Stocknet_order_service.model.Order;
import com.example.Stocknet_order_service.model.OrderItem;
import com.example.Stocknet_order_service.repository.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final ProductClient productClient;

	public OrderService(OrderRepository orderRepository,
	                    ProductClient productClient) {
	    this.orderRepository = orderRepository;
	    this.productClient = productClient;
	}

	public List<Order> listarOrders(){
		return orderRepository.findAll();
	}
	
	public Order ObtenerOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}
		
	public Order crearOrder(OrderDTO dto) {

	    Order order = new Order();
	    order.setClientId(dto.getClientId());
	    order.setStatus(OrderStatus.CREATED);

	    List<OrderItem> orderItems = new ArrayList<>();
	    BigDecimal subtotal = BigDecimal.ZERO;

	    for (OrderItemDTO itemDTO : dto.getItems()) {

	        ProductDTO product = productClient.getProductById(itemDTO.getProductId());

	        if (product == null || !product.getEstado()) {
	            throw new RuntimeException("Producto no disponible");
	        }

	        if (product.getStock() < itemDTO.getQuantity()) {
	            throw new RuntimeException(
	                "Stock insuficiente para: " + product.getNombre()
	            );
	        }
	        
	        productClient.reducirStock(product.getId(), itemDTO.getQuantity());

	        // 🔥 Crear entidad OrderItem
	        OrderItem orderItem = new OrderItem();
	        orderItem.setProductId(product.getId());
	        orderItem.setName(product.getNombre());
	        orderItem.setUnitPrice(product.getPrecio());
	        orderItem.setQuantity(itemDTO.getQuantity());
	        orderItem.setOrder(order);

	        orderItems.add(orderItem);

	        BigDecimal precioItem =
	                product.getPrecio()
	                       .multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

	        subtotal = subtotal.add(precioItem);
	    }

	    BigDecimal igv = subtotal.multiply(new BigDecimal("0.18"));
	    BigDecimal total = subtotal.add(igv);

	    order.setSubtotal(subtotal);
	    order.setIgv(igv);
	    order.setTotal(total);

	    order.setItems(orderItems);

	    return orderRepository.save(order);
	}


	public Order ActualizarOrder(Long id, OrderDTO dto) {

	    Order order = orderRepository.findById(id).orElse(null);

	    if (order == null) {
	        return null;
	    }

	    order.setClientId(dto.getClientId());

	    // Limpiar items anteriores
	    order.getItems().clear();

	    List<OrderItem> orderItems = new ArrayList<>();
	    BigDecimal subtotal = BigDecimal.ZERO;

	    for (OrderItemDTO itemDTO : dto.getItems()) {

	        ProductDTO product = productClient.getProductById(itemDTO.getProductId());

	        if (product == null || !product.getEstado()) {
	            throw new RuntimeException("Producto no disponible");
	        }

	        if (product.getStock() < itemDTO.getQuantity()) {
	            throw new RuntimeException(
	                "Stock insuficiente para: " + product.getNombre()
	            );
	        }

	        OrderItem orderItem = new OrderItem();
	        orderItem.setProductId(product.getId());
	        orderItem.setName(product.getNombre());
	        orderItem.setUnitPrice(product.getPrecio());
	        orderItem.setQuantity(itemDTO.getQuantity());
	        orderItem.setOrder(order);

	        orderItems.add(orderItem);

	        BigDecimal precioItem =
	                product.getPrecio()
	                       .multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

	        subtotal = subtotal.add(precioItem);
	    }

	    BigDecimal igv = subtotal.multiply(new BigDecimal("0.18"));
	    BigDecimal total = subtotal.add(igv);

	    order.setSubtotal(subtotal);
	    order.setIgv(igv);
	    order.setTotal(total);
	    order.setItems(orderItems);

	    return orderRepository.save(order);
	}

	
	public void EliminarOrder(Long id) {
		 orderRepository.deleteById(id);
	}
}
