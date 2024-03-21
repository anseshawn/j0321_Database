package t1_Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

// DAO : Database Access Object
// 데이터베이스 관련된 사항 담기
public class Test1DAO {

	// 생성할 때 1번 체크하기
	public Test1DAO() {
		try {
			// 1. (JDBC 드라이버가 있어야 함) JDBC 드라이버 검색
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 검색 성공!!!");
			
			// 2. DB서버에 연결(연동) 후 데이터베이스 사용 준비 작업
			// mysql 커넥터가 설치되어 있기 때문에 그 안에서 DriverManager 클래스 찾기
			String url = "jdbc:mysql://localhost:3306/javaclass"; // jdbc:사용하고있는프로그램://(프로토콜에는://항상존재)접속계정:3306/데이터베이스이름
			String user = "atom";
			String password = "1234";
			DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 연결 성공!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패~~~"); // 실패시 - url 확인, 계정을 받았는지, 비밀번호 확인
		} finally {
			System.out.println("작업끝!!");
		}
	}
	
	// 접속 -> URL 찾기 -> 데이터 찾기
	
}
