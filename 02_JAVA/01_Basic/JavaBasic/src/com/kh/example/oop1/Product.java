package com.kh.example.oop1;

public class Product {
	private String pName;
	private int price;
	private String brand;
	
	
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Product(String pName, int price, String brand) {
		super();
	}
	
	public void information() {
		System.out.println("상품명 : "+pName);
		System.out.println("가격 : "+price);
		System.out.println("브랜드명 : "+brand );
	}
	
}
