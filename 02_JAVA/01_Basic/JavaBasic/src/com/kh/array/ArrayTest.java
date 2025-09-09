package com.kh.array;

import java.util.Scanner;

public class ArrayTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 1. 크기가 10인 정수형 배열 생성
		
		// 2. 반복문을 통해서 0번 인덱스부터 마지막 인덱스까지 10으로 초기화
		
		// 3. for-each를 통해서 모든 배열의 요소를 출력
		
		// 4. 사용자에게 배열의 길이를 입력받아, 해당 크기의 문자열 배열 nameArr을 생성
		
		// 5. 사용자에게 사람의 이름을 입력받아 nameArr에 할당, nameArr에 모든 요소만큼 실행.
		
		// 6. 사용자에게 이름을 하나 입력받아, nameArr에 동일한 이름이 있다면 
		//	  동일한 이름이 존재합니다 / 동일한 이름이 존재하지 않습니다. 출력
		// 1
		int[] arr = new int[10];
		// 2
		for(int i =0; i<arr.length; i++) {
			arr[i] = 10;
		}
		// 3
		for(int i : arr) {
			System.out.print(i + " ");
		}
		// 4
		System.out.println("배열의 길이 : ");
		int leng = sc.nextInt();
		String[] nameArr = new String[leng];
		// 5
		
		for(int i=0; i<nameArr.length; i++) {
			System.out.println("이름 입력 : ");
			String name = sc.next();
			nameArr[i] = name;
		}
		// 6
		System.out.println("이름 입력해 : ");
		String name1 = sc.next();
		boolean found = false;
		
		for(int i=0;i<nameArr.length; i++) {
			if(nameArr[i].equals(name1)) {
				found = true;
				break;
			}
		}
		if (found) {
		    System.out.println("동일한 이름이 존재합니다.");
		} else {
		    System.out.println("동일한 이름이 존재하지 않습니다.");
		}
		
		
	}

}
