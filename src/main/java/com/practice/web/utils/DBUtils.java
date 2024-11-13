package com.practice.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtils {

    
	private static String jdbcUrl;
	private static String jdbcUser;
	private static String jdbcPass;
	
	public static Connection getConnection() throws SQLException {
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	public static void closeConnection(Connection connect) throws SQLException {
		if (connect != null) {
			connect.close();
		}
	}


	public static void setJdbcUrl(String jdbcUrl) {
		DBUtils.jdbcUrl = jdbcUrl;
	}


	public static void setJdbcUser(String jdbcUser) {
		DBUtils.jdbcUser = jdbcUser;
	}


	public static void setJdbcPass(String jdbcPass) {
		DBUtils.jdbcPass = jdbcPass;
	}
}
