<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
    
<%
	int k=8;
	if(k % 2 == 0) {
		out.println("짝수입니다.");
	}
%>

<hr/>

<c:set var="k" value="8" />
<c:if test="${ k % 2 == 0 }">
짝수입니다.
</c:if>


<hr/>

<%
	int score = 86;
	String grade = null;
	if(score > 90){
		grade = "A";
	}else if(score > 80){
		grade = "B";
	}else if(score > 70){
		grade = "C";
	}else if(score >60){
		grade = "D";
	}else{
		grade = "F";	
	}

	out.println("당신의 학점은 "+grade+" 입니다.");
%>

<hr/>
<c:set var="score" value="86" />
<c:set var="grade" value="" />
<c:choose>
	<c:when test="${score > 90 }">
		<c:set var="grade" value="A" />
	</c:when>
	<c:when test="${score > 80 }">
		<c:set var="grade" value="B" />	
	</c:when>
	<c:when test="${score > 70 }">
		<c:set var="grade" value="C" />
	</c:when>
	<c:when test="${score > 60 }">
		<c:set var="grade" value="D" />
	</c:when>	
	<c:otherwise>
		<c:set var="grade" value="F" />
	</c:otherwise>
</c:choose>

당신의 학접은 ${grade }입니다.









