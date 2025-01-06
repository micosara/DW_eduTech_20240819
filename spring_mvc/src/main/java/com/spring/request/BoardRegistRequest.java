package com.spring.request;

import com.spring.dto.BoardVO;

public class BoardRegistRequest {

	private String writer;
	private String title;
	private String content;
	
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public BoardVO toBoard() {
		BoardVO board = new BoardVO();
		
		board.setContent(content);
		board.setTitle(title);
		board.setWriter(writer);
		
		return board;
	}
}









