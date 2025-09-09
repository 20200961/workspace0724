package com.myapp.controller;

import com.myapp.service.UserService;
import com.myapp.model.vo.User;

public class UserController {
    private UserService userService = new UserService();

    // 회원가입
    public void registerUser(String userId, String password, String name, double balance) {
        User u = new User(userId, password, name, balance);

        int result = userService.insertUser(u); // Connection 제거

        if (result > 0) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
    }

    // 로그인
    public boolean loginUser(String userId, String password) {
        User u = new User(userId, password, null, 0);

        boolean isLogin = userService.loginUser(u); // Connection 제거

        if (isLogin) {
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패");
        }

        return isLogin;
    }

    // 잔액 조회
    public double checkBalance(String userId) {
        User u = new User(userId, null, null, 0);

        double balance = userService.getBalance(u); // Connection 제거

        System.out.println("현재 잔액: " + balance);
        return balance;
    }

    // 잔액 업데이트
    public void updateBalance(String userId, double newBalance) {
        User u = new User(userId, null, null, newBalance);

        int result = userService.updateBalance(u); // Connection 제거

        if (result > 0) {
            System.out.println("잔액 수정 성공");
        } else {
            System.out.println("잔액 수정 실패");
        }
    }
    
    public void addBalance(String userId,double newBalance) {
    	User u = new User(userId, null, null, newBalance);
    	
    	int result = userService.addBalance(u);
    	
    	if (result > 0) {
            System.out.println("입금 성공");
        } else {
            System.out.println("입금 실패");
        }
    }
}
