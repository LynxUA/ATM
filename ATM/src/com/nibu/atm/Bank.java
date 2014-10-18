package com.nibu.atm;
import java.io.File;
import java.util.HashMap;


public class Bank {
	private static Bank bank = new Bank();
	public HashMap<String, Account> users;
	
	private Bank() {
		File accountsStored = new File("Data.txt");
		if(accountsStored.exists() && !accountsStored.isDirectory()) {
			Object obj = FileIO.loadFile(accountsStored.getName());
			if (obj instanceof HashMap<?, ?>) {
				users = (HashMap<String, Account>) obj;
			}
		} 
		if (users == null)
			users = new HashMap<String, Account>();
	}
	
	public static Bank getInstance() {
		return bank;
	}
	
	public BankOperationRes authorize(String cardCode, String password) {
		if (!users.containsKey(cardCode))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!users.get(cardCode).checkPassword(cardCode, password))
			return BankOperationRes.INVALID_PASSWORD;
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes changeCreditLimit(String cardCode, long newLimit) {
		if (!users.containsKey(cardCode))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!users.get(cardCode).expandCreditLimit(newLimit))
			return BankOperationRes.LIMIT_OVERFLOW;
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes sendMoney(String cardFrom, String cardTo, long money) {
		if (!users.containsKey(cardFrom))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!users.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		if (!users.get(cardFrom).reduceMoney(money))
			return BankOperationRes.NOT_ENOUGH_MONEY;
		users.get(cardTo).increaseMoney(money);
		return BankOperationRes.COMPLETE;
	}
	
	/**
	 * If account after transaction has more money than limit, the rest of money would be sent to another account.
	 * @param cardFrom A card we set limit for
	 * @param cardTo A card we send the rest to
	 * @param limit Money limit
	 * @return
	 */
	public BankOperationRes setMoneyExcessLimit(String cardFrom, String cardTo, long limit) {
		if (!users.containsKey(cardFrom))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!users.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		users.get(cardFrom).setMoneyExcessLimit(users.get(cardTo), limit);
		return BankOperationRes.COMPLETE;
	}
	
	//Testing
	public static void main(String[] args) {
		
		Bank bank = Bank.bank;
		//bank.users.clear();
		//bank.users.put("ALEX", new Account("Alex", "ALEX", "123"));
		//bank.users.put("DENIS", new Account("Denis", "DENIS", "321"));
		//bank.users.put("XXXX-XXXX-XXXX-XXXX", new Account("Alex", "XXXX-XXXX-XXXX-XXXX", "qwerty"));/
		//System.out.println(bank.users.get("DENIS"));
		FileIO.saveFile(bank.users, "Data.txt");
	}
}
