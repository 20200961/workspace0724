package com.kh.inherit1;

public class Circle extends Point{
	int radius;

	public Circle() {
		super();
	}
	
	public Circle(int x,int y,int radius) {
		super(x,y);
		this.radius = radius;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public String toString() {
		return x+", "+y+", "+radius;
	}
	
	
	
	
}
