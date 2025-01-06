package com.spring.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.dao.AttachDAO;
import com.spring.dto.AttachVO;
import com.spring.dto.MemberVO;
import com.spring.dto.PdsVO;
import com.spring.request.PageMaker;
import com.spring.request.PdsModifyRequest;
import com.spring.request.PdsRegistRequest;
import com.spring.service.PdsService;

@Controller
@RequestMapping("/pds")
public class PdsController {

	private PdsService pdsService;
	private AttachDAO attachDAO;
	
	public PdsController(PdsService pdsService,AttachDAO attachDAO) {
		this.attachDAO = attachDAO;
		this.pdsService = pdsService;
	}
	
	@GetMapping("/main")
	public void main() {}
	
	@GetMapping("/list")
	public ModelAndView list(@ModelAttribute PageMaker pageMaker, ModelAndView mnv) throws Exception {
		String url="/pds/list";
		
		List<PdsVO> pdsList = pdsService.searchList(pageMaker);
		
		mnv.addObject("pdsList",pdsList);
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv) throws Exception {
		String url="/pds/regist";
		mnv.setViewName(url);
		return mnv;
	}
	
	@javax.annotation.Resource(name="pdsSavedFilePath")
	private String fileUploadPath;

	@PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
	public ModelAndView regist(PdsRegistRequest regRequest, ModelAndView mnv)throws Exception{
		String url="/pds/regist_success";
		
		List<MultipartFile> multiFiles = regRequest.getUploadFile();
		
		//save file
		List<AttachVO> attachList = saveFileToAttaches(multiFiles,fileUploadPath);
		
		//DB 
		PdsVO pds = regRequest.toPdsVO();
		pds.setAttachList(attachList);
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		
		pdsService.regist(pds);
		
		mnv.setViewName(url);
		return mnv;
	}
	
	private List<AttachVO> saveFileToAttaches(List<MultipartFile> multiFiles,String savePath )throws Exception{
		if (multiFiles == null) return null;
		
		//저장 -> attachVO -> attachList.add
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		for (MultipartFile multi : multiFiles) {
			
			//파일명
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String fileName = uuid+"$$"+multi.getOriginalFilename();
			
			//파일저장
			File target = new File(savePath, fileName);
			target.mkdirs();
			multi.transferTo(target);
			
			//attachVO
			AttachVO attach = new AttachVO();
			attach.setUploadPath(savePath);
			attach.setFileName(fileName);
			attach.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
			
			//attchList 추가
			attachList.add(attach);
		}
		
		
		return attachList;
	}
	
	@GetMapping("/detail")
	public ModelAndView detail(int pno, HttpSession session, ModelAndView mnv) throws Exception {
		String url="/pds/detail";
		
		ServletContext ctx = session.getServletContext();
		
		MemberVO member = (MemberVO)session.getAttribute("loginUser");
		String key =  member.getId()+pno;
		
		
		PdsVO pds = null;
		if(ctx.getAttribute(key)!=null) {
			pds = pdsService.getPds(pno);
		}else {
			pds = pdsService.detail(pno);
			ctx.setAttribute(key, key);			
		}
				
		mnv.addObject("pds",pds);		
		mnv.setViewName(url);
		return mnv;
	}
	

	@GetMapping("/getFile")
	@ResponseBody
	public ResponseEntity<Resource> getFile(int ano) throws Exception {
						
		AttachVO attach  = attachDAO.selectAttachByAno(ano);
	    String filePath = attach.getUploadPath() + File.separator + attach.getFileName();
		
		
	    Resource resource = new UrlResource(Paths.get(filePath).toUri());
	    
	    return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + 
						UriUtils.encode(attach.getFileName().split("\\$\\$")[1], "UTF-8") + "\"")
                .body(resource);		
	}
	
	@GetMapping("/modify")
	public void modifyForm(int pno, Model model) throws Exception {
		PdsVO pds = pdsService.getPds(pno);		
		model.addAttribute("pds",pds);
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(PdsModifyRequest modRequest, ModelAndView mnv)throws Exception{
		String url = "/pds/modify_success";
		

		//파일삭제
		if(modRequest.getDeleteFile() != null && modRequest.getDeleteFile().length > 0) {
			for(int ano : modRequest.getDeleteFile()) {
				AttachVO attach = attachDAO.selectAttachByAno(ano);
												
				File deleteFile = new File(attach.getUploadPath(), attach.getFileName());
				
				if(deleteFile.exists()) {
					deleteFile.delete(); //파일삭제
				}
				attachDAO.deletAttach(ano); //DB삭제
			}
		}
		
		// 파일저장
		List<AttachVO> attachList = saveFileToAttaches(modRequest.getUploadFile(), fileUploadPath);

		// PdsVO setting
		PdsVO pds = modRequest.toPdsVO();
		pds.setAttachList(attachList);

		// DB 저장
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		pdsService.modify(pds);
		
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@GetMapping("/remove")
	public ModelAndView remove(int pno, ModelAndView mnv) throws Exception {
		String url = "/pds/remove_success";

		// 첨부파일 삭제
		List<AttachVO> attachList = pdsService.getPds(pno).getAttachList();
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if (target.exists()) {
					target.delete();
				}
			}
		}
		
		//DB 삭제
		pdsService.remove(pno);

		mnv.setViewName(url);
		return mnv;
	}
	
}











