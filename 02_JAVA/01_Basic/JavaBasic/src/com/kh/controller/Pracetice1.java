package com.kh.controller;
import java.util.Scanner;

public class Pracetice1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * 나이를 입력받아
		 * 13세 이하면 : 어린이
		 * 13세 초과 19세 이하 : 청소년
		 * 19세 초과 : 성인
		 * 
		 * [출력]
		 * 나이를 입력 : 
		 * xx은 xxx에 속합니다
		 */
		
		System.out.println("나이를 입력 : ");
		int age = sc.nextInt();
		
		String old;
		
		if (age <= 13) {
			old ="어린이";
		}
		else if(age<=19) {
			old ="청소년";
		}
		else if(age > 19) {
			old = "성인";
		}
		else {
			old = "??";
		}

		System.out.printf(age+"살은 "+old+"에 속합니다.");
	}

}
