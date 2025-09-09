package com.kh.controller;
import java.util.Scanner;

public class Practice3 {
	/*
	 * 정수(양수)를 입력받아
	 * 짝수인지 홀수인지 출력하는 프로그램
	 * 
	 * [출력]
	 * 정수 입력 : xx
	 * 짝수다 / 홀수다 / 양수가 아니다
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수 입력 : ");
		int num = sc.nextInt();
		
		if (num % 2 == 1) {
			System.out.println("홀수다");
		}
		else if (num % 2 == 0 && num != 0) {
			System.out.println("짝수다");
		}
		else {
			System.out.println("양수가 아니다");
		}
		

	}

}
