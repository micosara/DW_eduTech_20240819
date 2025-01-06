<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:formatNumber  var="price"  value="10000000" pattern="#,###"  />

가격 : ￦${price}

<hr/>

<fmt:formatDate  var="today" value="<%=new Date() %>" pattern="yyyy-MM-dd"/>
오늘 날짜 : ${today}