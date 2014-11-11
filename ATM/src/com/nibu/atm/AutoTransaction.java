package com.nibu.atm;

import java.io.Serializable;
import java.util.Calendar;

public class AutoTransaction extends Transaction implements Serializable{
	private int dayOfMonth;
	private int monthLastTransfer;
	private String description;
	private long id;
	
	/**
	 * Constructor when we first create transaction(we are not loading it from database)
	 * @param from
	 * @param to
	 * @param moneyAmount
	 * @param monthDay
	 * @param description
	 */
	public AutoTransaction(String from, String to, long moneyAmount, int monthDay, String description, long id) {
		super(from, to, moneyAmount);
		this.dayOfMonth = monthDay;
		this.monthLastTransfer = 0;
		this.description = description;
		this.id = id;
	}
	
	/**
	 * Constructor when we load transaction from database
	 * @param from
	 * @param to
	 * @param moneyAmount
	 * @param monthDay
	 * @param monthLastTransfer
	 * @param description
	 */
	public AutoTransaction(String from, String to, long moneyAmount, int monthDay, int monthLastTransfer, String description) {
		super(from, to, moneyAmount);
		this.dayOfMonth = monthDay;
		this.monthLastTransfer = monthLastTransfer;
		this.description = description;
	}
	
	
	@Override
	public boolean process(Account from, Account to) {
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
	
	void edit(String to, long moneyAmount, int monthDay, String description) {
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
	
	public long getId() {
		return id;
	}
	
}
