package com.spring.dataSource;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.MemberVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
@Transactional
public class TestSqlSessionFactoryBean {

	@Autowired
	private SqlSessionFactory factory;
	
	private SqlSession session;
	
	@Before
	public void initSession() {
		session = factory.openSession();
	}
	
	@After
	public void closeSession() {
		if(session!=null) session.close();
	}
	
	@Test
	public void testSelectMemberById() {
		String targetID = "mimi";
		MemberVO member = session.selectOne("Member-Mapper.selectMemberByID",targetID);
		
		Assert.assertEquals(member.getId(), targetID);
	}
	
	@Test
	public void testSelectList() {
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");
		
		Assert.assertEquals(memberList.size(), 7);
	}
	
	@Test
	@Rollback
	public void testInsertMember() {
		MemberVO targetMember = new MemberVO();
		targetMember.setId("kaka");
		targetMember.setPwd("kaka");
		targetMember.setEmail("kaka@naver.com");
		
		int count = session.insert("Member-Mapper.insertMember",targetMember);
		
		Assert.assertEquals(count, 1);
	}
	
	@Test
	@Rollback
	public void testUpdateMember() {
		String tEmail = "ttt@naver.com";
		String tId = "mimi";
		
		MemberVO tMember = session.selectOne("Member-Mapper.selectMemberByID",tId);
		tMember.setEmail(tEmail);
		
		session.update("Member-Mapper.updateMember",tMember);
		
		tMember = session.selectOne("Member-Mapper.selectMemberByID",tId);
		
		Assert.assertEquals(tMember.getEmail(), tEmail);
	}	
	
	
	@Test
	@Rollback
	public void testDeleteMember() {
		String tId = "toto";
		
		MemberVO member = session.selectOne("Member-Mapper.selectMemberByID",tId);
		
		Assert.assertNotNull(member);
		
		int count = session.delete("Member-Mapper.deleteMember",tId);
		
		Assert.assertEquals(count, 1);
		
		member = session.selectOne("Member-Mapper.selectMemberByID",tId);
		
		Assert.assertNull(member);
		
	}
}











