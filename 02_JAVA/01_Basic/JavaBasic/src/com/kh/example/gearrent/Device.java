package com.kh.example.gearrent;

import java.util.Set;

public abstract class Device {
	private String id;
	private String name;
	private String category;
	private int borrowCount;
	private Set<String> tag;
	
	public Device() {
		super();
	}

	public Device(String id, String name, String category,Set<String> tag) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.tag = tag;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBorrowCount() {
		return borrowCount;
	}

	public void setBorrowCount(int borrowCount) {
		this.borrowCount = borrowCount;
	}

	public Set<String> getTag() {
		return tag;
	}

	public void setTag(Set<String> tag) {
		this.tag = tag;
	}

	public void increaseBorrowCount() {
		borrowCount++;
	}
	
	public boolean hasTag(String tag) {
		// 잘모르겠어요..
		return true;
	}

	@Override
	public String toString() {
		return "ID: " + id +
		           ", Name: " + name +
		           ", Category: " + category +
		           ", BorrowCount: " + borrowCount +
		           ", Tag: " + tag;
	}
	
	public int getBorrowLimitDays() {
		return 0;
	}
	
	public int calcLateFee(int overdueDays) {
		// overdueDays > 0;
		return overdueDays;
	}
	
}
