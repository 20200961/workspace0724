package com.kh.inherit1;

public class Rectangle extends Circle {
	int width;
	int height;
	
	public Rectangle(){
		super();
	}

	public Rectangle(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return x + ","+y+","+width+","+height;
	}
}
