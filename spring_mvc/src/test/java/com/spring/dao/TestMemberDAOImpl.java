package com.spring.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dto.MemberVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/root-context.xml")
public class TestMemberDAOImpl {

	@Autowired
	private MemberDAO memberDAO;	
	
	
	@Test
	public void testSelectMember()throws Exception{
		String targetId = "mimi";
		
		MemberVO member = memberDAO.selectMemberById(targetId);
		
		Assert.assertEquals(targetId, member.getId());
	}
	
}










