package t2_CRUD;
// 1.sql 2.VO 3.DAO(데이터베이스연결)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoewonDAO2 {
	private Connection conn = null;
	
	public HoewonDAO2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password = "1234";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패"+e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패"+e.getMessage());
		}
	}
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// 전체 회원 조회
	public void getList() {
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("번호 : "+ rs.getInt("idx"));
				System.out.println("성명 : "+ rs.getString("name"));
				System.out.println("나이 : "+ rs.getInt("age"));
				System.out.println("성별 : "+ rs.getString("gender"));
				System.out.println("주소 : "+ rs.getString("address"));	
				System.out.println();
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		}
	}
	// Connection conn => 서버에 접속해서 데이터베이스를 찾음 (아이디,패스워드 이용)
	// Statement stmt => sql 테이블 사용하기 위한 명령어 관리 (어떤 데이터베이스에 연결을..? -> Connection객체에 의해서 만들어짐)
	// ResultSet rs => sql 명령에 의해서 가져온 자료를 넘겨받아서 담아놓고 제어하는 객체
	// 저장, 수정 등에는 rs가 필요없음, ResultSet : select에서만 이용
}
