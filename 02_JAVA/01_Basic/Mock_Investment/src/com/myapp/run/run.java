package com.myapp.run;

import com.myapp.controller.AssetController;
import com.myapp.controller.TradeHistoryController;
import com.myapp.model.vo.User;
import com.myapp.controller.UserController;

import java.util.Scanner;

public class run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController();
        AssetController assetController = new AssetController();
        TradeHistoryController tradehistoryController = new TradeHistoryController();

        User currentUser = null;

        // 로그인/회원가입
        while (true) {
            System.out.println("=== 모의 투자 프로그램 ===");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("사용자 ID: ");
                String userId = sc.nextLine();
                System.out.print("비밀번호: ");
                String password = sc.nextLine();
                System.out.print("이름: ");
                String name = sc.nextLine();
                System.out.print("초기 잔액: ");
                double balance = sc.nextDouble();
                sc.nextLine();
                userController.registerUser(userId, password, name, balance);
            } else if (choice == 2) {
                System.out.print("사용자 ID: ");
                String userId = sc.nextLine();
                System.out.print("비밀번호: ");
                String password = sc.nextLine();
                if (userController.loginUser(userId, password)) {
                    double balance = userController.checkBalance(userId);
                    currentUser = new User(userId, password, null, balance);
                    break;
                } else {
                    System.out.println("로그인 실패. 다시 시도하세요.");
                }
            } else if (choice == 3) {
                System.out.println("프로그램 종료");
                return;
            } else {
                System.out.println("잘못된 선택입니다.");
            }
        }

        // 메인 메뉴
        while (true) {
            System.out.println("\n=== 메인 메뉴 ===");
            System.out.println("1. 전체 코인 조회");
            System.out.println("2. 코인 매수");
            System.out.println("3. 코인 매도");
            System.out.println("4. 차트");
            System.out.println("5. 잔액 조회");
            System.out.println("6. 보유 코인 조회");
            System.out.println("7. 잔액 충전");
            System.out.println("8. 거래 기록 조회");
            System.out.println("9. 거래 기록 날짜 조회");
            System.out.println("10. 종료");
            System.out.print("선택: ");
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1 -> assetController.viewAllAssets();

                case 2 -> {
                    System.out.print("코인 ID: ");
                    String assetId = sc.nextLine();
                    System.out.print("수량: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    assetController.buyAsset(currentUser, assetId, amount);
                }

                case 3 -> {
                    System.out.print("코인 ID: ");
                    String assetId = sc.nextLine();
                    System.out.print("수량: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    assetController.sellAsset(currentUser, assetId, amount);
                }

                case 4 -> {
                    System.out.print("차트로 보고 싶은 코인 ID 입력: ");
                    String assetId = sc.nextLine();
                    assetController.showRealtimeCandleChart(assetId);
                }

                case 5 -> {
                    double balance = userController.checkBalance(currentUser.getUserId());
                    currentUser.setBalance(balance);
                }
                case 6 -> assetController.viewUserPortfolio(currentUser);
                case 7 -> {
                	System.out.println("입금할 금액 : ");
                	int amount = sc.nextInt();
                	sc.nextLine();
                	userController.addBalance(currentUser.getUserId(), amount);
                	
                	double newBalance = userController.checkBalance(currentUser.getUserId());
                    System.out.println("입금 완료! 현재 잔액: " + newBalance);
                }
                case 8 -> {
                	System.out.print("===== 거래 기록 =====");
                	tradehistoryController.selectAllTradeHistory(currentUser.getUserId());
                	
                }
                case 9 -> {
                	System.out.println("===== 거래 기록 =====");
                	System.out.print("조회할 날짜 (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    tradehistoryController.selectTradeHistoryByDate(currentUser.getUserId(), date);
                	
                }
                case 10 -> {
                    System.out.println("프로그램 종료");
                    return;
                }
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
    }
}
