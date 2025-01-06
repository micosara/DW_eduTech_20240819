package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.command.FormRequest;

@Controller
public class HomeController {

	
	@GetMapping("/home")
	public String home() {
		String url = "home";
		return url;
	}
	
	@GetMapping("/commons/login")
	public void loginGet() {}
	
	@PostMapping("/commons/login")
	public String loginPost(LoginRequest req) {
				
		System.out.println(req.getId()+":"+req.getPwd());
		return null;
	}
	
	@GetMapping("/form")
	public void form() {}
	
	@PostMapping("/form")
	public void formPost(FormRequest req) {
		System.out.println(req);
	}
	
}













