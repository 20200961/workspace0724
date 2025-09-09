package com.kh.example.inherit2;

import java.util.Scanner;

public class PersonMenu {
	private Scanner sc = new Scanner(System.in);
	private PersonController pc = new PersonController();
	
	public void mainMenu() {
		int[] count = pc.personCount();
		while(true) {
			System.out.println("학생은 최대 3명까지 저장할 수 있습니다.");
			System.out.printf("현재 저장된 학생은 %d명입니다.\n",count[0]);
			System.out.println("사원은 최대 10명까지 저장할 수 있습니다.");
			System.out.printf("현재 저장된 사원은 %d명입니다.\n",count[1]);
			
		
			System.out.println("1. 학생 메뉴");
			System.out.println("2. 사원 메뉴");
			System.out.println("9. 끝내기");
			System.out.println("메뉴 번호 : ");
			
			int select = sc.nextInt();
			sc.nextLine();
			
			switch(select) {
			case 1:
				studentMenu();
				break;
			case 2:
				employeeMenu();
				break;
			case 9:
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("잘못 입력하셨습니다");
			}
		}
	}
	public void studentMenu() {
		while(true) {
			System.out.println("1.사원 추가");
			System.out.println("2. 사원 보기");
			System.out.println("9. 메인으로");
			System.out.println("메뉴 번호 : ");
			
			int select = sc.nextInt();
			sc.nextLine();
			
			switch(select) {
			case 1: // 추가
				insertStudent();
				break;
			case 2: // 보기
				break;
			case 9: // 메인으로
				System.out.println("메인으로....");
				return;
			default:
				System.out.println("잘못 입력하셨습니다");
			}
		}
	}
	public void employeeMenu() {
		while(true) {
			System.out.println("1.사원 추가");
			System.out.println("2. 사원 보기");
			System.out.println("9. 메인으로");
			System.out.println("메뉴 번호 : ");
			
			int select = sc.nextInt();
			sc.nextLine();
			
			switch(select) {
			case 1: // 추가
				insertStudent();
				break;
			case 2: // 보기
				break;
			case 9: // 메인으로
				System.out.println("메인으로....");
				return;
			default:
				System.out.println("잘못 입력하셨습니다");
			}
		}
	}
	
	public void insertStudent() {
		if(pc.personCount()[0] >= 3) {
			System.out.println("학생을 담을 수 있는 공간이 꽉참");
			return;
		}
		while(true) {
			
			System.out.print("학생 이름 : ");
			String name = sc.next();
			System.out.print("학생 나이 : ");
			int age = sc.nextInt();
			System.out.print("학생 키 : ");
			double height = sc.nextDouble();
			System.out.println("학생 몸무게 : ");
			double weight = sc.nextDouble();
			System.out.println("학생 학년 : ");
			int grade = sc.nextInt();
			System.out.println("학생 전공 : ");
			String major = sc.next();
			
			pc.insertStudent(name,age,height,weight,grade,major);
			
			
			System.out.println("그만하시려면 N(또는 n), 이어서하시려면 	아무 키나 누르세요.");
			char an = sc.next().toUpperCase().charAt(0);
			if(an == 'N') break;
		}

	}
	
	public void printStudent(){
		
	}
	

}
