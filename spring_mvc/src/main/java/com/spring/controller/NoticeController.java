package com.spring.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.dto.MemberVO;
import com.spring.dto.NoticeVO;
import com.spring.request.NoticeModifyRequest;
import com.spring.request.NoticeRegistRequest;
import com.spring.request.PageMaker;
import com.spring.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping("/main")
	public void main() {}

	@GetMapping("/list")
	public void list(@ModelAttribute PageMaker pageMaker, Model model) throws Exception {
		List<NoticeVO> noticeList = noticeService.list(pageMaker);		
		model.addAttribute("noticeList",noticeList);
	}	
	
	@GetMapping("/regist")
	public void regist() {}
	
	
	@PostMapping("/regist")
	public String registPost(NoticeRegistRequest regRequest)throws Exception{
		String url = "/notice/regist_success";
	
		NoticeVO notice = regRequest.toNoticeVO();
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		
		noticeService.regist(notice);
		
		return url;
	}
	
	@GetMapping("/detail")
	public void detail(int nno, HttpServletRequest request, Model model)throws Exception{
		
		ServletContext ctx = request.getServletContext();
		HttpSession session = request.getSession();
		
		MemberVO member =(MemberVO)session.getAttribute("loginUser");
		
		//application 객체 : key -> nno+id
		String key = nno+member.getId();
		
		NoticeVO notice = null;
		
		if(ctx.getAttribute(key)!=null) {
			notice = noticeService.getNotice(nno);
		}else {
			ctx.setAttribute(key, key);
			notice = noticeService.detail(nno);
		}	

		model.addAttribute("notice",notice);
	}
	
	@GetMapping("/modify")
	public void modifyGet(int nno, Model model)throws Exception{
		NoticeVO notice = noticeService.getNotice(nno);
		model.addAttribute("notice",notice);
	}
	
	@PostMapping("/modify")
	public String modifyPost(NoticeModifyRequest modifyRequest)throws Exception{
		String url = "/notice/modify_success";
		
		NoticeVO notice = modifyRequest.toNoticeVO();
		
		notice.setTitle(HTMLInputFilter.htmlSpecialChars(notice.getTitle()));
		
		noticeService.modify(notice);
		
		return url;
	}
	
	@Resource(name="summernotePath")
	private String summernotePath;
	
	@GetMapping("/remove")
	public String remove(int nno)throws Exception{
		String url="/notice/remove_success";
		
		NoticeVO notice = noticeService.getNotice(nno);
		
		File dir = new File(summernotePath);
		File[] files = dir.listFiles();
		
		if(files!=null) {
			for(File file : files) {
				if(notice.getContent().contains(file.getName())) {
					if(file.exists()) file.delete();
				}
			}
		}
		
		noticeService.remove(nno);
		
		return url;
	}
}






