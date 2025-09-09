package com.kh.example.poly2;

public class CookBook extends Book{
	private boolean CookBook;
	
	public CookBook() {
		super();
	}

	public CookBook(String title, String author, String publisher, boolean cookBook) {
		super(title, author, publisher);
		CookBook = cookBook;
	}

	public boolean isCookBook() {
		return CookBook;
	}

	public void setCookBook(boolean cookBook) {
		CookBook = cookBook;
	}
	
	public String toString(String title, String author, String publisher, boolean cookBook) {
		return title+", "+author+", "+publisher+", "+cookBook;
	}
	
	
	
}
