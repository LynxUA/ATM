package com.nibu.atm;

public class Transaction {
	protected Account from;
	protected Account to;
	protected long moneyAmount;
	
	public Transaction(Account from, Account to, long moneyAmount) {
		this.from = from;
		this.to = to;
		this.moneyAmount = moneyAmount;
	}
	
	public boolean process() {
		if (from.reduceBalance(moneyAmount)) {
			to.balance += moneyAmount;
			return true;
		}
		return false;
	}
	
	public String getCardFromNumber() {
		return from.getCardNumber();
	}
	
	public String getCardToNumber() {
		return to.getCardNumber();
	}
	
	public long getMoneyAmount() {
		return moneyAmount;
	}
}
