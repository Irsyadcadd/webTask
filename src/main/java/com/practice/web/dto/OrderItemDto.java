package com.practice.web.dto;

import com.practice.web.model.entity.product.Product;

public class OrderItemDto {
	private int id;
	private OrdersDto order;
    private ProductDto product;
    private int quantity;
    private long totalPrice;
    
    
	public OrderItemDto() {}
	
    public OrderItemDto(ProductDto product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        updateTotalPrice();
    }
	
	public OrderItemDto(OrdersDto order, ProductDto product, int quantity, long totalPrice) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrdersDto getOrder() {
		return order;
	}
	public void setOrder(OrdersDto order) {
		this.order = order;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
    public void updateTotalPrice() {
        this.totalPrice = product.getPrice() * quantity;
    }
}
