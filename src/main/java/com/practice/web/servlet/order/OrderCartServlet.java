package com.practice.web.servlet.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practice.web.dto.OrdersDto;
import com.practice.web.model.entity.order.Orders;
import com.practice.web.model.entity.user.User;
import com.practice.web.service.OrderService;


@WebServlet(urlPatterns = {"/customer/order-cart", "/customer/add-order"})
public class OrderCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService;
	
    @Override
    public void init() throws ServletException {
        super.init();
        this.orderService = new OrderService();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Orders order = (Orders) session.getAttribute("orderCart");
        if (order == null) {
        	RequestDispatcher dispatchView = request.getRequestDispatcher("/WEB-INF/jsp/error-page.jsp");
        	dispatchView.forward(request, response);
        } else {
            request.setAttribute("order", order);
    		RequestDispatcher dispatchView = request.getRequestDispatcher("/WEB-INF/jsp/order/order-cart.jsp");
    		dispatchView.forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
        HttpSession session = request.getSession();
        OrdersDto order = (OrdersDto) session.getAttribute("orderCart");

        try {        	
        	if (path.equals("/customer/add-order")) {
	            orderService.addOrder(order);
	
	            session.removeAttribute("orderCart");
	
	            response.sendRedirect(request.getContextPath() + "/customer/order-product-list");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatchView = request.getRequestDispatcher("/WEB-INF/jsp/error-page.jsp");
            dispatchView.forward(request, response);
        }
        
	}

}
