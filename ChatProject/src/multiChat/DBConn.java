package multiChat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private Connection con; //접속객체
	
	public Connection getConnection(){//접속객체 getter 작성
		return con;
	}
	
	// 아래는 생성자
	public DBConn() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		
 }




	
}

