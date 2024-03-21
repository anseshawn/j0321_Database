package t2_CRUD;
//1.sql 2.VO 3.DAO 4.Run(실행,메인메소드포함)
public class HoewonRun {
	public static void main(String[] args) {
		// 데이터베이스와 서비스 둘다 올려줘야 함
		// HoewonDAO dao = new HoewonDAO();
		// HoewonDAO2 dao = new HoewonDAO2();
		HoewonDAO3 dao = new HoewonDAO3();
				
		// hoewon자료 조회 (CRUD중 R에진입)
		dao.getList();
		
		dao.connClose();
	}
}
