package com.kh.inherit;

public class BusinessMan extends Man{
	private String company;
	private String position;
	
	public BusinessMan(String name,String company,String position) {
		super(name); // 자식 클래스의 생성자에는 무조건 부모 생성자가 필요함
		// 맨 첫번째 줄에, 생략시 부모의 기본 생성자를 호출(없으면 에러).
		
		this.company = company;
		this.position = position;
	}
	public void tellYourInfo() {
		System.out.println("my company is " + company);
		System.out.println("my position is " + position);
		super.tellYourName(); // super. -> 부모의 메모리에 접근
	}
	
}
