package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dao.SearchMemberDAO;
import com.spring.dto.MemberVO;
import com.spring.request.PageMaker;

public class SearchMemberServiceImpl extends MemberServiceImpl
									 implements SearchMemberService{
	
	private SearchMemberDAO searchMemberDAO;
	
	@Autowired
	public SearchMemberServiceImpl(SearchMemberDAO searchMemberDAO) {
		super(searchMemberDAO);
		this.searchMemberDAO = searchMemberDAO;
	}

	@Override
	public List<MemberVO> searchList(PageMaker pageMaker) throws SQLException {
		List<MemberVO> memberList = searchMemberDAO.selectSearchMemberList(pageMaker);
		
		int totalCount = searchMemberDAO.selectSearchMemberListCount(pageMaker);
		pageMaker.setTotalCount(totalCount);
		
		return memberList;
	}

}






