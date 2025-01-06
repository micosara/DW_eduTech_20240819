<%@page import="com.spring.dto.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<% 
	pageContext.setAttribute("msg", "pageContext");
	request.setAttribute("msg","request");
	session.setAttribute("msg","session");
	application.setAttribute("msg","application");
%>


<c:set var="msg" value="pageContext_set" scope="page" />
<c:set var="msg" value="request_set" scope="request" />
<c:set var="msg" value="session_set" scope="session" />
<c:set var="msg" value="application_set" scope="application" />

<c:remove var="msg" scope="page"/> 

<hr/>

<%=pageContext.getAttribute("msg") %><br/>
<%=request.getAttribute("msg") %><br/>
<%=session.getAttribute("msg") %><br/>
<%=application.getAttribute("msg") %><br/>


<hr/>
${pageScope.msg }<br/>
${requestScope.msg }<br/>
${sessionScope.msg }<br/>
${applicationScope.msg }<br/>

<hr/>
<%
	Map<String,String> map = new HashMap<String,String>();
	map.put("msg","hello");	
%>

<%=map.get("msg") %>

<hr/>
<c:set var="map" value="<%=map %>" />
${map.msg }


<hr/>
<%
	List<String> list = new ArrayList<String>();
	list.add("a");
	list.add("b");
	list.add("c");
%>

<%=list.get(1) %>
<hr/>
<c:set var="list" value="<%=list %>" />
${list[1] }


<hr/>

<%
	MemberVO member = new MemberVO();
	member.setId("id");
	member.setPwd("pwd");
	member.setName("name");
%>

<%=member.getId() %>,<%=member.getPwd() %>,<%=member.getName() %>

<hr/>
<c:set var="member" value="<%=member %>" />
${member.id },${member.pwd },${member.name }










