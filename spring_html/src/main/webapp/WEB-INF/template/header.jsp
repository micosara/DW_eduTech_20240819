<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<title>기업 페이지</title>
		
		<link rel="icon" href="favicon.ico" type="image/x-icon">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/style.css">
	
	</head>
	<body>
		<header>
			<div class="inner">
				<h1><a href="<%=request.getContextPath()%>/main">DCODLAB</a></h1>
				
				<ul id="gnb">
	                <li><a href="javascript:go_menu('<%=request.getContextPath() %>','department');">DEPARTMENT</a></li>
	                <li><a href="javascript:go_menu('<%=request.getContextPath() %>','gallery');">GALLERY</a></li>
	                <li><a href="javascript:go_menu('<%=request.getContextPath() %>','youtube');">YOUTUBE</a></li>
	                <li><a href="javascript:go_menu('<%=request.getContextPath() %>','community');">COMMUNITY</a></li>
	                <li><a href="javascript:go_menu('<%=request.getContextPath() %>','location');">LOCATION</a></li>
	            </ul>
	            <ul class="util">
	                <li><a href="#">Contact</a></li>
	                <li><a href="#">Help</a></li>
	                <li><a href="#">Login</a></li>
	                <li><a href="#">Join</a></li>
	                <li><a href="#">Sitemap</a></li>
	            </ul>
			</div>		
		</header>