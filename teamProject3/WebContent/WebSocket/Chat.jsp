<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloTalk</title>
<link rel="stylesheet" href="css/style.css" />


<script>

var webSocket = new WebSocket("ws://localhost:8080" + "<%= request.getContextPath() %>/ChatingServer"  ); 
var chatWindow, chatMessage, chatName;


// 상태바 시간표시
function printTime(){
	var clock = document.getElementById("clock"); 
	
	var time = new Date();
	
	var hours = time.getHours();
	var minutes = time.getMinutes();
	
	if(minutes < 10){
		minutes = "0" + minutes;
	}
	
	var currentTime = hours + ":" + minutes;
	
	clock.innerHTML = currentTime;
	setTimeout("printTime()", 1000);
}




// 채팅창이 열리면 대화창, 메시지 입력창, 대화명 표시란으로 사용할 DOM 객체 저장
window.onload = function() {
    chatWindow = document.getElementById("chatWindow");
    chatMessage = document.getElementById("chatMessage");
    chatName = document.getElementById('chatName').value;
    printTime();
}

    
    
// 메시지 전송
function sendMessage() {
    // 대화창에 표시
    
    
    chatWindow.innerHTML += "<div class='myChat'><div class='myMsg'><span>" + chatMessage.value + "</span></div></div>"
    webSocket.send(chatName + '|' + chatMessage.value);  // 서버로 전송
    chatMessage.value = "";  // 메시지 입력창 내용 지우기
    chatWindow.scrollTop = chatWindow.scrollHeight;  // 대화창 스크롤
}

// 서버와의 연결 종료
function disconnect() {
    webSocket.close();
}

// 엔터 키 입력 처리
function enterKey() {
    if (window.event.keyCode == 13) {  // 13은 'Enter' 키의 코드값
        sendMessage();
    }
}

// 웹소켓 서버에 연결됐을 때 실행
webSocket.onopen = function(event) {
    chatWindow.innerHTML += "<div class='noticeChat'><div class='noticeMsg'><span>웹소켓 서버에 연결되었습니다.</span></div></div>";
};

// 웹소켓이 닫혔을 때(서버와의 연결이 끊겼을 때) 실행
webSocket.onclose = function(event) {
    chatWindow.innerHTML += "<div class='noticeChat'><div class='noticeMsg'><span>웹소켓 서버가 종료되었습니다.</span></div></div>";
};

// 에러 발생 시 실행
webSocket.onerror = function(event) { 
    alert(event.data);
    chatWindow.innerHTML += "<div class='noticeChat'><div class='noticeMsg'><span>채팅 중 에러가 발생하였습니다.</span></div></div>";
}; 

// 메시지를 받았을 때 실행
webSocket.onmessage = function(event) { 
    var message = event.data.split("|");  // 대화명과 메시지 분리
    var sender = message[0];   // 보낸 사람의 대화명
    var content = message[1];  // 메시지 내용
    if (content != "") {
        if (content.match("/")) {  // 귓속말
            if (content.match(("/" + chatName))) {  // 나에게 보낸 메시지만 출력
                var temp = content.replace(("/" + chatName), "[귓속말] : ");
                chatWindow.innerHTML += "<div class='whisperChat'><div class='whisperMsg'><span>" + sender + "</span><span>" + temp + "</span></div><div>";
            }
        }
        else {  // 일반 대화
            chatWindow.innerHTML += "<div class='otherPersonChat'><div class='otherPersonMsg'><span>" + sender + "</span> : <span>" + content + "</span></div></div>";
        	// chatWindow.innerHTML += "<div class='otherPersonChat'><div class='otherPersonName'><span>" + sender + "</span></div><div class='otherPersonMsg'><span>" + content + "</span><div></div>";
        
        }
    }
    
    chatWindow.scrollTop = chatWindow.scrollHeight; 
};
				
			
</script>

</head>
<body>

<body class="chatScreen">  <!-- 대화창 UI 구조 정의 --> 

		

	<!-- 상단 상태바, 대화명, 종료버튼 -->
	<header class="chatHeader">
		
		<!-- 상단 상태바 -->
		<div class="statusBar" id="statusBar">
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
    	
    	<div class="chatBlock"></div>
    
    	<!-- 대화명, 종료버튼 -->
		<div class="chatHeaderElement"> 
   	 		<div>											<!-- 여기서 chatName받고 채팅창 상단에 출력 -->
   	 			<span>Name :</span><input type="text" id="chatName" value="${param.chatName}" readonly />
    		</div>
    		<button id="closeBtn" onclick="disconnect();" class="closeBtn">
    			<a href="index.jsp">Finish</a>  <!-- finish버튼누르면 채팅종료시키면서 index.jsp로 페이지 전환 -->
    		</button>
    	</div>
    </header>
    
    <!-- 메인채팅창 -->
    <main class="chatScreenMain">
    	<div>
          	<div id="chatWindow"></div>
      	</div>
    </main>
	
    
    <!-- 하단채팅입력창 -->
    <div class="chatFooter">
    	<div class="chatFooterElement">
    		<button class="fileUploadBtn">
    			<i class="fa-sharp fa-solid fa-circle-plus fa-2x"></i> <!-- +아이콘 -->
    		</button>
        	<input type="text" id="chatMessage" class="chatMessage" onkeyup="enterKey();" placeholder="Write a message..."> <!-- 메세지입력창 -->
        	<button id="sendBtn" class="sendBtn" onclick="sendMessage();"> <!-- 전송버튼 -->
				<i class="fa-solid fa-arrow-up"></i> <!-- 화살표 아이콘 -->
			</button>
        </div>
    </div>
    
     <script
      src="https://kit.fontawesome.com/bd982df679.js"
      crossorigin="anonymous"
    ></script>
	
</body>

</body>
</html>