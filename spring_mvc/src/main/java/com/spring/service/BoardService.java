package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.BoardVO;
import com.spring.request.PageMaker;

public interface BoardService {
	
	// 목록 : 검색포함.
	List<BoardVO> list(PageMaker pageMaker) throws SQLException;
		
	// 상세 : 조회수 증가
	BoardVO detail(int bno) throws SQLException;
		
	// 등록
	void regist(BoardVO board)throws SQLException;
	
	// 수정
	void modify(BoardVO board)throws SQLException;
	BoardVO getBoard(int bno)throws SQLException;
	
	// 삭제
	void remove(int bno)throws SQLException;
}







