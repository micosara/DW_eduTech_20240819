package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.MemberVO;


@RestController
public class RestTestController {

	@GetMapping("/hello")	
	public String hello() {
		String url="RestController Messasge!!!!";
		return url;
	}
	
	@GetMapping("/echo")
	public String echo(String message) {
		System.out.println(message);
		return "Server return : "+message; 
	}
	
	@PostMapping("/json")
	public MemberVO json(@RequestBody MemberVO member) {
		System.out.println(member.getPhone());
		
		member.setPhone("999-9999-9999");
		
		return member;
	}
	
	@PostMapping("/form")
	public String form(MemberVO member) {
		System.out.println(member.getPhone());
		
		return "received";
	}
}









