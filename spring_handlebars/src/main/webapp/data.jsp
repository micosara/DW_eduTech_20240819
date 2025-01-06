<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Data class</h1>
<div id="output" ></div>


<script src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<script type="text/x-handlebars-template"  id="data" >
{{#each .}}
<ul>
	<li>a:{{a}}</li>
	<li>b:{{b}}</li>
	<li>c:{{c}}</li>
	<li>today:{{prettifyDate today}}</li>
</ul>
{{/each}}
</script>

<script>
	let func = Handlebars.compile(document.querySelector('#data').innerHTML);
	//console.log(func({a:"111",b:"222",c:"333"}));
	
	 Handlebars.registerHelper({
		 "prettifyDate":function(timeValue){ //Handlbars에 날짜출력함수 등록
	         var dateObj=new Date(timeValue);
	         var year=dateObj.getFullYear();
	         var month=dateObj.getMonth()+1;
	         var date=dateObj.getDate();
	         return year+"-"+month+"-"+date;
	      }
	 });
	
	fetch("<%=request.getContextPath()%>/data/list")
	.then((response)=>response.json())
	.then((data)=>{
		document.querySelector('div#output').innerHTML = func(data);
        
		});
</script>




</body>
</html>