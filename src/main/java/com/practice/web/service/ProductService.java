package com.practice.web.service;

import com.practice.web.dto.ProductDto;
import com.practice.web.model.entity.product.Product;
import com.practice.web.model.repository.ProductRepository;
import com.practice.web.model.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@RequestScoped
public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public List<ProductDto> getAllProducts() throws RepositoryException {
    	List<Product> listProducts = productRepository.findAll();
    	List<ProductDto> listProductsDto = new ArrayList<>();
    	for (Product product: listProducts) {
    		listProductsDto.add(new ProductDto(
    					product.getId(),
    					product.getName(),
    					product.getPrice(),
    					product.getType()
    				));
    	}
        return listProductsDto;
    }

    public ProductDto getProductById(int id) throws RepositoryException {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RepositoryException("Product with ID " + id + " not found.");
        }
        ProductDto productDto = new ProductDto(
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getType()
			);
        return productDto;
    }

    public void updateProduct(int id, String name, String type, long price) throws RepositoryException {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct == null) {
            throw new RepositoryException("Product with ID " + id + " does not exist.");
        }
        productRepository.updateProduct(id, name, type, price);
    }

    public void addProduct(String name, String type, long price) throws RepositoryException {
        productRepository.insertProduct(name, type, price);
    }
    
    public void deleteProduct(int id ) throws RepositoryException {
    	productRepository.deleteProduct(id);    
    }
}
