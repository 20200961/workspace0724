package com.kh.practice;

import java.util.Scanner;

public class Q2 {
	/*
	 * 1부터 6까지 눈이 있는 세 개의 주사위를 던졌을 때, 아래 규칙에 따라 상금을 계산하는 프로그램을 작성하세요.
	 * 한 줄에 세 개의 정수 (각각 주사위 눈, 1 ~ 6) 공백으로 구분하여 입력
	 * if / else if / else 조건문을 사용하여 세 경우에 맞게 분기 처리
	 * 세 개 주사위 값을 변수에 저장한 뒤 비교
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("세 정수 입력 : ");
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		
		int result;

		if (num1 == num2 && num1 == num3) {
			result = 10000 + num1*1000;
		}
		else if(num1 == num2) {
			result = 1000 + num1 * 100;
		}
		else if(num2 == num3) {
			result = 1000 + num2 * 100;
		}
		else if(num1 == num3) {
			result = 1000 + num3 * 100;
		}
		/*
		 * java에서 제공하는 수학함수
		 * java.lang.Math -> 코드를 작성할 때 기본적으로 유용한 것들을 담아서 제공
		 * Math.max(n1,n2) -> 둘중 큰값을 반환
		 * 
		 * int max = Math.max(n1,n2);
		 * max = Math.max(max,n3)
		 */
		else if(num1 > num2 && num1 > num3){
			result = num1 * 100;
		}
		else if(num2 > num1 && num2 > num3){
			result = num2 * 100;
		}
		else {
			result = num3 * 100;
		}
		System.out.println("출력 : " + result);
		

	}

}
