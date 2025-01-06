package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MemberVO;
import com.spring.exception.NotExistPictureFileException;
import com.spring.request.MemberModifyRequest;
import com.spring.request.MemberRegistRequest;
import com.spring.request.PageMaker;
import com.spring.service.SearchMemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private SearchMemberService memberService;

	@Autowired
	public MemberController(SearchMemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/main")
	public void main() {}
	
	@GetMapping("/list")
	public String list(PageMaker pageMaker, Model model) {
		String url = "/member/list";
		try {

			List<MemberVO> memberList = memberService.searchList(pageMaker);

			model.addAttribute("memberList", memberList);
			model.addAttribute("pageMaker",pageMaker);
			
		} catch (SQLException e) {
			e.printStackTrace();
			url = "/error/500";
		}

		return url;
	}

	@GetMapping("/regist")
	public void registForm() {
	}

	@GetMapping("/idCheck")
	@ResponseBody
	public String idCheck(String id) throws Exception {
		String result = "duplicated";
		MemberVO member = memberService.getMember(id);
		if (member == null)
			result = "";

		return result;
	}

	@Resource(name = "picturePath")
	private String picturePath;

	@PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
	public ModelAndView regist(MemberRegistRequest regRequest, ModelAndView mnv) {
		String url = "/member/regist_success";
		try {
			MultipartFile multi = regRequest.getPicture();
			String fileName = savePicture(null, multi);

			// DB 저장
			MemberVO member = regRequest.toMemberVO();
			member.setPicture(fileName);

			memberService.regist(member);

		} catch (NotExistPictureFileException e) {
			url = "/error/400.jsp";
			e.printStackTrace();
		} catch (Exception e) {
			url = "/error/500.jsp";
			e.printStackTrace();
		}

		mnv.setViewName(url);
		return mnv;
	}

	@GetMapping("/getPicture")
	@ResponseBody
	public ResponseEntity<byte[]> getPicture(String id) {
		ResponseEntity entity = null;

		MemberVO member = null;
		try {
			 member = memberService.getMember(id);
		}catch(SQLException e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (member == null)
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);

		String picture = member.getPicture();
		String imgPath = this.picturePath;

		InputStream in = null;

		try {
			in = new FileInputStream(new File(imgPath, picture));
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), HttpStatus.OK);
			return entity;
			
		}catch(IOException e) {
			System.out.println("Not Founded ::: "+member.getId()+":"+member.getPicture());
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
	
	@GetMapping("/detail")
	public ModelAndView detail(String id, ModelAndView mnv) throws Exception {
		String url = "/member/detail";

		MemberVO member = memberService.getMember(id);

		mnv.addObject("member", member);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modifyForm(String id, ModelAndView mnv) throws Exception {
		String url = "/member/modify";
		
		MemberVO member = memberService.getMember(id);
		
		mnv.addObject("member", member);
		mnv.setViewName(url);
		return mnv;
	}

	@PostMapping(value = "/modify", produces = "text/plain;charset=utf-8")
	public ModelAndView modify(MemberModifyRequest modifyRequest, ModelAndView mnv) throws Exception {
		String url = "/member/modify_success";
		
		MemberVO member = modifyRequest.toMemberVO();
		MultipartFile multi = modifyRequest.getPicture();
		String oldPicture = memberService.getMember(member.getId()).getPicture();
		
		//파일 저장 및 삭제
		try {
			String fileName = savePicture(oldPicture, multi);
			member.setPicture(fileName);
		}catch (NotExistPictureFileException e) {
			member.setPicture(oldPicture);
		}catch (Exception e) {
			url = "/error/500.jsp";
			e.printStackTrace();
		}
		
		// DB 수정
		memberService.modify(member);
		
		mnv.addObject("id",member.getId());
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping(value = "/remove")
	public ModelAndView remove(String id,ModelAndView mnv) throws Exception {
		String url = "/member/remove_success";

		// 이미지 파일을 삭제
		MemberVO member = memberService.getMember(id);
		String savePath = this.picturePath;
		File imageFile = new File(savePath, member.getPicture());
		if (imageFile.exists()) {
			imageFile.delete();
		}
		// db삭제
		memberService.remove(id);

		mnv.setViewName(url);
		return mnv;
	}
	
	public String savePicture(String oldPicture, MultipartFile multi)
			throws NotExistPictureFileException, IllegalStateException, IOException {

		if (multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 1)
			throw new NotExistPictureFileException();

		// 저장 파일명
		String fileName = null;

		// 파일저장폴더설정
		String uploadPath = this.picturePath;

		// 파일유무확인, 저장 파일명 결정

		String uuid = UUID.randomUUID().toString().replace("-", "");
		fileName = uuid + "$$" + multi.getOriginalFilename();
		File storeFile = new File(uploadPath, fileName);

		// 파일경로 생성
		storeFile.mkdirs();

		// local HDD 에 저장.
		multi.transferTo(storeFile);

		// 기존파일 삭제
		if (oldPicture != null && !oldPicture.isEmpty()) {
			File oldFile = new File(uploadPath, oldPicture);
			if (oldFile.exists()) {
				oldFile.delete();
			}
		}

		return fileName;
	}

	@GetMapping("/authority/modifyForm")
	public void modifyAuthorityForm(String id,Model model)throws Exception {
		MemberVO member = memberService.getMember(id);
		model.addAttribute("member",member);		
	}
	
	@PostMapping("/authority/modify")
	public String modify(String id, String[] authorities)throws SQLException{
		String url="/member/authority/success";
		memberService.modifyAuthority(id, Arrays.asList(authorities));		
		return url;
	}
	
	
}
