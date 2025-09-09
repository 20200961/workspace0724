package com.kh.thread.runable;

//스레드를 만드는 방법 2
//Thread 클래스를 상속받은 클래스를 작성
public class Task extends Thread {

	@Override
	public void run() {
		int n1 = 20;
		int n2 = 30;

		String name = Thread.currentThread().getName();
		System.out.println(name + " : " + (n1+n2));
	}

}
