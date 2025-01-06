<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Form 양식 처리하기</h1>
	<form action="" method="post">
		text : <input type="text" name="txt" /><br/>
		password : <input type="password" name="pwd" /><br/>
		email : <input type="email" name="email"/><br/>
		date : <input type="date" name="date" /><br/>
		radio : a<input type="radio" name="radio" value="a" >&nbsp;
		        b<input type="radio" name="radio" value="b" /><br/> 
		checkbox : 1<input type="checkbox" name="checkBox" value="1" />&nbsp;
				   2<input type="checkbox" name="checkBox" value="2" />&nbsp;
				   3<input type="checkbox" name="checkBox" value="3" />&nbsp;
				   4<input type="checkbox" name="checkBox" value="4" /><br/>
		select : <select name="select" >
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				 </select><br/>
		textarea : <textarea name="textArea" rows="10" cols="20"></textarea>
		
		<br/>
		<input type="submit" value="전송" >
	</form>
</body>
</html>









