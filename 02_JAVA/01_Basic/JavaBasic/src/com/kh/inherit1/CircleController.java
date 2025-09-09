package com.kh.inherit1;

public class CircleController extends Circle{
	Circle c = new Circle();

	public CircleController() {
		super();
	}
	
	public String calcArea(int x, int y, int radius) {
		c.setX(x);
        c.setY(y);
        c.setRadius(radius);
        
        double area = Math.PI * c.getRadius() * c.getRadius();
        return c.getX() + ", " + c.getY() + ", " + c.getRadius() + " / 면적: " + area;
	}
	
	public String calcCircum(int x, int y, int radius) {
		c.setX(x);
        c.setY(y);
        c.setRadius(radius);
        
        double circum = Math.PI * c.getRadius() * 2;
        return c.getX() + ", " + c.getY() + ", " + c.getRadius() + " / 둘레: " + circum;
	}

	
	
}
