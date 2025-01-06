package com.spring.dataSource;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
public class TestBasicDataSource {

	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	@Before
	public void initConnection()throws Exception {
		conn = dataSource.getConnection();		
	}
	
	@After
	public void closeConnection()throws Exception{
		if(conn!=null)conn.close();
	}
	
	@Test
	public void testConnection() {
		Assert.assertNotNull(conn);
	}
	
	
}









