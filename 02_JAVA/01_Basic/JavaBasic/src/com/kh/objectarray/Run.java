package com.kh.objectarray;

public class Run {
	/*
	 * 객체 배열
	 * 같은 타입의 객체 여러개를 하나의 배열에 저장해서 관리하는 것
	 * 단, 배열의 각 요소가 개체 자체를 저장하는게 아니라 객체의 주소(참조값)을 저장
	 * 		배열 생성시 객체까지 자동으로 생성되는 것이 아니라, 각 요소에 객체를 직접 생성해서 넣어야함
	 */
	public static void main(String[] args) {
		
		// 1. 객체 배열 선언 및 생성
		Book[] books = new Book[3]; // Book book1, book2, book3;
		
		books[0] = new Book();
		books[1] = new Book();
		books[2] = new Book();
		
		System.out.println();
		for(Book b : books) 
			System.out.println(b + " ");
		
	}

}
