package com.kh.example.gearrent;

import java.time.LocalDate;

public class Loan {
	private String itemId;
	private String memberId;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnedDate;
	
	public Loan(String itemId, String memberId, LocalDate loanDate, LocalDate dueDate) {
		super();
		this.itemId = itemId;
		this.memberId = memberId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
	}
	
	public boolean isOverdue(LocalDate today) {
		return true;
	}
	
	public int overdueDays(LocalDate today) {
		return 0;
	}

	@Override
	public String toString() {
		return "ItemLoan{" +
	            "itemId='" + itemId + '\'' +
	            ", memberId='" + memberId + '\'' +
	            ", loanDate=" + loanDate +
	            ", dueDate=" + dueDate +
	            '}';
	}
	
	
	
}
