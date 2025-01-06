package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberVO;
import com.spring.request.PageMaker;

public interface SearchMemberService extends MemberService{

	List<MemberVO> searchList(PageMaker page)throws SQLException;
}
