package com.nibu.atm;

public class Transaction {
	protected String from;
	protected String to;
	protected long moneyAmount;
	
	public Transaction(String from, String to, long moneyAmount) {
		this.from = from;
		this.to = to;
		this.moneyAmount = moneyAmount;
	}
	
	public boolean process(Account from, Account to) {
		if (from.reduceBalance(moneyAmount)) {
			to.balance += moneyAmount;
			return true;
		}
		return false;
	}
	
	public String getCardFromNumber() {
		return from;
	}
	
	public String getCardToNumber() {
		return to;
	}
	
	public long getMoneyAmount() {
		return moneyAmount;
	}
}
