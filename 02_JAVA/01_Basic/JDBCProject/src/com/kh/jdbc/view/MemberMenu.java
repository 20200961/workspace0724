package com.kh.jdbc.view;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.controller.MemberController;
import com.kh.jdbc.model.vo.Member;

public class MemberMenu {
	private Scanner sc;
	private MemberController mc;
	
	public MemberMenu() {
		super();
		this.sc = new Scanner(System.in);
		this.mc = new MemberController();
	}
	
	/*
	 * 사용자가 보게될 첫 화면(메인화면)
	 */
	
	public void mainMenu() {
		while(true) {
			System.out.println("============ 회원관리 프로그램 ============");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 정보 변경");
			System.out.println("4. 회원 탈퇴");
			System.out.println("9. 프로그램 종료");
	
			System.out.println("메뉴 입력 : ");
			int sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 1:
				System.out.println("============ 회원 추가 ============");
				// id~ 취미까지 전부 입력받아서 회원 추가
				
				System.out.println("아이디 : ");
				String userId = sc.next();
	
				System.out.println("비밀번호 : ");
				String userPwd = sc.next();
	
				System.out.println("이름 : ");
				String userName = sc.next();
	
				System.out.println("성별(M/F) : ");
				String gender = sc.next();
	
				System.out.println("나이 : ");
				String age = sc.next();
	
				System.out.println("이메일 : ");
				String email = sc.next();
	
				System.out.println("전화번호 : ");
				String phone = sc.next();
	
				System.out.println("주소 : ");
				sc.nextLine();
				String address = sc.next();
	
				System.out.println("취미(,로 구분) : ");
				String hobby = sc.next();
				sc.nextLine();
				
				mc.insertMember(userId,userPwd,userName,gender,age,email,phone,address,hobby);
				break;
			case 2: mc.selectMemberAll(); break;
			case 3: updateMember(); break;
			case 4: deleteMember(); break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
				default:
					System.out.println("잘못 입력하셨습니다");
			
			}
			System.out.println();
		}
	}
	//===============================응답화면===============================
	// 서비스 요청 처리 후 성공했을 때 사용자가 보게될 화면
	// msg : 기능별 성공메세지
	
	public void updateMember() {
		System.out.println("========== 회원 정보 ==========");
		// 어떤 회원의 정보를 수정할 것인가? -> USER_ID
		// 변경할 정보를 입력
		
		System.out.println("수정할 아이디 : ");
		String userId = sc.next();

		System.out.println("수정할 이메일 : ");
		String email = sc.next();

		System.out.println("수정할 전화번호 : ");
		String phone = sc.next();

		System.out.println("수정할 주소 : ");
		sc.nextLine();
		String address = sc.next();

		System.out.println("수정할 취미(,로 구분) : ");
		String hobby = sc.next();
		
		mc.updateMember(userId, email, phone, address, hobby);
	}
	
	public void deleteMember() {
		System.out.println("========== 회원 정보 ==========");
		// 어떤 회원의 정보를 삭제할 것인가? -> USER_ID
		
		System.out.println("삭제할 아이디 : ");
		String userId = sc.next();
		
		System.out.println("삭제할 아이디의 비밀번호 : ");
		String userPwd = sc.next();
		
		mc.deleteMember(userId,userPwd);
	}
	

	public void displaySuccess(String msg) {
		System.out.println("\n서비스 요청 성공 : "+msg);
	}
	
	// 서비스 요청 처리 후 실패했을 때 사용자가 보게될 화면
		// msg : 기능별 성공메세지
		public void displayFail(String msg) {
			System.out.println("\n서비스 요청 실패 : "+msg);
		}
		
		public void displayNoData(String msg) {
			System.out.println("\n"+msg);
		}
		
		public void displayList(List list, String title) {
			System.out.println("========="+title+"=========");
			for(Object o : list) {
				System.out.println(o);
			}
		}
}
