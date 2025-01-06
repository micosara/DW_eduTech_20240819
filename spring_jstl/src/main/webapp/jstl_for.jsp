<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	int sum=0;
	for(int i=1;i<11;i++){
		sum += i;
	}
	
	out.println(sum);
%>

<hr/>


<c:set var="sum" value="0" />
<c:forEach var="i" begin="1" end="10" step="1" >
	<c:set var="sum" value="${sum+i }" />
</c:forEach>
${sum}

<hr/>

<%
	String[] fruits = {"apple","grape","banana","pinapple","melon"};
	for(String fruit:fruits){
		out.println(fruit);
	}
%>

<hr/>
<c:set var="fruits" value="apple,grape,pinapple,melon" />
<c:forEach var="fruit"  items="${fruits }"  varStatus="status">
	${status.count } : ${fruit }<br/>
</c:forEach>










