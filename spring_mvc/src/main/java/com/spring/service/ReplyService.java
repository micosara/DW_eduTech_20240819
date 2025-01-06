package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.ReplyVO;
import com.spring.request.PageMaker;

public interface ReplyService {
	
	//목록
	List<ReplyVO> list(int bno, PageMaker pageMaker)throws SQLException;
	
	//등록
	void regist(ReplyVO reply) throws SQLException;
	
	//수정
	void modify(ReplyVO reply)throws SQLException;
	
	//삭제
	void remove(int rno)throws SQLException;
}
 










