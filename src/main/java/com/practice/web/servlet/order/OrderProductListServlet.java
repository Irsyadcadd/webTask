package com.practice.web.servlet.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practice.web.dto.OrdersDto;
import com.practice.web.dto.ProductDto;
import com.practice.web.dto.UserDto;
import com.practice.web.model.repository.RepositoryException;
import com.practice.web.service.ProductService;


@WebServlet(urlPatterns = {"/customer/order-product-list", "/customer/add-to-cart"})
public class OrderProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductService productService;
	
    @Override
    public void init() throws ServletException {
        super.init();
        this.productService = new ProductService();
    }
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProductDto> listProduct;
		try {
			listProduct = productService.getAllProducts();
			
			request.setAttribute("data_products", listProduct);
			RequestDispatcher dispatchView = request.getRequestDispatcher("/WEB-INF/jsp/order/order-product-list.jsp");
			dispatchView.forward(request, response);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		int id = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		try {
			ProductDto product = productService.getProductById(id);
			
			if (path.equals("/customer/add-to-cart")) {
	            HttpSession session = request.getSession();
	            OrdersDto order = (OrdersDto) session.getAttribute("orderCart");
	            UserDto user = (UserDto) session.getAttribute("user");

	            if (order == null) {
	                order = new OrdersDto();
	                order.setUser(user);
	                session.setAttribute("orderCart", order);
	            }
	            
	            order.addItem(product, quantity);
	            
	            request.setAttribute("order", order);
	            response.sendRedirect(request.getContextPath() + "/customer/order-product-list");
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
