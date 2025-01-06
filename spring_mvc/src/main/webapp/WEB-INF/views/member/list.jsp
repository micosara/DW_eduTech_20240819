<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.spring.dto.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<head>
    
<style>
	table th,td{
		text-align:center;
		padding:5px;
	}
</style>

</head>    
<body class="hold-transition sidebar-mini">
<div class="wrapper">
<!-- Content Wrapper. Contains page content -->    

	 <!-- Main content -->
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원목록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>회원관리
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	목록
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	 
	 
   	<section class="content">
   		<div class="card">
   			<div class="card-header with-border">
   				<button type="button" class="btn btn-primary" onclick="OpenWindow('regist','회원등록',700,900);" >회원등록</button>   				
   				<div id="keyword" class="card-tools" style="width:550px;">
   					 <div class="input-group row">
   					 	<!-- search bar -->
   					 	<!-- sort num -->
					  	<select class="form-control col-md-3" name="perPageNum" id="perPageNum" onchange="">					  		  		
					  		<option value="10"  ${pageMaker.perPageNum eq 10 ? 'selected':'' } >정렬개수</option>
					  		<option value="2" ${pageMaker.perPageNum eq 2 ? 'selected':'' } >2개씩 정렬</option>
					  		<option value="3" ${pageMaker.perPageNum eq 3 ? 'selected':'' } >3개씩 정렬</option>
					  	</select>
					  	
					  	<!-- search bar -->
					 	<select class="form-control col-md-3" name="searchType" id="searchType">
					 		<option value=""  >검색구분</option>
					 		<option value="i" ${pageMaker.searchType eq 'i' ? 'selected':'' } >아이디</option>
							<option value="n" ${pageMaker.searchType eq 'n' ? 'selected':'' }>이 름</option>
							<option value="p" ${pageMaker.searchType eq 'p' ? 'selected':'' }>전화번호</option>
							<option value="e" ${pageMaker.searchType eq 'e' ? 'selected':'' }>이메일</option>			 									
						</select>
						<!-- keyword -->
   					 	<input  class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${pageMaker.keyword }"/>
						<span class="input-group-append">
							<button class="btn btn-primary" type="button" onclick="search_list(1);" id="searchBtn" data-card-widget="search" >
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					<!-- end : search bar -->		 
   					 </div>
   				</div>   			
   			</div>
   			<div class="card-body" >    		
		    		<table class="table table-bordered table-striped" id="memberTable">
		    			<thead>
			    			<tr>
			    				<th style="width:10%;">사진</th>
			                	<th style="width:15%;">아이디</th>
			                	<th style="width:15%;">패스워드</th>
			                	<th style="width:15%;">이 름</th>
			                	<th style="width:15%;">이메일</th>
			                	<th style="width:15%;">전화번호</th>
			                	<th>등록날짜</th> <!-- yyyy-MM-dd  -->
			               	</tr>
		               	</thead>
		               <%-- 	
		                <% List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList"); %>
		               	<% for(MemberVO member : memberList){ %>               		
		               	
		               	<tr>
		               		<td></td>
		               		<td><%=member.getId() %></td>
		               		<td><%=member.getPwd() %></td>
		               		<td><%=member.getName() %></td>
		               		<td><%=member.getEmail() %></td>
		               		<td><%=member.getPhone() %></td>
		               		<td><%=new SimpleDateFormat("yyyy-MM-dd").format(member.getRegDate()) %></td>
		               	</tr>
		               			               
			            <%	} %> 
			            
			            --%>
			            
			           	<tbody>		            
			            <c:if test="${empty memberList }">
			            	<tr>
			            		<td  colspan="7" class="text-center">해당 내용이 없습니다.</td>
			            	</tr>
			            </c:if>
			            
			            <c:if test="${not empty memberList}">
				            <c:forEach var="member" items="${memberList }">
				         		<fmt:formatDate var="regDate" value="${member.regDate }" pattern="yyyy-MM-dd"/>
				            	<tr onclick="OpenWindow('detail?id=${member.id}','회원정보',700,800)" style="cursor:pointer;">
				            		<td><span class="person-info" data-id="${member.id }" ></span></td>
				            		<td>${member.id }</td>
				            		<td>${member.pwd }</td>
				            		<td>${member.name }</td>
				            		<td>${member.email }</td>
				            		<td>${member.phone }</td>
				            		<td>${regDate }</td>
				            	</tr>
				            
				            </c:forEach>
			            </c:if>
			           </tbody>
		            </table>
    		    
    		</div> <!-- card-body -->
    		<div class="card-footer">
    			<!-- pagination -->
    			<%@ include file="/WEB-INF/views/module/pagination.jsp" %>
    		</div>
	     </div>
   	</section>

</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/module/dataTable_js.jsp" %>


<script>
	$('#memberTable').DataTable({
		"paging": false,
	    "searching": false,
	    "ordering": false,
	    "info": false,
    	"responsive": true, 
    	"lengthChange": true, 
    	"autoWidth": false,
    	"buttons": ["copy", {
    		extend: 'csv',
            charset: 'utf-8',
            bom: true
    	}, "excel", "pdf", "print"]
  	}).buttons().container().appendTo('#memberTable_wrapper .col-md-6:eq(0)');

	$('#memberTable td').css({
								"padding":"5px",
								"line-height":"40px"
							});
</script>
<script>
MemberPictureThumb('<%=request.getContextPath()%>');

</script>

</body>









    
