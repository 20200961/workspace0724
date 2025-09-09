package com.kh.operator;
import java.util.Scanner;

public class Practice1 {
	/*
	 * 키보드로 가로, 세로값을 실수형으로 입력받아 사각형의 면적과 둘레를 계산하여 출력
	 * 면적 : 가로 * 세로
	 * 둘레 : (가로 + 세로)
	 * 
	 * [출력]
	 * 가로 : (키보드로 입력)
	 * 세로 : (키보드로 입력)
	 * 
	 * 면적 : ~
	 * 둘레 : ~
	 * + 소수점 2번째 자리까지 출력
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("가로 : ");
		float num1 = sc.nextFloat();
		
		System.out.printf("세로 : ");
		float num2 = sc.nextFloat();
		
		float result1 = num1*num2;
		float result2 = (num1 + num2) *2;
		System.out.printf("면적 : %.2f\n", result1);
		System.out.printf("둘레 : %.2f", result2);
		
		

	}

}
