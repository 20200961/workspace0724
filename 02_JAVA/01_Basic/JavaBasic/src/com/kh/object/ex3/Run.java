package com.kh.object.ex3;

public class Run {

	public static void main(String[] args) {
		Math m = new Math();
		System.out.println(m.adder(10,20));
		System.out.println(m.adder(10));
		System.out.println(m.adder(10.0,30.0));
		System.out.println(m.adder('a','b'));
		System.out.println(m.adder(10,"살입니다."));
		System.out.println(m.adder("나이 : ",10));

	}

}
