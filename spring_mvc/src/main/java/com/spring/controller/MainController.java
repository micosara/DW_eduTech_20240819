package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.dto.MenuVO;
import com.spring.service.MenuService;

@Controller
public class MainController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/index")
	public String main(String mcode,Model model) throws Exception{
		String url="/main";
		
		List<MenuVO> menuList = menuService.getMainMenuList();
		
		if (mcode != null) {
			MenuVO menu = menuService.getMenuByMcode(mcode);
			model.addAttribute("menu", menu);
		}
		
		model.addAttribute("menuList",menuList);
		
		return url;
	}
	
	@GetMapping("/main")
	public String home() {
		return "/commons/home";
	} 
}






