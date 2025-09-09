package com.kh.practice.loop;

import java.util.Scanner;
	// 영수증에 적힌 총 구매 금액이 실제 구매한 각 물건의 가격 × 개수 합계와 일치하는지 확인하는 프로그램을 작성하세요.
public class Practice2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt();
		int n = sc.nextInt();
		int sum = 0;
		
		for(int i=0; i<n;i++) {
			int a = sc.nextInt(); // 물건의 가격
			int b = sc.nextInt(); // 물건의 구매개수
			
			sum += (a * b);
		}
		
		System.out.println(sum == x ? "Yes" : "No");
		sc.close();

	}

}
