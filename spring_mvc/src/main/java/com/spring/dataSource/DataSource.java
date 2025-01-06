package com.spring.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {	
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	public Connection getConnection() throws Exception{
		Class.forName(driverClassName);		
		Connection conn = DriverManager.getConnection(url,username,password);
		return conn;
				
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}




