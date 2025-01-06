package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dao.ReplyDAO;
import com.spring.dto.ReplyVO;
import com.spring.request.PageMaker;

public class ReplyServiceImpl implements ReplyService{

	private ReplyDAO replyDAO;
	public ReplyServiceImpl(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	@Override
	public List<ReplyVO> list(int bno, PageMaker pageMaker) throws SQLException {
		List<ReplyVO> replyList = replyDAO.selectReplyList(bno, pageMaker);
		
		int count = replyDAO.countReply(bno);
		pageMaker.setTotalCount(count);
		
		return replyList;
	}
	@Override
	public void regist(ReplyVO reply) throws SQLException {
		int rno = replyDAO.selectReplySeqNextValue();
		reply.setRno(rno);
		replyDAO.insertReply(reply);		
	}
	@Override
	public void modify(ReplyVO reply) throws SQLException {
		replyDAO.updateReply(reply);		
	}
	@Override
	public void remove(int rno) throws SQLException {
		replyDAO.deleteReply(rno);
		
	}
	
	
	
}
