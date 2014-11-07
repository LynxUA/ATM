package com.nibu.atm;

import java.io.Serializable;

public class Transaction implements Serializable{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + (int) (moneyAmount ^ (moneyAmount >>> 32));
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (moneyAmount != other.moneyAmount)
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

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
