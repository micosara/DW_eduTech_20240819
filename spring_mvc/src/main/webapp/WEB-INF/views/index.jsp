<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% if(session.getAttribute("loginUser")!=null){ %>

<script>
	location.href="<%=request.getContextPath()%>/index";
</script>	
<% }else{ %>


<script>
	location.href="commons/login";
</script>
	
<% } %>