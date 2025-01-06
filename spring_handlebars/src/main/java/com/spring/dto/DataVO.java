package com.spring.dto;

import java.util.Date;

public class DataVO {

	private String a;
	private String b;
	private String c;
	private Date today;
	
		
	public DataVO() {}
	public DataVO(String a, String b, String c, Date today) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.today = today;
	}
	
	
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	
	
}
