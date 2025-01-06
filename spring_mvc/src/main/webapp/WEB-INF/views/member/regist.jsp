<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<script src="<%=request.getContextPath()%>/resources/js/regist.js"></script>
</head>

<body>
	<!-- Content Wrapper. Contains page content -->
	<div class="">

		<section class="content-header">
			<div class="container-fluid">
				<div class="row md-2">
					<div class="col-sm-6">
						<h1>회원등록</h1>
					</div>
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="#"> <i
									class="fa fa-dashboard">회원관리</i>
							</a></li>
							<li class="breadcrumb-item active">등록</li>
						</ol>
					</div>
				</div>
			</div>
		</section>
		<!-- Main content -->
		<section class="content register-page">
			<div class="">
				<div class="login-logo">
					<a href=""><b>회원 등록</b></a>
				</div>
				<!-- form start -->
				<div class="card">
					<div class="register-card-body">
						<form name="regist" class="form-horizontal" action="regist"
							method="post" enctype="multipart/form-data">
							<div class="input-group mb-3">
								<div class="mailbox-attachment-icon has-img" id="pictureView"
									style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>
								<div class="mailbox-attachment-info" style="width: 100%;">
									<div class="input-group input-group-sm">
										<label for="inputFile"
											class=" btn btn-warning btn-sm btn-flat input-group-addon">파일선택</label>
										<input id="inputFileName" class="form-control" type="text"
											name="tempPicture" disabled /> <input type="file"
											id="inputFile" name="picture" style="display: none;"
											onchange="picture_go();" />
									</div>
								</div>
								<br />
							</div>
							<div class="form-group row">
								<label for="id" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>아이디
								</label>
								<div class="col-sm-9 input-group input-group-sm">
									<input name="id"
										onblur="validation('id');"
										onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);"
										type="text" class="form-control" id="id"
										placeholder="13글자 영문자,숫자 조합"> <span
										class="input-group-append-sm">
										<button type="button" onclick="idCheck_go();"
											class="btn btn-info btn-sm btn-append">중복확인</button>
									</span>
								</div>
							</div>
							<div class="form-group row">
								<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>패스워드
								</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="pwd" type="password"
										class="form-control" id="pwd"
										placeholder="20글자 영문자,숫자,특수문자 조합"
										onblur="validation(this.name);" />
								</div>

							</div>
							<div class="form-group row">
								<label for="name" class="col-sm-3" style="font-size: 0.9em;">
									<span style="color: red; font-weight: bold;">*</span>이 름
								</label>
								<div class="col-sm-9 input-group-sm">
									<input class="form-control" name="name" type="text"
										class="form-control" id="name" placeholder="이름을 입력하세요"
										onblur="validation(this.name);"
										onkeyup="this.value=this.value.trim();" />
								</div>

							</div>
							<div class="form-group row">
								<label for="authority" class="col-sm-3"
									style="font-size: 0.9em;">직책권한</label>
								<div class="col-sm-9 row">
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="role1" name="authorities" value="ROLE_USER"> <label
												for="role1" class="custom-control-label">사용자</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="role2" name="authorities" value="ROLE_MANAGER"> <label
												for="role2" class="custom-control-label">운영자</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="role3" name="authorities" value="ROLE_ADMIN"> <label
												for="role3" class="custom-control-label">관리자</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="authority" class="col-sm-3"
									style="font-size: 0.9em;">회원관리권한</label>
								<div class="col-sm-9 row">
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="user1" name="authorities" value="ROLE_USER_CREATE"> <label
												for="user1" class="custom-control-label">등록</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="user2" name="authorities" value="ROLE_USER_MODIFY"> <label
												for="user2" class="custom-control-label">수정</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="user3" name="authorities" value="ROLE_USER_REMOVE"> <label
												for="user3" class="custom-control-label">삭제</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="authority" class="col-sm-3"
									style="font-size: 0.9em;">공지관리권한</label>
								<div class="col-sm-9 row">
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="notice1" name="authorities" value="ROLE_NOTICE_CREATE"> <label
												for="notice1" class="custom-control-label">등록</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="notice2" name="authorities" value="ROLE_NOTICE_MODIFY"> <label
												for="notice2" class="custom-control-label">수정</label>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="custom-control custom-checkbox">
											<input class="custom-control-input" type="checkbox"
												id="notice3" name="authorities" value="ROLE_NOTICE_REMOVE"> <label
												for="notice3" class="custom-control-label">삭제</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="email" class="col-sm-3" style="font-size: 0.9em;">이메일</label>
								<div class="col-sm-9 input-group-sm">
									<input name="email" type="email" class="form-control"
										id="email" placeholder="example@naver.com"
										onblur="validation('email');">
								</div>
							</div>
							<div class="form-group row">
								<label for="phone" class="col-sm-3 control-label">전화번호</label>
								<div class="col-sm-9">
									<div class="input-group-sm">
										<select style="width: 90px;" name="phone" id="phone"
											class="form-control float-left">
											<option value="">-선택-</option>
											<option value="010">010</option>
											<option value="011">011</option>
											<option value="017">017</option>
											<option value="018">018</option>
										</select> <label class="float-left"
											style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>
										<input style="width: 87px;" data-role="phone1"
											oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
											onkeyup="phone1_max(this.value.length);" maxlength="4"
											name="phone" type="text" class="form-control float-left" />
										<label class="float-left"
											style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>
										<input style="width: 87px;" maxlength="4" data-role="phone2"
											name="phone" type="text" class="form-control float-left"
											onfocus="phone2();" />
									</div>
								</div>
							</div>

							<div class="card-footer">
								<div class="row">
									<div class="col-sm-6">
										<button type="button" id="registBtn" onclick="regist_go();"
											class="btn btn-info">등&nbsp;&nbsp;록</button>
									</div>

									<div class="col-sm-6">
										<button type="button" id="cancelBtn" onclick="CloseWindow();"
											class="btn btn-default float-right">취 &nbsp;&nbsp;소</button>
									</div>

								</div>
							</div>
						</form>
					</div>
					<!-- register-card-body -->
				</div>
			</div>
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

	
	<script>

	$('input[name="phone"]').focus(function(){		
		if(!$('select[name=phone]').val()){
			alert("국번을 먼저 선택하세요.");
			$('select[name=phone]').focus();
		}
	});

	function phone2(){
		let phone1 = $('input[data-role="phone1"]');		
		if($('select[name="phone"]').val() &&  phone1.val().length < 4 ){
			alert("전화번호 앞자리 4개를 작성하세요.");
			phone1.focus();
		}
	}
	
	function phone1_max(count){
		//alert(count);
		if(count==4) $('input[data-role="phone2"]').focus();
	}

	
	function regist_go(){
		let form = document.forms.regist;
		for(let element of form ){
			//alert(element.name);
			switch(element.name){
				case "id":case "pwd":case "email":case "phone":case "name":case "picture":
					if(!element.value){
						alert(element.name+"은 필수입니다.");					
						if(element.name=="picture"){
							element.click();
						}else{
							element.focus();
						}					
						return;
					}
					
					if(element.name=="id" && element.value!=checkID){
						alert("아이디 중복확인은 필수입니다.");
						return;
					}
			}
		}		
		
		form.action="regist";
		form.method="post";
		form.submit();
		
	}

</script>

</body>

