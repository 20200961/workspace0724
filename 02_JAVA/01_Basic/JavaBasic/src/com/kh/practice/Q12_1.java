package com.kh.practice;

import java.util.Scanner;

public class Q12_1 {
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] chess = {1,1,2,2,2,8};
		int[] num = new int[6];
		int[] fin = new int[6];
		
		for(int i=0;i<num.length;i++) {
			num[i]=sc.nextInt();
			fin[i] = chess[i] - num[i];
			System.out.print(fin[i]+" ");
		}
		
	}

}
