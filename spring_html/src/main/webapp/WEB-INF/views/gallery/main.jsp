<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ include file="/WEB-INF/template/header.jsp"  %>
<style>
h1#my{
	height:calc(100vh - 120px);
}
</style>

<h1 id="my"><%=request.getRequestURI() %> 페이지 입니다.</h1>


<%@ include file="/WEB-INF/template/footer.jsp"  %>