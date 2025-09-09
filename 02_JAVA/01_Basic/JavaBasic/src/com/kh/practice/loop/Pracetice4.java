package com.kh.practice.loop;

import java.util.Scanner;

public class Pracetice4 {
	/*
	 * 1부터 100 사이의 숫자 중에서 7의 배수만 출력하는 프로그램을 작성하세요.
	 * 단, 짝수인 7의 배수는 건너뛰고, 출력에서 제외하세요.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i=1;i<100;i++) {
			if(i%7 != 0) {
				continue;
			}
			if(i%2 == 0) {
				continue;
			}
			System.out.print(i+" ");
		}
		

	}

}
