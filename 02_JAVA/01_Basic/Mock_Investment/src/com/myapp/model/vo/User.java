package com.myapp.model.vo;

public class User {
	private String userId;
	private String password;
	private String name;
	private double balance;
	
	public User() {
		super();
	}

	public User(String userId, String password, String name, double balance) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.balance = balance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User{" +
	            "userId='" + userId + '\'' +
	            ", password='" + password + '\'' +
	            ", name='" + name + '\'' +
	            ", balance=" + balance +
	            '}';
	}
	

}
