package t1_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test3DAO {
	Connection conn = null; // 1. 커넥션 객체 만들기
	
	// 2. 생성자 만들기
	public Test3DAO() { //3. 드라이버 찾기
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// 4. 찾아야할 url,user,password 변수선언
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password = "1234";
			// 5. 커넥션객체를 이용하여 DB 연결하기
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 실패~~"+e.getMessage());
		}
	}
	
	// 6. 커넥션 닫기 위한 메소드 생성
	public void connClose() {
		try {
			conn.close();
			System.out.println("DB 연결 종료!!");
		} catch (SQLException e) {}
	}
}
