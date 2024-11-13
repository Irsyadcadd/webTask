package com.practice.web.model.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.practice.web.model.entity.order.Orders;
import com.practice.web.dto.OrdersDto;
import com.practice.web.helper.DtoToEntity;
import com.practice.web.model.entity.order.OrderItem;

public class OrderRepository {
	@Transactional
    public void saveOrder(Orders order) throws RepositoryException {

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
   		EntityManager em = emf.createEntityManager();
        try {
        	em.getTransaction().begin();
            em.persist(order);

            for (OrderItem item : order.getItems()) {
                item.setOrder(order);
                em.persist(item); 
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RepositoryException("Failed to save order: " + e.getMessage());
        }
    }
}
