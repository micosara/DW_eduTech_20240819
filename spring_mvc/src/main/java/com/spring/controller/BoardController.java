package com.spring.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.dto.BoardVO;
import com.spring.dto.MemberVO;
import com.spring.request.BoardModifyRequest;
import com.spring.request.BoardRegistRequest;
import com.spring.request.PageMaker;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("/main")
	public void main() {}
	
	@GetMapping("/list")
	public void list(@ModelAttribute PageMaker pageMaker, Model model)throws Exception{
		List<BoardVO> boardList = boardService.list(pageMaker);
		
		model.addAttribute("boardList",boardList);		
	}
	
	@GetMapping("/detail")
	public void detail(int bno,HttpSession session, Model model)throws Exception{
		
		ServletContext ctx = session.getServletContext();
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		
		String key = member.getId()+bno;
		
		if(ctx.getAttribute(key)!=null) {
			model.addAttribute("board",boardService.getBoard(bno));
		}else {
			ctx.setAttribute(key, key);
			model.addAttribute("board",boardService.detail(bno));
		}
	}
	
	@GetMapping("/regist")
	public void registForm() {}
	
	@PostMapping("/regist")
	public String registPost(BoardRegistRequest regRequest)throws Exception{
		String url = "/board/regist_success";
		
		BoardVO board = regRequest.toBoard();
		board.setTitle(HTMLInputFilter.htmlSpecialChars(board.getTitle()));
		
		boardService.regist(board);
		
		return url;
	}
	
	@GetMapping("/modify")
	public void modifyForm(int bno, Model model)throws Exception{
		BoardVO board = boardService.getBoard(bno);
		
		model.addAttribute("board",board);
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardModifyRequest modifyReq)throws Exception{
		
		String url = "/board/modify_success";
		
		BoardVO board = modifyReq.toBoardVO();
		board.setTitle(HTMLInputFilter.htmlSpecialChars(board.getTitle()));
		
		boardService.modify(board);
		
		return url;		
	}
	
	
	@GetMapping("/remove")
	public String remove(int bno)throws Exception{
		String url="/board/remove_success";		
		
		boardService.remove(bno);
		
		return url;
	}
}










