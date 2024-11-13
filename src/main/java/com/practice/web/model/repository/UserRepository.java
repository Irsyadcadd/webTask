package com.practice.web.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.practice.web.model.entity.user.User;
import com.practice.web.utils.DBUtils;


public class UserRepository {
	
	public User findByName(String username) throws RepositoryException {
		User user = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
		EntityManager em = emf.createEntityManager();
		try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            user = query.getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException("User not found: " + e.getMessage());
        }
		return user;
	}
	
	@Transactional
	public void insertUser(String username, String password, String address) throws RepositoryException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
		EntityManager em = emf.createEntityManager();

	    try {
            User user = new User();
            em.getTransaction().begin();
            user.setUsername(username);
            user.setPassword(password);
            user.setAddress(address);
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RepositoryException("Error inserting user: " + e.getMessage());
        }
	}
}
