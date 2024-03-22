package t4_CRUD;
// 1.sql 2.VO 3.DAO(데이터베이스연결)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HoewonDAO2 {
	private Connection conn = null; // 커넥션 역할: 서버에 접속해서 DB에 연동하는 것까지
	private Statement stmt = null; // sql문 쓰기 위해 필요
	private ResultSet rs = null; // sql명령 중에 select절이 있으면 무조건 있어야 함
	
	HoewonVO vo = null;
	private String sql = null;
	
	public HoewonDAO2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 String url = "jdbc:mysql://localhost:3306/javaclass";
			// String url = "jdbc:mysql://127.0.0.1:3306/javaclass"; // 외부에서 접속할 때 localhost부분에 아이피, 혹은 사이트 주소 (로컬호스트 아이피: 127.0.0.1)
			// String url = "jdbc:mysql://192.168.50.58:3306/javaclass"; 
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
	
	// Statement 객체 닫기
	public void stmtClose() {
		// 사용했을 경우에만 닫아줘야 함
		try {
			if(stmt != null)  stmt.close(); // stmt를 사용했다면 null이 아님
		} catch (SQLException e) {}
	}
	
	// ResultSet 닫기
	public void rsClose() {
		try {
			if(rs != null) rs.close(); // 사용했을 경우에만 닫기
			stmt.close(); // ResultSet을 사용할 땐 Statement도 무조건 사용하게 되므로 한번에 같이 닫기
		} catch (SQLException e) {}
	}
	
	
	// 전체 회원 조회
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<>();
		
		try {
			stmt = conn.createStatement();
			sql= "select * from hoewon";
			rs = stmt.executeQuery(sql);			
			
			// HoewonVO vo = null;
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
		} finally {
			rsClose();
		}
		
		return vos;
	}

	// 개별검색처리
	public HoewonVO getSearch(String name) {
		HoewonVO vo = new HoewonVO(); 
		// vo = new HoewonVO(); // 위에 선언을 미리 해주긴 했지만 리턴타입에 맞추기 쉽게 새로 선언
		
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
		} finally {
			rsClose();
		}
		
		return vo;
	}

/*
	// 회원자료 수정처리
	public void setUpdate(int idx, int choice, String content) {
		try {
			stmt = conn.createStatement();
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
		} finally {
			stmtClose();
		}
	}
*/
	

	// 회원 삭제 처리
	public void setDelete(String name) {
		try {
			stmt = conn.createStatement();
			sql= "delete from hoewon where name='"+name+"'";
			// executeQuery: select에만 씀, executeUpdate: insert,update,delete
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		} finally {
			stmtClose();
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
		} finally {
			stmtClose();
		}
	}

	// 회원 정보 수정하기
	public int setUpdate(HoewonVO vo) {
		// System.out.println("vo : "+vo); => 수정이 잘 되었는지 체크 후 삭제하면 됨
		int res = 0;
		try {
			stmt = conn.createStatement();
			sql = "update hoewon set name='"+vo.getName()+"',age="+vo.getAge()+",gender='"+vo.getGender()+"',address='"+vo.getAddress()+"' where idx="+vo.getIdx();
			// executeQuery: select에만 씀, executeUpdate: insert,update,delete
			res = stmt.executeUpdate(sql);
			// System.out.println("res : "+res); -> 체크한뒤 최종으로 출력은 되지 않게 삭제 혹은 주석처리
		} catch (SQLException e) {
			System.out.println("SQL 오류 : "+e.getMessage());
		} finally {
			stmtClose();
		}
		return res;
	}
	
}
