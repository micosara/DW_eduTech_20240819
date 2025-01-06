package com.spring.request;

import com.spring.dto.PdsVO;

public class PdsModifyRequest extends PdsRegistRequest {

	private int pno;
	private int[] deleteFile;

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public int[] getDeleteFile() {
		return deleteFile;
	}

	public void setDeleteFile(int[] deleteFile) {
		this.deleteFile = deleteFile;
	}

	@Override
	public PdsVO toPdsVO() {
		PdsVO pds = super.toPdsVO();
		pds.setPno(this.pno);

		return pds;
	}
}





