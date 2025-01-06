<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/template/header.jsp"  %>
		
		<figure>
			<video src="<%=request.getContextPath() %>/resources/images/visual.mp4" autoplay muted loop></video>
			 <div class="inner">
	            <h1>INNOVATION</h1>
	            <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. <br>
	                Id praesentium molestias similique quaerat magni facere in a? Adipisci, possimus reprehenderit!</p>
	            <a href="#">view detail</a>
       		</div>
		</figure>
		
		<section>
			<div class="inner">
				 <h1>RECENT NEWS</h1>
				  <div class="wrap">
				  	<article>
				  		<div class="pic">
                        	<img src="<%=request.getContextPath() %>/resources/images/news1.jpg" 
                        		 alt="1번째 콘텐츠 이미지">
                    	</div>
                    	
                    	<h2><a href="#">Lorem ipsum dolor sit.</a></h2>
                  		<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vitae minus, eaque</p>
				  	</article>
				  	<article>
				  		<div class="pic">
                        	<img src="<%=request.getContextPath() %>/resources/images/news2.jpg" 
                        		 alt="1번째 콘텐츠 이미지">
                    	</div>
                    	
                    	<h2><a href="#">Lorem ipsum dolor sit.</a></h2>
                  		<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vitae minus, eaque</p>
				  	</article>
				  	<article>
				  		<div class="pic">
                        	<img src="<%=request.getContextPath() %>/resources/images/news3.jpg" 
                        		 alt="1번째 콘텐츠 이미지">
                    	</div>
                    	
                    	<h2><a href="#">Lorem ipsum dolor sit.</a></h2>
                  		<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vitae minus, eaque</p>
				  	</article>
				  	<article>
				  		<div class="pic">
                        	<img src="<%=request.getContextPath() %>/resources/images/news4.jpg" 
                        		 alt="1번째 콘텐츠 이미지">
                    	</div>
                    	
                    	<h2><a href="#">Lorem ipsum dolor sit.</a></h2>
                  		<p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vitae minus, eaque</p>
				  	</article>
				  </div>
			</div>
		</section>
		
	
<%@ include file="/WEB-INF/template/footer.jsp"  %>





