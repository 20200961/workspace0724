package com.kh.example.oop5;

public class Snack {
	private String kind;
	private String name;
	private String flaver;
	private int numOf;
	private int price;
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlaver() {
		return flaver;
	}
	public void setFlaver(String flaver) {
		this.flaver = flaver;
	}
	public int getNumOf() {
		return numOf;
	}
	public void setNumOf(int numOf) {
		this.numOf = numOf;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String information() {
		return name + "(" + kind + "-" + flaver + ")" + numOf + "-" + price;
	}
}
