package com.spring.dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class TestDataSource {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	@Before
	public void init()throws Exception{
		conn = dataSource.getConnection();
	}
	
	@Test
	public void testConnection() {		
		Assert.assertNotNull(conn);;
	}
	
	@Test
	public void testSelectMember()throws Exception {
		String targetId = "mimi";		
		String sql = "select * from member where id='"+targetId+"'";
		
		Statement stmt  = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		String destId = null;
		if(rs.next()) {
			destId = rs.getString("id"); 
		} 
		
		Assert.assertEquals(targetId, destId);
			
	}
	
	
	@After
	public void complete()throws Exception {
		if(conn!=null) conn.close();
	}

}






