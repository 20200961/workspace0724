package com.kh.example.inherit2;

public class Employee {
	private int salary;
	private String dept;
	
	public Employee() {
		super();
	}
	
	public Employee(int salary, String dept) {
		super();
		this.salary = salary;
		this.dept = dept;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+","+salary+","+dept;
	}
	
	
	
	
}
