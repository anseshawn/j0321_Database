package t3_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class HoewonService {
	Scanner sc = new Scanner(System.in);

	// 전체조회
	public void getList() {
		// HoewonDAO dao = new HoewonDAO();
		HoewonDAO2 dao = new HoewonDAO2();
		
		ArrayList<HoewonVO> vos = dao.getList(); // vo(자료)가 여러개라서 ArrayList에 담아줘야 함
		HoewonVO vo = new HoewonVO(); // 출력을 위한 vo: 한번만 생성
		
		System.out.println("===========================================");
		System.out.println(" 번호\t성명\t나이\t성별\t주소");
		System.out.println("===========================================");
		for(int i=0; i<vos.size();i++) { // vos의 크기를 알기 때문에 for문
			vo = vos.get(i); // 출력을 위한 vo 에 DAO2의 vo에서 넘어온 값을 담아줌
			System.out.print((i+1)+"\t");
			System.out.print(vo.getName()+"\t");
			System.out.print(vo.getAge()+"\t");
			System.out.print(vo.getGender()+"\t");
			System.out.print(vo.getAddress()+"\n");	
		}
		System.out.println("===========================================");
		System.out.println("\t\t총 : "+vos.size()+" 건");
		
		dao.connClose();
	}

	// 개별 검색처리
	public void getSearch(String name) {
		HoewonDAO2 dao = new HoewonDAO2();
		
		HoewonVO vo = dao.getSearch(name); // Run에서 가져온 name을 DAO에 다시 넘겨주기
		System.out.println(name + "(으)로 검색된 자료?");
		if(vo.getName() != null) {
			System.out.println("번호 : "+ vo.getIdx());
			System.out.println("성명 : "+ vo.getName());
			System.out.println("나이 : "+ vo.getAge());
			System.out.println("성별 : "+vo.getGender());
			System.out.println("주소 : "+vo.getAddress());
		}
		else {
			System.out.println("검색한 자료가 없습니다.");
		}
		
		dao.connClose();
	}

	// 회원 정보 수정
	public void setUpdate(String name) {
		HoewonDAO2 dao = new HoewonDAO2();
		
		HoewonVO vo = dao.getSearch(name);
		
		System.out.println(name + "(으)로 검색된 자료?");
		if(vo.getName() != null) {
			System.out.println("번호 : "+ vo.getIdx());
			System.out.println("성명 : "+ vo.getName());
			System.out.println("나이 : "+ vo.getAge());
			System.out.println("성별 : "+vo.getGender());
			System.out.println("주소 : "+vo.getAddress());
			System.out.println("===========================");
			System.out.print("수정할 항목? (1.성명 2.나이 3.성별 4.주소) ==> ");
			int choice = sc.nextInt();
			System.out.print("수정할 내용? ");
			String content = sc.next();
			dao.setUpdate(vo.getIdx(),choice,content); // vo를 하나만 바꾸기 위해 idx(고유번호)도 같이 넘겨야함
			System.out.println("===> 자료가 수정되었습니다.");
		}
		else {
			System.out.println("검색한 자료가 없습니다.");
		}
		
		dao.connClose();
	}

	// 회원자료 삭제처리
	public void setDelete(String name) {
		HoewonDAO2 dao = new HoewonDAO2();
		
		HoewonVO vo = dao.getSearch(name);
		
		System.out.println(name + "(으)로 검색된 자료?");
		if(vo.getName() != null) {
			System.out.println("번호 : "+ vo.getIdx());
			System.out.println("성명 : "+ vo.getName());
			System.out.println("나이 : "+ vo.getAge());
			System.out.println("성별 : "+vo.getGender());
			System.out.println("주소 : "+vo.getAddress());
			System.out.println("===========================");
			System.out.print("삭제하시겠습니까?(Y/N) ==> ");
			String choice = sc.next();
			if(choice.toUpperCase().equals("Y")) {
				dao.setDelete(name);
				System.out.println("====> 삭제 성공");
			}
			else {
				System.out.println("====> 삭제 취소");
			}
		}
		else {
			System.out.println("검색한 자료가 없습니다.");
		}
		
		dao.connClose();
	}

	// 회원자료 등록처리
	public void setInput() {
		HoewonDAO2 dao = new HoewonDAO2();
		
		String name,gender,address;
		int age;
		
		System.out.println("==> 회원 정보 등록하기");
		System.out.print("성명 : ");
		name = sc.next();
		System.out.print("나이 : ");
		age = sc.nextInt();
		System.out.print("성별 : ");
		gender = sc.next();
		System.out.print("주소 : ");
		address = sc.next();
		
		HoewonVO vo = new HoewonVO();
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);
		
		dao.setInput(vo);
		System.out.println("회원 등록 완료!!!");
		
		dao.connClose();
	}

}
