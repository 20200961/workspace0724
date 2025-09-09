package com.kh.example.gearrent;

public class Member {
	private String id;
	private String name;
	
	public Member() {
		super();
	}

	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Member{" +"id='" + id + '\'' +
            ", name='" + name+"}";
	}
	
	
	
	
}
