package com.spring.interceptor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.dto.MemberVO;

public class NoticeRegistInterceptor extends HandlerInterceptorAdapter{

	private String savePath;
	private String saveFileName = "notice_regist_user.csv";
	public NoticeRegistInterceptor(String logSavedPath) {
		this.savePath = logSavedPath;
	}
	public void setSaveFileName(String saveFileName) {		
		this.saveFileName = saveFileName;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		
		if(title==null && writer==null) return;		
		
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		String tag ="[notice:regist]";
		String log =tag
					+loginUser.getId()+","					
					+loginUser.getPhone()+","
					+loginUser.getEmail()+","
					+request.getRemoteAddr()+","
					+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		File file=new File(savePath);
		file.mkdirs();
		
		String logFilePath=savePath+File.separator+saveFileName;	
		BufferedWriter out=new BufferedWriter(new FileWriter(logFilePath,true));


		//로그를 기록
		out.write(log);
		out.newLine();
		
		out.close();
		
	}



	
}


