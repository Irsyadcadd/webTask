package com.practice.web.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.web.dto.OrdersDto;
import com.practice.web.dto.ProductDto;
import com.practice.web.dto.UserDto;
import com.practice.web.model.entity.order.OrderItem;
import com.practice.web.model.entity.order.Orders;
import com.practice.web.model.entity.product.Product;
import com.practice.web.model.entity.user.User;

public class DtoToEntity {
    public static Orders OrderToEntity(OrdersDto ordersDto) {

        Orders order = new Orders();

        List<OrderItem> items = ordersDto.getItems().stream()
            .map(itemDto -> {
                OrderItem item = new OrderItem(
	                		ProductToEntity(itemDto.getProduct()),
	                		itemDto.getQuantity()
                		);
                
                return item;
            })
            .collect(Collectors.toList());

        order.setItems(items);
        order.setTotalPrice(ordersDto.getTotalPrice());
        order.setUser(UserToEntity(ordersDto.getUser()));

        return order;
    }
    
    public static Product ProductToEntity(ProductDto productDto) {
    	Product product = new Product(
    				productDto.getId(),
    				productDto.getName(),
    				productDto.getPrice(),
    				productDto.getType()
    				);
    	return product;
    }
    
    public static User UserToEntity(UserDto userDto) {
    	User user = new User(
    				userDto.getUsername(),
    				userDto.getPassword(),
    				userDto.getAddress()
    				);
    	return user;
    }
}
