package com.kh.example.abstractNInterface;

public abstract class SmartPhone {
	public String maker;

	public SmartPhone() {
		super();
	}
	
	public SmartPhone(String maker) {
		super();
		this.maker = maker;
	}
	// 각 인터페이스의 메서드들의 반환값을 모두 조합해서 반환(자식에서 구현)
	public abstract String printInformation();
	
	public void setMaker(String maker) {
		this.maker = maker;
	}

	public abstract String getMaker();
	
	
	
}
