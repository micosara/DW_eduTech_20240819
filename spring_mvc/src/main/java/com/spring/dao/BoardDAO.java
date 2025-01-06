package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.BoardVO;
import com.spring.request.PageMaker;

public interface BoardDAO {

	List<BoardVO> selectSearchBoardList(PageMaker pageMaker)throws SQLException;
	
	int selectSearchBoardListCount(PageMaker pageMaker) throws SQLException;
	
	BoardVO selectBoardByBno(int bno) throws SQLException;
	
	void insertBoard(BoardVO board)throws SQLException;
	void updateBoard(BoardVO board)throws SQLException;
	void deleteBoard(int bno)throws SQLException;
	
	void increaseViewCnt(int bno)throws SQLException;
	
	int selectBoardSeqNext()throws SQLException;
	
}

