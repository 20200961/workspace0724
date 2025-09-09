package com.kh.practice.loop;

import java.util.Scanner;

public class Practice8 {
	// 사용자로부터 정수 N(1 ≤ N ≤ 20)을 입력받아, N층의 왼쪽 정렬된 별 피라미드를 출력하세요.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 입력 : ");
		int num = sc.nextInt();
		
		for(int i = 0;i<num;i++) {
			for(int j =0; j<=i;j++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

}
