package com.kh.array;
import java.util.Scanner;
public class Practice3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] num = new int[N];
		
		
		for(int m=0;m<M;m++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int k = sc.nextInt();
			
			for(int n = 1-1;n<=j-1;n++) {
				num[n] = k;
			}
		}
		
		for (int b : num) {
            System.out.print(b + " ");
        }
		
		sc.close();

	}

}
