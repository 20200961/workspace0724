package com.kh.practice;

import java.util.Scanner;

public class Q12_3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next().toUpperCase();
        int[] arr = new int[26];

        // 알파벳 빈도 계산
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'A';
            arr[idx]++;
        }

        int max = 0, index = 0;
        boolean isDupl = false;

        // 최댓값 및 중복 여부 판단
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
                isDupl = false;
            } else if (arr[i] == max && max != 0) {
                isDupl = true;
            }
        }

        System.out.println(isDupl ? "?" : (char) (index + 'A'));
    }
}
