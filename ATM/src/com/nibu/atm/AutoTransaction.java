package com.nibu.atm;

import java.util.Calendar;

public class AutoTransaction extends Transaction{
	private int dayOfMonth;
	private int monthLastTransfer;
	private String description;
	
	public AutoTransaction(Account from, Account to, long moneyAmount, int monthDay, String description) {
		super(from, to, moneyAmount);
		this.dayOfMonth = monthDay;
		this.description = description;
		this.monthLastTransfer = 0;
	}
	
	
	@Override
	public boolean process() {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
		if (month != monthLastTransfer && dayOfMonth == this.dayOfMonth) {//We didn't make transfer in this month and today is transfer day
			if (from.reduceBalance(moneyAmount)) {//Taking money from first account was successful
				to.balance += moneyAmount;
			} else {
				return false;
			}
		}
		return true;
	}
	
	void edit(Account to, long moneyAmount, int monthDay, String description) {
		this.to = to;
		this.moneyAmount = moneyAmount;
		this.dayOfMonth = monthDay;
		this.description = description;
	}
	
	public int getDay() {
		return dayOfMonth;
	}
	
	public String getDescription() {
		return description;
	}
	
}
