<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloTalk</title>
<link rel="stylesheet" href="css/style.css" />

<script>

	// 상태바 현재시간
	function printTime(){
		var clock = document.getElementById("clock"); 
		
		var time = new Date();
		
		var hours = time.getHours();
		var minutes = time.getMinutes();
		
		var currentTime = hours + ":" + minutes;
		
		if(minutes < 10){ // 한자리일 경우만 앞에 0붙여서 출력
			minutes = "0" + minutes;
		}
		
		clock.innerHTML = currentTime;
		setTimeout("printTime()", 1000);
	}
	
	window.onload=function(){
		printTime();
	}
</script>

</head>
<body>

	<!-- 여긴 클라이언트 -->


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

	
	<!-- 홈페이지 메인 문구 -->
	<main class="welcome">
      <span class="welcome__big-span">Welcome to HelloTalk</span>
      <p class="welcome__small-span">
        If you have a HelloTalk account, log in with your information
      </p>


	<!-- 로그인 입력폼 -->			
      <form action="loginCheck.jsp" method="get" class="login-form">
        <input name="id" type="text" placeholder="ID" required id="chatId"/>
        <input name="password" type="password" placeholder="PW" required id="chatpw" />
        <input type="submit" value="Log In" />
        <a href="joinForm.jsp">New here? Sign Up!</a>
      </form>
    </main>
    
	
	<script
      src="https://kit.fontawesome.com/bd982df679.js"
      crossorigin="anonymous"
    ></script>
</body>
</html>