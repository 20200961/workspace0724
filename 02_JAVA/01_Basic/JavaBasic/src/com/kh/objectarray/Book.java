package com.kh.objectarray;

public class Book {
	// 필드(private) -> 정보은닉
	private String title;
	private String genre;
	private String author;
	private int maxPage;
	
	public Book() {
		super();
	}
	
	public Book(String title, String genre, String author, int maxPage) {
		super();
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.maxPage = maxPage;
	}
	// Getter, Setter
	// alt+shift+s-> r : 생성 단축키
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	
	
}
