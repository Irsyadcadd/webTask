package com.practice.web.dto;

import java.util.List;

import com.practice.web.model.entity.order.OrderItem;
import com.practice.web.model.entity.product.Product;

public class OrdersDto {

    private int id;
    private UserDto user;
	private List<OrderItemDto> items;
    private long totalPrice;
    
	public OrdersDto() {}
	public OrdersDto(int id, UserDto user, List<OrderItemDto> items, long totalPrice) {
		super();
		this.id = id;
		this.user = user;
		this.items = items;
		this.totalPrice = totalPrice;
	}
	
    public void addItem(ProductDto product, int quantity) {
        for (OrderItemDto item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                item.updateTotalPrice();
                totalPrice += product.getPrice() * quantity;
                return;
            }
        }
        OrderItemDto newItem = new OrderItemDto(product, quantity);
        items.add(newItem);
        totalPrice += product.getPrice() * quantity;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public List<OrderItemDto> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
}
