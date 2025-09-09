package com.kh.practice.loop;

import java.util.Scanner;

public class Practice3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt(); // 테스트케이스
		
		for(int j = 0; j<a; j++) {
			int b = sc.nextInt();
			int c = sc.nextInt();
			System.out.printf("case #%d = %d\n",a,(b+c));
		}

		sc.close();
	}

}
