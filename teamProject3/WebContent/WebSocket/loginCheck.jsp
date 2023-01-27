<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	

	<%
    
    	
	
		// 로그인폼에서 입력한 id, pw 받기
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		
		// DB접속
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "hr";
		String password = "hr";
		
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
	
    	String sql = "SELECT * FROM members WHERE id = ?";
    	
    	Class.forName("oracle.jdbc.driver.OracleDriver");
		
    	conn = DriverManager.getConnection(url, user, password);
		
		
    	// 쿼리문 수행
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
			
			
			if(rs.next()){
				
				if(pw.equals(rs.getString("password"))){		// Members DB에 저장되어 있는 pw와 로그인폼에 입력한 pw가 일치하면
					
					String chatName = rs.getString("username");  // 해당 id의 username을 chatName에 저장
					
				%>
					<script>
					var chatName = "<%=chatName%>";   <%-- JS로 chatName 받고 chat.jsp페이지 열어주면서 chatName함께전송 --%>
					window.open("Chat.jsp?chatName=" + chatName,"","width=500, height=650");
					location.href= "index.jsp"; // 로그인 완료되면 다시 index.jsp로 페이지전환
					</script>
				<% 
				}
		
			}
    %>
    		<!-- 만약 pw가 일치하지 않느다면 경고창 띄우고 index.jsp로 페이지전환 -->
    		<script>
    			alert("아이디 또는 비밀번호가 일치하지 않습니다."); 
    			location.href= "index.jsp";
    		</script>

</body>
</html>