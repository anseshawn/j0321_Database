package t1_Connection;

public class Test3Run {
	public static void main(String[] args) {
		Test3DAO dao = new Test3DAO();
		
		System.out.println("데이터베이스 연결 후 작업 중입니다...");
		
		Test3Service service = new Test3Service();
		
		service.getTestMethod();
		
		// 작업 후 DB 연결 종료
		dao.connClose();
	}
}
