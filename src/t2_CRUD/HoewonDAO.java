package t2_CRUD;
// 1.sql 2.VO 3.DAO(데이터베이스연결)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoewonDAO {
	private Connection conn = null;
	
	public HoewonDAO() {
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
		// 테이블에 접근하기 위한 준비 작업
		try {
			// Statement : sql명령에 의한 테이블 제어작업 가능
			Statement stmt = conn.createStatement();
			// ResultSet : stmt의 도움을 받아 쿼리(sql명령문)를 실행, 실행한 걸 ResultSet이 담당
			String sql = "select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			
			// 현재 rs 객체가 BOF에 존재... 
			rs.next(); // 레코드포인터를 다음 레코드로 이동
			System.out.println("번호 : "+ rs.getInt("idx"));
			System.out.println("성명 : "+ rs.getString("name"));
			System.out.println("나이 : "+ rs.getInt("age"));
			System.out.println("성별 : "+ rs.getString("gender"));
			System.out.println("주소 : "+ rs.getString("address"));	
			System.out.println();
			
			rs.next(); // 레코드포인터를 다음 레코드로 이동
			System.out.println("번호 : "+ rs.getInt("idx"));
			System.out.println("성명 : "+ rs.getString("name"));
			System.out.println("나이 : "+ rs.getInt("age"));
			System.out.println("성별 : "+ rs.getString("gender"));
			System.out.println("주소 : "+ rs.getString("address"));	
			System.out.println();
			
			rs.next(); // 레코드포인터를 다음 레코드로 이동
			System.out.println("번호 : "+ rs.getInt("idx"));
			System.out.println("성명 : "+ rs.getString("name"));
			System.out.println("나이 : "+ rs.getInt("age"));
			System.out.println("성별 : "+ rs.getString("gender"));
			System.out.println("주소 : "+ rs.getString("address"));	
			System.out.println();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		}
	}
	
}
