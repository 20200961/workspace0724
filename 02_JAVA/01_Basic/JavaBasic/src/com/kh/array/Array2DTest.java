package com.kh.array;

import java.util.Scanner;

public class Array2DTest {

	public static void main(String[] args) {
		// 사용자에게 행(m)과 열(n)을 입력받아
		// 해당 행과열의 빙고판을 만들어라
		// 다음 행과 열의 들어갈 문자를 각각 모두 입력받아 저장한 뒤 출력
		
		/*
		 * 행 : 2
		 * 열 : 3
		 * 
		 * 1행 1열 : 바나나
		 * 1행 2열 : 배
		 * ...
		 * 2행 3열 : 귤
		 */
		Scanner sc = new Scanner(System.in);
//		System.out.println("행을 입력하세요 : ");
//		int m = sc.nextInt();
//		
//		System.out.println("열을 입력하세요 : ");
//		int n = sc.nextInt();
//		
//		String[][] arr = new String[m][n];
//		
//		for(int j = 0; j<arr.length;j++) {
//			for(int i=0; i<arr[j].length;i++) {
//				System.out.printf("%d행 %d열 : ",j+1,i+1);
//				arr[j][i] = sc.next();
//			}
//		}
//		
//		for(int j = 0; j<arr.length;j++) {
//			for(int i=0; i<arr[j].length;i++) {
//				System.out.println(arr[j][i]+" ");
//			}
//		}
		
		/*
		 * ex)
		 * 사용자에게 좌석의 행과 열을 입력받아 2차원 배열을 생성
		 * 각 좌석에 들어갈 관객의 이름을 입력받아 저장한 뒤
		 * 모두 입력받았으면 좌석표를 출력
		 * 행(줄)의 수 : 
		 * 열(좌석)의 수 : 
		 * 1행 1열 : 철수~
		 * 1행 2열 : 민수~
		 * 1행 3열 : 상수~
		 * ...
		 * "====좌석표====="
		 * 철수 민수 상수 ...
		 * 
		 */
		
		System.out.println("행을 입력하세요 : ");
		int m = sc.nextInt();
		
		System.out.println("열을 입력하세요 : ");
		int n = sc.nextInt();
		
		String arr[][] = new String[m][n];
		
		for(int i=0;i<arr.length; i++) {
			for(int j = 0;j<arr[i].length;j++) {
				System.out.printf("%d행 %d열 : ",i+1,j+1);
				arr[i][j] = sc.next();
			}
		}
		
		System.out.println("=====좌석표=====");
		for(int i=0;i<arr.length; i++) {
			for(int j = 0;j<arr[i].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
		
		
	}

}
