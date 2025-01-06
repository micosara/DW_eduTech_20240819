<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>지역선택</h1>
	
	<select name="si" onchange="find_gu();">
		<option>시도를 선택하세요.</option>
		<c:forEach items="${listSi }" var="si">		
		<option value="${si }">${si }</option>
		</c:forEach>
	</select>
	
	<script>
		function find_gu(){
			let si = document.querySelector("select[name='si']");
			//alert(si.value);	
			
			fetch("findGu?si="+si.value)
			.then((response)=>response.json())
			.then((data)=>{
				
				if(document.querySelector("select[name='gu']")){
					document.querySelector("select[name='gu']").remove();	
				};
				
				let select = document.createElement("select");
				select.name = "gu";
				let option = document.createElement("option");
				option.text = "구/군을 선택하세요.";
				select.append(option);
				
				select.onchange=function(){findDong(select.value)};
				for(let gu of data){
					//alert(gu);					
					let option = document.createElement("option");
					option.value=option.text=gu;
					select.append(option);
				}
				
				//document.body.appendChild(select);
				si.after(select);
			});
		}
		
		function findDong(gu){
			//alert("findDong:"+gu);			
			
			if(document.querySelector("select[name='dong']")){
				document.querySelector("select[name='dong']").remove();	
			};		
			
			let select = document.createElement("select");
			select.name="dong";
			let option = document.createElement("option");
			option.text = "동/면/읍 선택하세요.";
			
			select.append(option);
			select.onchange=function(){
				weather_go(select.value);
			}
			
			fetch("findDong?gu="+gu)
			.then((response)=>response.json())
			.then((data)=>{
				for(let dong of data){
					let option = document.createElement("option");
					option.value=dong.district_code;
					option.text=dong.dong;
					select.append(option);
				}
			});
			
			document.body.appendChild(select);
		}
		
		function weather_go(code){
			//alert(code);
			fetch("weather?district_code="+code)
			.then((response)=>response.json())
			.then((data)=>console.log(data));
			
		}
	</script>
</body>
</html>


