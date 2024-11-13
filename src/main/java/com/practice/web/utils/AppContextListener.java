package com.practice.web.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
         ServletContext ctx = sce.getServletContext();
         String dbUrl = ctx.getInitParameter("jdbcUrl");
         String dbUser = ctx.getInitParameter("jdbcUser");
         String dbPass = ctx.getInitParameter("jdbcPass");
         
         DBUtils.setJdbcUrl(dbUrl);
         DBUtils.setJdbcUser(dbUser);
         DBUtils.setJdbcPass(dbPass);
    }
	
}
