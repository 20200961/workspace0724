package com.kh.practice;

import java.util.Scanner;

public class Q3 {
	/* 
	 * 어린이, 청소년, 성인의 구분에 따라 입장료가 다르게 부과되는 놀이공원 요금 계산기를 만들어보세요.
	 * 또한, 주말에는 20% 할인이 적용됩니다.
	 * 나이 (int), 요일 (문자열: "월", "화", ..., "일")
	 * 요일이 "토" 또는 "일"인 경우, 입장료의 20% 할인
	 * 나이에 따라 구분을 if 또는 else if로 나눈다.
	 * 요일에 따라 할인 적용 여부를 판단하기 위해 switch 또는 if를 활용한다.
	 * 최종 요금은 정수로 출력하며, 할인 적용 여부도 명시해야 한다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("나이를 입력하세요 : ");
		int age = sc.nextInt();
		
		System.out.printf("요일을 입력하세요 : ");
		String day = sc.next();
		
		int price;
		String old;
		
		if (day.equals("토") || day.equals("일")) {
			if(age > 0 && age <= 12) {
				old = "어린이";
				price = 5000 * 8/10;
			}
			else if(age > 13 && age <= 18) {
				old = "청소년";
				price = 7000 * 8/10;
			}
			else {
				old = "성인";
				price = 10000 * 8/10;
			}
			System.out.println(old + "요금입니다. (주말 할인 적용)");
			System.out.println("최종 요금은 " + price + "입니다.");
		}
		else {
			if(age > 0 && age <= 12) {
				old = "어린이";
				price = 5000;
			}
			else if(age > 13 && age <= 18) {
				old = "청소년";
				price = 7000;
			}
			else {
				old = "성인";
				price = 10000;
			}
			System.out.println(old + "요금입니다. (주말 할인 적용)");
			System.out.println("최종 요금은" + price + "입니다.");
		}
		
		

	}

}
