package com.spring.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;

@Controller
@RequestMapping("/commons")
public class CommonsController {

	
	private MemberService memberService;
	
	@Autowired
	public CommonsController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/login")
	public String loginGet(@ModelAttribute(name = "retUrl") String retUrl,HttpServletResponse response) {		
		String url = "/commons/loginForm";
		response.setStatus(302);
		
		return url;
	}
	
//	@PostMapping("/login")
//	public String loginPost(String id, String pwd, String retUrl, HttpServletRequest request,HttpSession session){
//		String url="/commons/login_success";
//		
//		MemberVO member=null;
//		
//		try {
//			member = memberService.getMember(id);			
//		} catch (SQLException e) {
//			url = "/error/500";
//			return url;
//		}
//				
//		String message =null;
//		
//		if(member!=null) {
//			if(pwd.equals(member.getPwd())) { //로그인 성공.
//				message = "로그인 성공";
//				
//				session.setAttribute("loginUser",member);
//				//session.setMaxInactiveInterval(10);
//				
//				if(retUrl!=null && !retUrl.isEmpty()) {
//					url="redirect:"+retUrl;
//				}
//				
//			}else { //패스워드 불일치				
//				url="/commons/login_fail";
//				message = "패스워드 불일치";
//			}
//		}else {  //아이디 불일치
//			url="/commons/login_fail";
//			message = "아이디 불일치";
//		}	
//		
//		return url;
//	}
//	
//	@GetMapping("/logout")
//	public String logout(HttpServletRequest request, HttpSession session) {
//		String url="redirect:/"+request.getContextPath();
//		
//		session.invalidate(); //세션 갱신
//		
//		return url;
//	}
//	

	
	@GetMapping("/accessDenied")
	public void addcessDenied() {}
	
	@GetMapping("/loginTimeOut")
	public String loginTimeOut(Model model) throws Exception {

		String url = "/commons/sessionOut";
		model.addAttribute("message", "세션이 만료되었습니다.\\n다시 로그인 하세요!");
		return url;
	}
	
	@GetMapping("/loginExpired")
	public String loginExpired(Model model) throws Exception {

		String url = "/commons/sessionOut";
		model.addAttribute("message", "다른 장치에서 중복 로그인이 확인되었습니다.\\n다시 로그인 하세요!");
		return url;
	}
	
	@GetMapping("/sessionCheck")
	@ResponseBody
	public String sessionCheck(HttpSession session) {
		String loginUser_id=null;		
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser!=null) loginUser_id=loginUser.getId();
		
		return loginUser_id;
	}
}














