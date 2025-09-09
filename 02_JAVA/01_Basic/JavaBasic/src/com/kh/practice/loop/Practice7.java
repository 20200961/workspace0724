package com.kh.practice.loop;

public class Practice7 {
	/*
	 * 2단부터 9단까지의 구구단 전체를 출력하는 프로그램을 작성하세요.
	 * 각 단마다 2 * 1 = 2 형식으로 1부터 9까지 곱한 값을 출력합니다.
	 */
	public static void main(String[] args) {
		for(int i = 2;i<10;i++) {
			for(int j = 1;j<10;j++) {
				System.out.printf("%d * %d = %d\n",i,j,i*j);
			}
		}

	}

}
