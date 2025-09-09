package com.kh.loop;

import java.util.Scanner;

public class DoWhile {
	/*
	 * do-while문
	 * 
	 * do{
	 * 		실행할 코드
	 * } while(조건식);
	 * 
	 * 처음에는 무조건 실행코드 한번 실행되고 조건문을 검사
	 * 
	 * *기존의 for/while은 처음 수행될 때 조건검사 후 true일 경우에만 반복코드를 실행함
	 * 하지만 do-while은 첫 실행은 조건검사를 하지않고 무조건 실행
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int num;
//		
//		do {
//			System.out.println("서비스 번호를 입력하세요 : ");
//			System.out.println("1. 추가 : ");
//			System.out.println("2. 삭제: ");
//			System.out.println("3. 종료 : ");
//			
//			System.out.println("입력 : ");
//			num = sc.nextInt();
//			
//			switch(num) {
//			case 1:
//				// 추가하는 로직 
//				System.out.println("추가");
//				break;
//			case 2:
//				// 추가하는 로직 
//				System.out.println("삭제");
//				break;
//			case 3:
//				// 추가하는 로직 
//				System.out.println("종료");
//				break;
//			case 4:
//				// 추가하는 로직 
//				System.out.println("잘못입력하셨습니다");
//				break;
//			}
//		} while(num !=3);
		
		
		// 사용자가 입력한 수를 계속해서 더하는 프로그램 작성
		// 단, 사용자가 0을 입력하면 종료
		int num1;
		int sum = 0;
		
		do {
			System.out.println("서비스 번호를 입력하세요(0을 입력하면 종료) : ");
			num1 = sc.nextInt();
			sum += num1;
			
			
		} while(num1 != 0);
		System.out.println("sum : "+sum);
		
		
		
		
		
		
		

	}

}
