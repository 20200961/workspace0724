package com.kh.practice;
import java.util.Scanner;

public class Q1 {
	/*
	 * 0점부터 100점 사이의 정수를 입력받아 아래 조건에 따라 **등급(학점)**을 출력하는 프로그램을 작성하세요.
	 * 시험 점수 (정수형, 0 이상 100 이하)
	 * 조건문(if ~ else if ~ else) 또는 switch문을 사용하여 등급 분기
	 * 입력값이 0~100 범위에 있는지 검사하지 않아도 됨 (문제에 보장되어 있음)
	 * 등급은 문자 또는 문자열로 출력
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("점수를 입력하세요 : ");
		int score = sc.nextInt();
		String grade;
		
		switch(score/10) {
		case 10:
			grade = "A";
			break;
		case 9:
			grade = "A";
			break;
		case 8:
			grade = "B";
			break;
		case 7:
			grade = "C";
			break;
		case 6:
			grade = "D";
			break;
		default:
			grade = "F";
			break;
		}
		System.out.printf("당신의 성적은 %s입니다.",grade);
	}

}
