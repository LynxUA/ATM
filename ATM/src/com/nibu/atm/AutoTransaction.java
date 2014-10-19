package com.nibu.atm;

import java.util.Calendar;

public class AutoTransaction extends Transaction{
	private int dayOfMonth;
	private int monthLastTransfer;
	
	public AutoTransaction(Account from, Account to, long moneyAmount, int monthDay) {
		super(from, to, moneyAmount);
		this.dayOfMonth = monthDay;
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
	
}
