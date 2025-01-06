<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

1. Echo message <br/>
<input type="text" name="message" /><button class="btn1">전송</button><br/>
<message>서버로부터 받은 메세지 출력</message>

<hr/>

2. JSON Data<br/>
아이디 :<input type="text" name="id" /><br/>
패스워드 :<input type="text" name="pwd" /><br/>
전화번호:<input type="text" name="phone" /><br/>
이메일 :<input type="text" name="email" /><br/>
<button class="btn2" onclick="json_go()">전송</button>

<hr/>
3. form data<br/>
<form action="form" method="get" onsubmit="return false;">
	아이디 :<input type="text" name="id" /><br/>
	패스워드 :<input type="text" name="pwd" /><br/>
	전화번호:<input type="text" name="phone" /><br/>
	이메일 :<input type="text" name="email" /><br/>
	<button type="submit" class="btn3" onclick="form_go();">전송</button>
</form>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
<script>
	function form_go(){
		//alert("click btn3");
		let form = $('form[action="form"]');
		$.ajax({
			url: "form",
			method: "POST",
			data: form.serialize(),
			success:function(data){
				alert(data);
			}
		});
	}

	function json_go(){
		let data = {
				id: $('input[name="id"]').val(),
				pwd: $('input[name="pwd"]').val(),
				phone: $('input[name="phone"]').val(),
				email: $('input[name="email"]').val()
		};
		
		//console.log(data);
		$.ajax({
			url: "json",
			method:"post",
			data: JSON.stringify(data),
			contentType: "application/json",
			success:function(data){
				console.log(data);
			}
		});
	}
	
	$('button.btn1').click(function(event){
		let inputText = $('input[name="message"]').val();
	
		$.ajax({
			url:'echo?message='+inputText ,
			method: 'get',			
			success:function(data){
				//alert(data);
				$('message').text(data);
			}					
			
		});
		
	});	
</script>

</body>
</html>




