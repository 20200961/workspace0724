package com.kh.controller;
import java.util.Scanner;

public class Practice2 {
	/*
	 * 성별을(m/f)(대소문자 상관x)로 입력받아 남학생인지 여학생인지
	 * 출력하는 프로그램을 작성해라
	 * 
	 * [출력]
	 * 성별(m/f) : x
	 * 여학생입니다 / 남학생입니다 / 잘못입력하셨습니다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("성별(m/f) : ");
		String sex = sc.next().toLowerCase();
		String result;
		
		if (sex.equals("m")) {
			System.out.println("남학생입니다.");
		}
		else if (sex.equals("f")) {
			System.out.println("여학생입니다.");
		}
		else {
			System.out.println("잘못입력하셨습니다.");
		}
		
		
		
	}

}
