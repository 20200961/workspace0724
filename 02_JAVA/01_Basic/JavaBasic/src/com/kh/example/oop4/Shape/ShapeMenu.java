package com.kh.example.oop4.Shape;

import java.util.Scanner;

public class ShapeMenu {
    Scanner sc = new Scanner(System.in);
    SquareController scr = new SquareController();
    TriangleController tc = new TriangleController();

    public void inputMenu() {
        int select = 0;
        do {
            System.out.println("===== 도형 프로그램 =====");
            System.out.println("3. 삼각형");
            System.out.println("4. 사각형");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴번호 : ");
            select = sc.nextInt();
            sc.nextLine();

            switch(select) {
                case 3: triangleMenu(); break;
                case 4: squareMenu(); break;
                case 9: System.out.println("프로그램 종료"); break;
                default: System.out.println("잘못된 번호입니다. 다시 입력하세요."); break;
            }
        } while(select != 9);
    }

    public void triangleMenu() {
        int select = 0;
        do {
            System.out.println("===== 삼각형 =====");
            System.out.println("1. 삼각형 면적");
            System.out.println("2. 삼각형 색칠");
            System.out.println("3. 삼각형 정보");
            System.out.println("9. 메인으로");
            System.out.print("메뉴 번호 : ");
            select = sc.nextInt();
            sc.nextLine();

            switch(select) {
                case 1:
                case 2:
                    inputSize(3, select);
                    break;
                case 3:
                    printInformation(3);
                    break;
                case 9: break;
                default: System.out.println("잘못된 번호입니다. 다시 입력하세요."); break;
            }
        } while(select != 9);
    }

    public void squareMenu() {
        int select = 0;
        do {
            System.out.println("===== 사각형 =====");
            System.out.println("1. 사각형 둘레");
            System.out.println("2. 사각형 면적");
            System.out.println("3. 사각형 색칠");
            System.out.println("4. 사각형 정보");
            System.out.println("9. 메인으로");
            System.out.print("메뉴 번호 : ");
            select = sc.nextInt();
            sc.nextLine();

            switch(select) {
                case 1:
                case 2:
                case 3:
                    inputSize(4, select);
                    break;
                case 4:
                    printInformation(4);
                    break;
                case 9: break;
                default: System.out.println("잘못된 번호입니다. 다시 입력하세요."); break;
            }
        } while(select != 9);
    }

    public void inputSize(int type, int menuNum) {
        double height, width;
        String color;

        switch(type) {
            case 3: // 삼각형
                if(menuNum == 1) { // 면적
                    System.out.print("높이 : ");
                    height = sc.nextDouble();
                    System.out.print("너비 : ");
                    width = sc.nextDouble();
                    double area = tc.calcArea(height, width);
                    System.out.println("삼각형의 넓이 : " + area);
                } else if(menuNum == 2) { // 색칠
                    System.out.print("색깔을 입력하세요 : ");
                    color = sc.next();
                    tc.paintColor(color);
                }
                break;
            case 4: // 사각형
                if(menuNum == 1) { // 둘레
                    System.out.print("높이 : ");
                    height = sc.nextDouble();
                    System.out.print("너비 : ");
                    width = sc.nextDouble();
                    double perimeter = scr.calcPerimeter(height, width);
                    System.out.println("사각형의 둘레 : " + perimeter);
                } else if(menuNum == 2) { // 면적
                    System.out.print("높이 : ");
                    height = sc.nextDouble();
                    System.out.print("너비 : ");
                    width = sc.nextDouble();
                    double area = scr.calcArea(height, width);
                    System.out.println("사각형의 넓이 : " + area);
                } else if(menuNum == 3) { // 색칠
                    System.out.print("색깔을 입력하세요 : ");
                    color = sc.next();
                    scr.paintColor(color);
                }
                break;
        }
    }

    public void printInformation(int type) {
        if(type == 3) {
            System.out.println(tc.print());
        } else if(type == 4) {
            System.out.println(scr.print());
        }
    }
}
