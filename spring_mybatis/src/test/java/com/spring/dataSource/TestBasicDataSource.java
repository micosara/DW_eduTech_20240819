package com.spring.dataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class TestBasicDataSource {

	
	@Autowired
	private DataSource dataSource;
	
	private Connection conn;
	
	@Before
	public void initConnction()throws Exception {
		conn= dataSource.getConnection();
	}
	
	@After
	public void closeConnection()throws Exception{
		if(conn!=null) conn.close();
	}
	
	@Test
	public void testConnection() {
		Assert.assertNotNull(conn);
	}
	
	@Test
	public void testSelectMember()throws SQLException{
		int targetBNO = 17461; 
		String destWriter = "mama";
		
		// 1. statement 생성
		Statement stmt = conn.createStatement();
		
		// 2. Qeury 작성..
		String query = "select * from board where bno="+targetBNO;
		
		// 3. Qeury 실행 + 결과 득..
		ResultSet rs = stmt.executeQuery(query);		
		
		// 4. destination data check ( destWriter / rs.getString("writer")
		if(rs.next()) {
			Assert.assertEquals(destWriter, rs.getString("writer"));
		}else {
			Assert.assertNotNull(null);
		}
		
		
	}
	
	
}











