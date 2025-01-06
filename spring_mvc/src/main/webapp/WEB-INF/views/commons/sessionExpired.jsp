<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<script>
	
	alert("${message}");
	if(window.opener) {
		window.opener.parent.location.reload();
	}else{
		if(window.parent){
			window.parent.location.reload();			
		}else{
			window.location.reload();		
		}
	}
	window.close();
	
</script>