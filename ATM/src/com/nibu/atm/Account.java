package com.nibu.atm;

import java.io.Serializable;
import java.util.Objects;


public class Account implements Serializable {
	/** Each card when account created has base credit limit*/
	public static final long BASE_CREDIT_LIMIT = 1500;
	/** Basically credit limit may be expanded to this value */
	public static final long BASE_MAX_CREDIT_LIMIT = 5000;
	/** When account created this value is equals to BASE_MAX_CREDIT_LIMIT. 
	 *  Later it may be expanded if user has good credit history. It may
	 *  be expanded according to bank decision.
	 * */
	private long maxCreditLimit;
	/**  How much money may be taken as credit money */
	private long creditLimit;
	private String ownerName;
	private String cardCode;//XXXX-XXXX-XXXX-XXXX format
	private String password;
	long balance;
	Account protectingAccount;
	long protectMoneyAmount;
	
	public Account(String name, String code, String password) {
		this.ownerName = name;
		this.cardCode = code;
		this.password = password;
		creditLimit = BASE_CREDIT_LIMIT;
		maxCreditLimit = BASE_MAX_CREDIT_LIMIT;
		balance = 0;
	}
	
	public boolean checkPassword(String cardCode, String password) {
		return this.cardCode.equals(cardCode) && this.password.equals(password);
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
		maxCreditLimit = newValue;
		return true;
	}
	
	public void setMoneyExcessLimit(Account protectingAccount, long limit) {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(ownerName, cardCode, password);
	}
	
	@Override
	public String toString() {
		return ownerName + " " + cardCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Account) {
			Account a = (Account) obj;
			if (a.ownerName.equals(ownerName) && a.cardCode.equals(cardCode) && a.password.equals(password))
				return true;
		}
		return false;
	}

}
