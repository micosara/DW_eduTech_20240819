package com.spring.request;

import com.spring.dto.NoticeVO;

public class NoticeModifyRequest extends NoticeRegistRequest{
	
	private int nno;

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	@Override
	public NoticeVO toNoticeVO() {
		
		NoticeVO notice = super.toNoticeVO();		
		notice.setNno(nno);
		
		return notice;
	}
	
	
	
	
}
