package com.practice.web.model.repository;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.practice.web.model.entity.product.Product;


@ApplicationScoped
public class ProductRepository {
	public List<Product> findAll() throws RepositoryException {
		List<Product> listProduct = new ArrayList<Product>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
		EntityManager em = emf.createEntityManager();
		
		
		String query = "SELECT p FROM Product p";
        Query q = em.createQuery(query);
        listProduct = (List<Product>) q.getResultList();
        
        em.close();       
        emf.close();
		return listProduct;
	}
	
	public Product findById(int id) throws RepositoryException {
   		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
   		EntityManager em = emf.createEntityManager();
		Product product = null;
		try {
            product = em.find(Product.class, id); 
        } catch (Exception e) {
            throw new RepositoryException("Error finding product by id: " + e.getMessage());
        } finally {
            em.close();       
            emf.close();
        }
		return product;
	}
	
	@Transactional
	public void updateProduct(int id, String name, String type, long price) throws RepositoryException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
   		EntityManager em = emf.createEntityManager();
   		try {
            Product product = em.find(Product.class, id);
            if (product != null) {
            	em.getTransaction().begin();
                product.setName(name);
                product.setPrice(price);
                product.setType(type);
                em.merge(product);
                em.getTransaction().commit();
            } else {
                throw new RepositoryException("Product not found.");
            }
        } catch (Exception e) {
            throw new RepositoryException("Error updating product: " + e.getMessage());
        } finally {
            em.close();       
            emf.close();
        }
	}
	
	@Transactional
	public void insertProduct(String name, String type, long price) throws RepositoryException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
   		EntityManager em = emf.createEntityManager();
		try {
            Product product = new Product();
            em.getTransaction().begin();
            product.setName(name);
            product.setType(type);
            product.setPrice(price);
            
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RepositoryException("Error inserting product: " + e.getMessage());
        } finally {
            em.close();       
            emf.close();
        }
	}
	
	@Transactional
	public void deleteProduct(int id) throws RepositoryException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
   		EntityManager em = emf.createEntityManager();
		try {
            Product product = em.find(Product.class, id);
            if (product != null) {
            	em.getTransaction().begin();
                em.remove(product);
                em.getTransaction().commit();
            } else {
                throw new RepositoryException("Product not found.");
            }
        } catch (Exception e) {
            throw new RepositoryException("Error deleting product: " + e.getMessage());
        } finally {
            em.close();       
            emf.close();
        }
	}
}
