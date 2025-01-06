package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberVO;
import com.spring.request.PageMaker;

public interface SearchMemberDAO extends MemberDAO{

	List<MemberVO> selectSearchMemberList(PageMaker pageMaker) throws SQLException;
	
	int selectSearchMemberListCount(PageMaker pageMaker)throws SQLException;
	
}
