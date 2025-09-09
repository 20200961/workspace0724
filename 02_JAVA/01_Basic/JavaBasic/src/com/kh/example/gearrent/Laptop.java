package com.kh.example.gearrent;

public class Laptop extends Device {
	@Override
	public int getBorrowLimitDays() {
		return super.getBorrowLimitDays();
	}

	@Override
	public int calcLateFee(int overdueDays) {
		return super.calcLateFee(overdueDays);
	}
}
