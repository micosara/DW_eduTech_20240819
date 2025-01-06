package com.spring.request;

import com.spring.dto.BoardVO;

public class BoardModifyRequest extends BoardRegistRequest{

	private int bno;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}
	
	public BoardVO toBoardVO() {
		BoardVO board = super.toBoard();
		
		board.setBno(bno);
		
		return board;
	}
}
