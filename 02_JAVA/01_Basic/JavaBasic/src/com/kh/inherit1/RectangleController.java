package com.kh.inherit1;

public class RectangleController {
	Rectangle r = new Rectangle();
	
	public RectangleController() {
		super();
	}
	
	public String calcArea(int x, int y, int height, int width) {
		r.setX(x);
        r.setY(y);
        r.setHeight(height);
        r.setWidth(width);

        int area = r.getHeight() * r.getWidth();
        return r.getX() + ", " + r.getY() + ", " + r.getHeight() + ", " + r.getWidth() + " / 면적: " + area;
	}
	
	public String calcPerimeter(int x, int y, int height, int width){
		r.setX(x);
        r.setY(y);
        r.setHeight(height);
        r.setWidth(width);

        int perimeter = 2 * (r.getHeight() + r.getWidth());
        return r.getX() + ", " + r.getY() + ", " + r.getHeight() + ", " + r.getWidth() + " / 둘레: " + perimeter;
	}

	
}
