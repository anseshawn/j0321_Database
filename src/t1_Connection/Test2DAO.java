package t1_Connection;

// import java.sql.*; 로 사용하기도 함
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// DAO : Database Access Object
// 데이터베이스 관련된 사항 담기
public class Test2DAO {
	Connection conn = null; // 중괄호 여러 블록 안에서 사용하기 위해 처음에 선언
	
	// 생성하면서 1번부터 체크...
	public Test2DAO() {
		try {
			// 1. (JDBC 드라이버가 있어야 함) JDBC 드라이버 검색
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 검색 성공!!!");
			
			// 2. DB서버에 연결(연동) 후 데이터베이스 사용 준비 작업
			// mysql 커넥터가 설치되어 있기 때문에 그 안에서 DriverManager 클래스 찾기
			String url = "jdbc:mysql://localhost:3306/javaclass"; // jdbc:사용하고있는프로그램://(프로토콜에는://항상존재)접속계정(ip):3306/데이터베이스이름
			String user = "atom";
			String password = "1234";
			// type은 java.sql로... 드라이버를 변수 선언해서 종료시에 커넥션 끊어주기
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 연결 성공!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패~~~"); // 실패시 - url 확인, 계정을 받았는지, 비밀번호 확인
		} finally {
//			try { ==> 생성자에서 닫으면 생성자가 열자마자 닫아버림(작업할시간x), 메소드로 빼서 수행하기
//				System.out.println("데이터베이스 연결 끊기 성공!!!");
//				conn.close();
//			} catch (SQLException e) {}
			connClose();
			System.out.println("작업끝!!");
		}
	}
	// 접속 -> URL 찾기 -> 데이터 찾기
	
	// conn 객체 close
	public void connClose() {
		try {
			conn.close();
			System.out.println("데이터베이스 연결 끊기 성공!!!");
		} catch (SQLException e) {}
	}
	// io(input output)에 관련 된 사항은 가비지컬렉터가 닫아주지 못하기 때문에 내가 닫아줘야함
	// 예: sc.close();
	
}
