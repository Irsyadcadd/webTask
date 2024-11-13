package com.practice.web.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practice.web.dto.UserDto;
import com.practice.web.model.repository.RepositoryException;
import com.practice.web.service.UserService;


@WebServlet(urlPatterns = { "/login", "/register" })
public class AuthenticationServlet extends HttpServlet {

	private UserService userService;
	
	@Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/login")) {
            request.getRequestDispatcher("/WEB-INF/jsp/authentication/login.jsp").forward(request, response);
		} else if (path.equals("/register")) {
            request.getRequestDispatcher("/WEB-INF/jsp/authentication/register.jsp").forward(request, response);
		} else {
            request.getRequestDispatcher("/WEB-INF/jsp/error-page.jsp").forward(request, response);
		};
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/login")) {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	      
	        
	        try {
				UserDto user = userService.findByName(username);
				
	            if (user != null && password.equals(user.getPassword())) {
	                HttpSession session = request.getSession();
	                session.setAttribute("user", user);
	                
	                response.sendRedirect(request.getContextPath() + "/customer/order-product-list"); 
	            } else {
	                response.getWriter().println("<script type=\"text/javascript\">");
	                response.getWriter().println("alert('Password salah');");
	                response.getWriter().println("location='login.jsp';");
	                response.getWriter().println("</script>");
	            }
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		} else if (path.equals("/register")) {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String address = request.getParameter("address");
	        
	        try {
				userService.insertUser(username, password, address);
				response.sendRedirect(request.getContextPath() + "/login"); 
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/error-page.jsp").forward(request, response);
		}
	}

}
