package com.kh.practice;

import java.util.Scanner;

public class Q12_5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0; // 그룹 단어 개수 카운트

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            boolean[] isDupl = new boolean[26];
            char prev = 0;
            boolean isOk = true;

            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (prev != ch) {
                    int index = ch - 'a';
                    if (isDupl[index]) { // 이미 등장했던 문자가 다른 곳에서 다시 나옴
                        isOk = false;
                        break;
                    }
                    isDupl[index] = true; // 등장 기록
                    prev = ch; // 이전 문자 저장
                }
            }

            if (isOk) count++;
        }

        System.out.println(count);
    }
}
