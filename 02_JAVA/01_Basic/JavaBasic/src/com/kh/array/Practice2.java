package com.kh.array;

import java.util.Scanner;

public class Practice2 {
	// N개의 정수가 주어질 때, 이들 중 최솟값과 최댓값을 찾는 프로그램을 작성하세요
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num1 = sc.nextInt();
		int[] num = new int[num1];
		
		for(int i = 0; i<num1; i++) {
			int num2 = sc.nextInt();
			num[i]=num2;
		}
		
		int max = num[0];
		int min = num[0];
	
		
		for (int i = 1; i < num1; i++) {
            if (num[i] > max) {
                max = num[i];
            }
            if (num[i] < min) {
                min = num[i];
            }
        }
		System.out.println(min+" "+max);
		

	}

}
