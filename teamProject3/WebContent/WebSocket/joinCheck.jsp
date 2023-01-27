<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	
		// 회원가입 폼에 입력한 id,pw,userName 받기
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String username = request.getParameter("userName");
		
		
		
		// DB실행
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "hr";
		String password = "hr";
	
		Connection conn1= null;
		PreparedStatement pstmt1= null;
	
		String signUpSql1 = "insert into members values(?, ?, ?)";
		
		
		
		try{
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn1 = DriverManager.getConnection(url, user, password);
			
			// 쿼리문 실행
			pstmt1 = conn1.prepareStatement(signUpSql1);
			pstmt1.setString(1, id);
			pstmt1.setString(2, pw);
			pstmt1.setString(3, username);
			
			int result1= pstmt1.executeUpdate();
			
			
			
			if(result1 == 1){ // 회원가입 완료되면 로그인할 수 있게 index.jsp로
				
				response.sendRedirect("index.jsp");
				
			}else{ // 안되면 다시 회원가입창 초기화
				
				response.sendRedirect("joinCheck.jsp");
				
			}
			
			
			
			
		}catch(Exception e){
			// e.printStackTrace();
		}finally{
			
			try{
				
				if(conn1!= null) conn1.close();
				if(pstmt1 != null) pstmt1.close();
				
			}catch(Exception e){
				// e.printStackTrace();
			}
			
		}
		
		
	%>




</body>
</html>