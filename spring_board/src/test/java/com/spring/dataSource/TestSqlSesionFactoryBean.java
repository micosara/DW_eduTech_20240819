package com.spring.dataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dto.BoardVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
public class TestSqlSesionFactoryBean {
	
	@Autowired
	private SqlSessionFactory factory;
	
	private SqlSession session;
	
	@Before
	public void initSqlSession() {
		session = factory.openSession();
	}
	
	@After
	public void closeSqlSession() {
		if(session!=null) session.close();
	}
	
	@Test
	public void testSelectBoardByBNO() {
		int bno = 17461;
		String writer = "mama";
		
		BoardVO board = session.selectOne("Board-Mapper.selectBoardByBNO",bno);
		
		Assert.assertEquals(writer, board.getWriter());
	}

}













