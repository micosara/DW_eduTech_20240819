package com.spring.request;

import com.spring.dto.MemberVO;

public class MemberModifyRequest extends MemberRegistRequest{

	@Override
	public MemberVO toMemberVO() {
		return super.toMemberVO();
	}

}
