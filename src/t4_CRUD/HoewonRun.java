package t4_CRUD;

import java.util.Scanner;

//1.sql 2.VO 3.DAO 4.Run(실행,메인메소드포함)
public class HoewonRun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HoewonService service = new HoewonService();
		
		boolean run = true;
		String name = "";
		
		while(run) {
			System.out.print("1.회원등록  2.전체조회  3.개별조회  4.수정  5.삭제  6.종료 ==> ");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					service.setInput(); // hoewon자료 등록
					break;
				case 2:
					service.getList(); // hoewon자료 전체조회
					break;
				case 3:
					System.out.print("검색할 성명을 입력하세요: ");
					name = sc.next();
					service.getSearch(name); // hoewon자료 개별조회
					break;
				case 4:
					System.out.print("수정할 성명을 입력하세요: ");
					name = sc.next();
					service.setUpdate(name); // hoewon자료 수정
					break;
				case 5:
					System.out.print("삭제할 성명을 입력하세요: ");
					name = sc.next();
					service.setDelete(name); // hoewon자료 삭제
					break;
				default:
					run = false;
			}
		}
		System.out.println("수고하셨습니다.");
		
		sc.close();
	}
}
