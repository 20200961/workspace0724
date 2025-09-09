//package com.kh.example.gearrent;
//
//import java.util.Scanner;
//import java.util.Set;
//
//public class GearRentMenu {
//	 GearRentController gc = new GearRentController();
//	 Scanner sc = new Scanner(System.in);
//	 
//	 public void mainMenu() {
//		 while(true) {
//			 System.out.println("=== KH 장비 대여 관리 ===");
//			 System.out.println("1) 장비등록   2) 회원등록   3) 대여   4) 반납   5) 태그검색\r\n"
//			 		+ "6) 키워드검색 7) 전체장비   8) 대여중목록  9) 종료");
//			 System.out.print("메뉴 : ");
//			 int sel = sc.nextInt();
//			 switch(sel) {
//			 case 1:
//				 System.out.print("id : ");
//				 String id = sc.next();
//				 System.out.print("name : ");
//				 String name = sc.next();
//				 System.out.print("category : ");
//				 String category = sc.next();
//				 System.out.print("tags(쉼표로 구분 : ");
//				 Set<String,String> tags = sc.next();
//				 
//			 case 2:
//				 System.out.print("member id : ");
//				 String id = sc.next();
//				 System.out.print("name : ");
//				 String name = sc.next();
//			 case 3:
//			 case 4:
//			 case 5:
//			 case 6:
//			 case 7:
//			 case 8:
//			 case 9:
//				System.out.println("프로그램을 종료합니다");
//				break;
//				 default:
//					 System.out.println("다시 입력해주세요");break;
//			 }
//		 }
//		 
//	 }
//}
