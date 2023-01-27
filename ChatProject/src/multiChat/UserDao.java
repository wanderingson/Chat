package multiChat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDao {

		private Connection con;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		public UserDao() throws ClassNotFoundException, SQLException {
			con = new DBConn().getConnection();
		}
		
		// 회원가입 입력 메소드
		public int insert_ud(UserData ud) {
			
			String sql = "insert into UserContent"
					+ " values(?,?,?)";
			
			int cnt = 0;
			
			try {
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ud.getId());
				pstmt.setString(2, ud.getPw());
				pstmt.setString(3, ud.getName());
				cnt = pstmt.executeUpdate(); // executeUpdate() : 처리된 row의 개수를 반환
				
				if(cnt == 0) { // cnt가 0 이면 값이 입력이 안된거
					return cnt;
				}else {
					return cnt; 
				}
				
			} catch (SQLException e) {
				System.out.println("insert error");
			}
			return cnt;
			
		}
				
		
		// select문
		public int select_id(String id) throws SQLException {
			String sql = "select pw"
					+ " from UserContent"
					+ " where ID =?";
			int cnt=0;
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 반환값이 있다면 cnt 증가로 1 반환
				cnt++;
			}
			
			return cnt;
			
			
		} // select-end
		
		public int select_pw(String pw) throws SQLException {
			String sql = "select ID"
					+ " from UserContent"
					+ " where PW =?";
			int cnt=0;
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 반환값이 있다면 cnt 증가로 1 반환
				cnt++;
			}
			
			return cnt;
			
			
		} // select-end
		
		public int select_name(String name) throws SQLException {
			String sql = "select PW"
					+ " from UserContent"
					+ " where NAME =?";
			int cnt=0;
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 반환값이 있다면 cnt 증가로 1 반환
				cnt++;
			}
			
			return cnt;
			
			
		} // select-end
		
		// update문
		public boolean update_tel(String tel1, String tel2) {
			                  // 기존번호    , 변경번호
			String sql = "update cafemem"
					+ " set memtel = ?"
					+ " where memtel = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel2); // 변경번호
				pstmt.setString(2, tel1); // 기존번호
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("update error");
				return false;
			}
			return true;

		}
		
		// DB에 채팅내용 저장하기
	      public int insertContents(String Message) {
	         String[] messageArr = Message.split(":"); // ex) Message =  "홍길동 : 안녕\n" 형식

	         String userName = messageArr[0].trim();
	         String contents = messageArr[1].split("\n")[0];
	
	         String sql = "insert into ChatContents values (?,?, sysdate)";
	      
	         int cnt = 0; 
	         
	         try {
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, userName);
	            pstmt.setString(2, contents);
	          
	            cnt = pstmt.executeUpdate(); // executeUpdate() : 처리된 row의 개수를 반환
	            
	            if(cnt == 1)  // cnt가 0 이면 값이 입력이 안된거
	               return cnt;
	            	
	            
	         } catch (SQLException e) {
	            //e.printStackTrace();
	            System.out.println("insertContents error");
	         }
	         return cnt;
	      }
	      
	      // DB에서 ChatContents 테이블 데이터 읽어서 List<String> rcvContents에 넣어준 후 rcvContents 반환해주기
	      public List<String> selectContents() throws SQLException, IOException {
	    	 String sql = "select UserName, Contents, to_char(chatTime,'YY/MM/DD HH24:MI:SS') from ChatContents order by 3";
	         
	         pstmt = con.prepareStatement(sql);
	         rs = pstmt.executeQuery();

	         List<String> rcvContents = new ArrayList<>();
	         
	         while(rs.next()) {
	            String userName = rs.getString("UserName");
	            String contents = rs.getString("Contents");
	            String chatTime = rs.getString(3);
	            
	            System.out.println(chatTime);
	            
	            String str = userName + " : " + contents + " - " + chatTime + "\n";
	            System.out.println(str);
	            rcvContents.add(str);
	         }
	         return rcvContents;
	      }
	    
		

		
}
