package com.spring.command;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class FormRequest {
	
	private String txt;
	private String pwd;
	private String email;
	private Date date;
	private String radio;
	private List<String> checkBox;
	private int select;
	private String textArea;
	
	
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public List<String> getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(List<String> checkBox) {
		this.checkBox = checkBox;
	}
	public int getSelect() {
		return select;
	}
	public void setSelect(int select) {
		this.select = select;
	}
	public String getTextArea() {
		return textArea;
	}
	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}
	@Override
	public String toString() {
		return "FormRequest [txt=" + txt + ", pwd=" + pwd + ", email=" + email + ", date=" + date + ", radio=" + radio
				+ ", checkBox=" + checkBox + ", select=" + select + ", textArea=" + textArea + "]";
	}
	
	
	
	
}
