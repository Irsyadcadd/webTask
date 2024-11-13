package com.practice.web.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.practice.web.dto.UserDto;
import com.practice.web.model.entity.user.User;


@WebFilter(filterName = "/AuthenticationFilter", urlPatterns = "/customer/*")
public class AuthenticationFilter extends HttpFilter implements Filter {


	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		UserDto loginContext = (UserDto) session.getAttribute("user");
		
		if(loginContext == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/authentication/login.jsp").forward(req, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

}
