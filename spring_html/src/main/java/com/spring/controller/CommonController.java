package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	
	@GetMapping("/main")
	public String main() {
		String url = "/main/main";
		return url;
	}
	
	@GetMapping("/department")
	public String department() {
		String url="/department/main";
		return url;
	}
	
	@GetMapping("/gallery")
	public String gallery() {
		String url="/gallery/main";
		return url;
	}
	
	@GetMapping("/youtube")
	public String youtube() {
		String url="/youtube/main";
		return url;
	}
	
	@GetMapping("/community")
	public String community() {
		String url="/community/main";
		return url;
	}
	
	@GetMapping("/location")
	public String location() {
		String url="/location/main";
		return url;
	}
}
