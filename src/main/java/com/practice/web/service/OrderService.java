package com.practice.web.service;

import javax.inject.Inject;

import com.practice.web.dto.OrdersDto;
import com.practice.web.helper.DtoToEntity;
import com.practice.web.model.entity.order.Orders;
import com.practice.web.model.entity.user.User;
import com.practice.web.model.repository.OrderRepository;
import com.practice.web.model.repository.RepositoryException;

public class OrderService {
	
	private final OrderRepository orderRepository = new OrderRepository();
	
    public void addOrder(OrdersDto orderDto) throws RepositoryException {
		Orders order = DtoToEntity.OrderToEntity(orderDto);
        orderRepository.saveOrder(order);
    }

}
