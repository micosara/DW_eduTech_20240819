package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.dao.ReplyDAO;
import com.spring.dto.ReplyVO;
import com.spring.request.PageMaker;
import com.spring.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	private ReplyService replyService;
	private ReplyDAO replyDAO;
	
	@Autowired
	public ReplyController(ReplyService replyService,ReplyDAO replyDAO) {
		this.replyService = replyService;
		this.replyDAO = replyDAO;
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String,Object>> list(int bno, PageMaker pageMaker)throws Exception{
		ResponseEntity<Map<String,Object>> result=null;
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		List<ReplyVO> replyList = replyService.list(bno, pageMaker);
		
		dataMap.put("replyList", replyList);
		dataMap.put("pageMaker", pageMaker);
		
		result = new ResponseEntity<Map<String,Object>>(dataMap,HttpStatus.OK);
		
		return result;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<String> regist(@RequestBody ReplyVO reply)throws Exception{
		ResponseEntity<String> result = null;
		
		reply.setReplytext(HTMLInputFilter.htmlSpecialChars(reply.getReplytext()));
		
		replyService.regist(reply);
		

		int totalCount = replyDAO.countReply(reply.getBno());
		int perPageNum = new PageMaker().getPerPageNum();
		String pageNum = "" + (int) Math.ceil(totalCount / (double) perPageNum);
		
		result= new ResponseEntity<String>(pageNum, HttpStatus.OK);
		
		return result;
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> modify(@RequestBody ReplyVO reply)throws Exception {
		ResponseEntity<String> entity = null;
		
		reply.setReplytext(HTMLInputFilter.htmlSpecialChars(reply.getReplytext()));

		replyService.modify(reply);
		entity = new ResponseEntity<String>(HttpStatus.OK);
		
		return entity;		
	}
	
	@GetMapping(value = "/remove")
	public ResponseEntity<String> remove(int rno, int bno, int page)throws Exception {
		ResponseEntity<String> entity = null;
		
		replyService.remove(rno);
		
		int totalCount = replyDAO.countReply(bno);
		int perPageNum = new PageMaker().getPerPageNum();
		int realEndPage = (int) Math.ceil(totalCount / (double) perPageNum);
		
		String pageNum = ""+page;
		if (page > realEndPage) {
			pageNum = "" + realEndPage;
		}
		
		entity = new ResponseEntity<String>(pageNum, HttpStatus.OK);
		
		return entity;
	}
}







