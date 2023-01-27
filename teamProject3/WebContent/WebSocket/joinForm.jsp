<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" href="css/style.css" />
<style>
.statusBar{
	
	background-color: white;

}

.loginForm{
	
	display:flex;
	justify-content: center;
	align-items: center;
	width:100%;
	
}


.loginFormScreen{
	margin-top : 90px;
	width:40%;
	padding:50px;
	border: 2px solid #fae100;
	margin-bottom: 30px;
	
}

.joinBox{
	
	margin-top:60px;
	width:100%;
	display: flex;
	justify-content: center;
	align-items: center;

}

.welcomeMessage{
	
	display:flex;
	justify-content:center;
	align-items:center;
	flex-direction:column;
	width:100%;

}

.welcomeMessage > span{

 	font-size: 55px;
 	font-weight: 650;
 	margin-bottom: 20px;

}

.joinBox > form{

	display:flex;
	flex-direction: column;

}

.joinBox > form > input{

	margin: 10px 0;
	width:200px;
	height: 25px;
	border:none;

}

.joinBox > form > input{

	border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}


.joinBox > form > input:focus{
	border-bottom-color: #fae100;
}


.joinBox > form > div{
	margin : 10px 0;
}

.signUpButton{

	background-color: #fae100;
	border:none;
	border-radius: 5px;
	padding: 10px;
}

.signUpButton:hover{
	cursor:pointer;
}

.cancelButton{
	
	width:67px;
	height: 35px;
	border-radius: 5px;
	background-color:#fae100;
}


</style>

	<script>
	// 상태바 시간표시
	function printTime(){
		
		var clock = document.getElementById("clock"); 
		
		var time = new Date();
		
		var hours = time.getHours();
		var minutes = time.getMinutes();
		
		var currentTime = hours + ":" + minutes;
		
		if(minutes < 10){
			minutes = "0" + minutes;
		}
		
		clock.innerHTML = currentTime;
		setTimeout("printTime()", 1000);
		}
	
		window.onload=function(){
		printTime();
		
		}
	</script>
	<script>
	
		function checkInfo(){ // 아이디, 비밀번호, 비밀번호 확인, 이름이 입력되었는지 확인하는 checkInfo함수
			
			
			if(!document.userInfo.id.value){
				alert("아이디를 입력하세요")
				return false;
			}
			
			if(!document.userInfo.password.value){
				alert("비밀번호를 입력하세요")
				return false;
			}
			
			if(document.userInfo.password.value != document.userInfo.passwordCheck.value){ // 비밀번호와 비밀번호확인 불일치시
				alert("동일한 비밀번호를 입력하세요");
				return false;
			}
			
			if(!document.userInfo.username.value){
				alert("이름을 입력하세요");
				return false;
				
			}
			// form 태그의 onsubmit에 의해 실행, submit버튼 클릭시 실행되며 false return시 submit처리가 되지않는다.
		}
	
	</script>


</head>
<body class="loginForm">

	<!-- 상단 상태바 -->
	<div class="statusBar">
      <div class="statusBarLeft">
        <span>Service</span>
        <i class="fa-solid fa-wifi fa-lg"></i>
      </div>
      <div class="statusBarCenter">
       	<span id="clock"></span>
      </div>
      <div class="statusBarRight">
        <span>80%</span>
        <i class="fa-solid fa-battery-three-quarters fa-lg"></i>
      </div>
    </div>

	<main class="loginFormScreen">
		<!-- 페이지 문구 -->
		<div class="welcomeMessage">
			<span>Hello, Friend!</span>
			<p>Enter your personal details and start journey with us</p>
		</div>

		<div class="joinBox">	<!-- 회원가입 폼 -->						<!-- onsubmit으로 checkInfo()실행 -->
			<form method="get" action="joinCheck.jsp" name="userInfo" onsubmit="return checkInfo()" >
				
				<input type="text" name="id" maxlength="50" placeholder="User"/>
				<input type="password" name="password" maxlength="50" placeholder="Password"/>
				<input type="password" name="passwordCheck" maxlength="50" placeholder="Comfirm Password"/>
				<input type="text" name="userName" maxlength="50" placeholder="Name" /> 			
				<div>
				<input type="submit" value="Sign Up" class="signUpButton"/>
				<button class="cancelButton"><a href="index.jsp">Cancel</a></button> <!-- 회원가입취소하면 index.jsp로 페이지 전환 -->
				</div>
			</form>
		</div>
	</main>
	
	<script
      src="https://kit.fontawesome.com/bd982df679.js"
      crossorigin="anonymous"
    ></script>

</body>
</html>