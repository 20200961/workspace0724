package com.kh.practice;

import java.util.Scanner;

public class Q12_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String alpa = sc.next();
		int isSame = 1;
		
		for(int i=0; i<alpa.length()/2;i++) {
			if (alpa.charAt(i) != alpa.charAt(alpa.length() - 1 - i)) {
                isSame = 0; 
                break; 
            }
		}
		
		System.out.println(isSame);
		

	}

}
