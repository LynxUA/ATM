package com.nibu.atm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class Account implements Serializable {
	List<AutoTransaction> autoTransactions = new ArrayList<AutoTransaction>();
	/** Each card when account created has base credit limit*/
	public static final long BASE_CREDIT_LIMIT = 1500;
	/** Basically credit limit may be expanded to this value */
	public static final long BASE_MAX_CREDIT_LIMIT = 5000;
	private String ownerName;
	private String cardNumber;//XXXX-XXXX-XXXX-XXXX format
	private String password;
	/** When account created this value is equals to BASE_MAX_CREDIT_LIMIT. 
	 *  Later it may be expanded if user has good credit history. It may
	 *  be expanded according to bank decision.
	 * */
	private long maxCreditLimit;
	/**  How much money may be taken as credit money */
	private long creditLimit;
	long balance;
	String protectingAccount;
	long protectMoneyAmount;
	
	public Account(String name, String cardNumber, String password) {
		this.ownerName = name;
		this.cardNumber = cardNumber;
		this.password = password;
		creditLimit = BASE_CREDIT_LIMIT;
		maxCreditLimit = BASE_MAX_CREDIT_LIMIT;
		balance = 0;
		protectingAccount = null;
		protectMoneyAmount = 0;
	}
	
	public Account(String ownerName, String cardNumber, String password, 
				  long maxCreditLimit, long creditLimit, long balance, 
				  String protectingAccount, long protectMoneyAmount) {
		this.ownerName = ownerName;
		this.cardNumber = cardNumber;
		this.password = password;
		this.maxCreditLimit = maxCreditLimit;
		this.creditLimit = creditLimit;
		this.balance = balance;
		this.protectingAccount = protectingAccount;
		this.protectMoneyAmount = protectMoneyAmount;
	}
	
	public boolean checkPassword(String cardNumber, String password) {
		return this.cardNumber.equals(cardNumber) && this.password.equals(password);
	}
	
	/**
	 * Changes credit limit for this account.
	 * @param newValue
	 * @return Returns false if new credit limit is bigger, than maximum credit limit available
	 * for this account. Otherwise true.
	 */
	public boolean expandCreditLimit(long newValue) {
		if (newValue > maxCreditLimit)
			return false;
		creditLimit = newValue;
		return true;
	}
	
	public void setMoneyExcessLimit(String protectingAccount, long limit) {
		this.protectingAccount = protectingAccount;
		this.protectMoneyAmount = limit;
	}
	
	public boolean reduceBalance(long money) {
		if (balance + creditLimit - money >= 0) {
			balance -= money;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return Maximum credit limit that this account may have.(Value may be increased according to bank decision.)
	 */
	public long getMaxCreditLimit() {
		return maxCreditLimit;
	}
	
	/**
	 * 
	 * @return Current credit limit for this account.
	 */
	public long getCreditLimit() {
		return creditLimit;
	}
	
	public String getName() {
		return ownerName;
	}
	
	public long getBalance() {
		return balance;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	@Override
	public String toString() {
		return ownerName + " " + cardNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result
				+ ((ownerName == null) ? 0 : ownerName.hashCode());
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
		Account other = (Account) obj;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		return true;
	}

	public String getProtectingAccount() {
		return protectingAccount;
	}

}
