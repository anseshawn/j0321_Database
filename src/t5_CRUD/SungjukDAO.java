package t5_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SungjukDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//preparedstatement : 입력받을 부분을 '?'로 사용(웹에서 넘어오는 자료는 전부 문자로 인식)
	// sql을 pstmt 생성할 때 넣음 (stmt: 생성해놓고 sql 넣음)
	// 순서: sql생성 -> pstmt 생성 -> 실행
	
	SungjukVO vo = null;
	String sql = "";
	// 8~13 가장 기본셋팅(jsp)
	
	public SungjukDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패"+e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패"+e.getMessage());
		}
	}
	
	// conn객체 Close()
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// pstmt객체 close
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();			
		} catch (Exception e) {}
	}
	
	// rs객체 close
	public void rsClose() {
		try {
			if(rs != null) rs.close();
			pstmtClose();
		} catch (Exception e) {
		}
	}

	// 성적자료 입력처리
	public int setSungjukInput(SungjukVO vo) {
		int res = 0;
		try {
			sql = "insert into sungjuk values (default,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			// ? 부분에 타입 지정해주기
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			res = pstmt.executeUpdate(); // 정상처리가 되면 res가 0이 아니게 됨
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 성명 조회(개별검색/동명이인검색)
	public SungjukVO getSungjukSearch(String name) {
		SungjukVO vo = new SungjukVO();
		try {
			sql = "select * from sungjuk where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
			}
			else vo = null; // vo에 자료가 있으면 담아가고 없으면 null 처리
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}	
		return vo;
	}

	// 전체 자료 검색 처리
	public ArrayList<SungjukVO> getSungjukList() {
		ArrayList<SungjukVO> vos = new ArrayList<>();
		
		try {
			sql = "select * from sungjuk order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new SungjukVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}	
		return vos;
	}

	// 성적자료 수정하기
	public int setSungjukUpdate(SungjukVO vo) {
		int res = 0;
		try {
			sql = "update sungjuk set name=?,kor=?,eng=?,mat=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}	
		return res;
	}

	public int setSungjukDelete(SungjukVO vo) {
		int res = 0;
		try {
			sql = "delete from sungjuk where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx()); // 동명이인이 있을수 있으니까... 역시 idx가...
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}	
		return res;
	}
	
	
}
