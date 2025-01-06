package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dto.MemberVO;
import com.spring.request.PageMaker;

public class SearchMyBatisMemberDAOImpl extends MyBatisMemberDAOImpl
										implements SearchMemberDAO{

	private SqlSession session;
	
	@Autowired
	public SearchMyBatisMemberDAOImpl(SqlSession session) {
		super(session);
		this.session = session;
	}

	@Override
	public List<MemberVO> selectSearchMemberList(PageMaker pageMaker) throws SQLException {
		int offset = pageMaker.getStartRow()-1;
		int limit = pageMaker.getPerPageNum();
		
		RowBounds rows = new RowBounds(offset,limit);
		
		List<MemberVO> memberList 
			= session.selectList("Member-Mapper.selectSearchMemberList",pageMaker,rows);
		return memberList;
	}

	@Override
	public int selectSearchMemberListCount(PageMaker pageMaker) throws SQLException {
		return session.selectOne("Member-Mapper.selectSearchMemberListCount",pageMaker);
	}

	
}








