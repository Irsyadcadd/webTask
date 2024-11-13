package com.practice.web.servlet.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.web.model.entity.product.ProductTypeList;
import com.practice.web.model.repository.ProductRepository;
import com.practice.web.model.repository.RepositoryException;
import com.practice.web.service.ProductService;


@WebServlet("/admin/new-product")
public class NewProductServlet extends HttpServlet {
	
	private ProductService productService;
	
    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("product_types", ProductTypeList.getProductTypes());
		RequestDispatcher dispatchView = request.getRequestDispatcher("/WEB-INF/jsp/product/product-new.jsp");
		dispatchView.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String type = request.getParameter("type");
        long price = Long.valueOf(request.getParameter("price"));
        
        try {
			productService.addProduct(name, type, price);
			response.sendRedirect(request.getContextPath() + "/admin/list-products");
			
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

}
