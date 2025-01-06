package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dao.MemberDAO;
import com.spring.dao.SearchMemberDAO;
import com.spring.dto.MemberVO;

public class MemberServiceImpl implements MemberService{

	
	private SearchMemberDAO searchMemberDAO;
	
	public MemberServiceImpl(SearchMemberDAO searchMemberDAO) {
		this.searchMemberDAO = searchMemberDAO;		
	}


	@Override
	public MemberVO getMember(String id)throws SQLException {
		MemberVO member = searchMemberDAO.selectMemberById(id);
		if(member!=null)
			member.setAuthorities(searchMemberDAO.selectAuthoritiesById(id));
		return member;
	}


	@Override
	public List<MemberVO> list() throws SQLException {	
		return null;
	}


	@Override
	public void regist(MemberVO member) throws SQLException {
		searchMemberDAO.insertMember(member);
		
		if(member.getAuthorities().size()>0)for(String authority : member.getAuthorities()) {
			searchMemberDAO.insertAuthorities(member.getId(), authority);
		}		
	}


	@Override
	public void modify(MemberVO member) throws SQLException {
		searchMemberDAO.updateMember(member);		
	}


	@Override
	public void remove(String id) throws SQLException {
		searchMemberDAO.deleteMember(id);		
	}


	@Override
	public void modifyAuthority(String id, List<String> authorities) throws SQLException {
		searchMemberDAO.deleteAllAuthorityById(id);
		
		if(authorities.size()>0)for(String authority:authorities) {
			searchMemberDAO.insertAuthorities(id, authority);
		}
	}

}








