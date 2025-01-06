package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.ReplyVO;
import com.spring.request.PageMaker;

public interface ReplyDAO {
	
	List<ReplyVO> selectReplyList(int bno,PageMaker pageMaker) throws SQLException;
	int countReply(int bno)throws SQLException;
	
	int selectReplySeqNextValue()throws SQLException;
	
	void insertReply(ReplyVO reply)throws SQLException;
	void updateReply(ReplyVO reply)throws SQLException;
	void deleteReply(int rno)throws SQLException;
}
