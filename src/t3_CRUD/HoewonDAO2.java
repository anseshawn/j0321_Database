package t3_CRUD;
// 1.sql 2.VO 3.DAO(데이터베이스연결)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HoewonDAO2 {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	private String sql = null;
	
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
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			sql= "select * from hoewon";
			rs = stmt.executeQuery(sql);			
			
			HoewonVO vo = null;
			while(rs.next()) {
				vo = new HoewonVO(); // 반복되면서 값을 담아줄 새로운 vo생성
				vo.setIdx(rs.getInt("idx")); // 새로운 vo에 값을 담음
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				
				vos.add(vo); // 값이 있는 vo를 ArrayList에 넘겨줌
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		} 
		
		return vos;
	}

	// 개별검색처리
	public HoewonVO getSearch(String name) {
		HoewonVO vo = new HoewonVO();
		
		try {
			stmt = conn.createStatement();
			sql= "select * from hoewon where name = '"+name+"'";
			rs = stmt.executeQuery(sql);			
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx")); // 새로운 vo에 값을 담음
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));				
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		}
		
		return vo;
	}

	// 회원자료 수정처리
	public void setUpdate(int idx, int choice, String content) {
		try {
			stmt = conn.createStatement();
			sql= "";
			if(choice == 1) {
				sql = "update hoewon set name = '"+content+"' where idx = "+idx;
			}
			else if(choice == 2) {
				sql = "update hoewon set age = "+Integer.parseInt(content)+" where idx = "+idx;
			}
			else if(choice == 3) {
				sql = "update hoewon set gender = '"+content+"' where idx = "+idx;
			}
			else if(choice == 4) {
				sql = "update hoewon set address = '"+content+"' where idx = "+idx;
			}
			// executeQuery: select에만 씀, executeUpdate: insert,update,delete
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		}
	}

	// 회원 삭제 처리
	public void setDelete(String name) {
		try {
			stmt = conn.createStatement();
			sql= "delete from hoewon where name='"+name+"'";
			// executeQuery: select에만 씀, executeUpdate: insert,update,delete
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		}
	}

	// 회원 등록 처리
	public void setInput(HoewonVO vo) {
		try {
			stmt = conn.createStatement();
			sql= "insert into hoewon values(default,'"+vo.getName()+"',"+vo.getAge()+",'"+vo.getGender()+"','"+vo.getAddress()+"')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		}
	}
	
}
