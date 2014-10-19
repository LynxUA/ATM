package com.nibu.atm;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Bank {
	private static Bank bank = new Bank();
	private HashMap<String, Account> users;
	private TransactionsProcessor transactionsProcessor;
	
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
		//TODO
		transactionsProcessor = new TransactionsProcessor();
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
		if (!transactionsProcessor.add(new Transaction(users.get(cardFrom), users.get(cardTo), money)))
			return BankOperationRes.NOT_ENOUGH_MONEY;
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes addAutoTransaction(String cardFrom, String cardTo, int dayNumber, long money, String description) {
		if (!users.containsKey(cardFrom))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!users.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		if (dayNumber < 1 || dayNumber > 28)
			return BankOperationRes.INVALID_DAY;
		transactionsProcessor.add(new AutoTransaction(users.get(cardFrom), users.get(cardTo), money, dayNumber, description));
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes editAutoTransaction(AutoTransaction transaction, String cardTo, int dayNumber, long money, String description) {
		if (!users.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		if (dayNumber < 1 || dayNumber > 28)
			return BankOperationRes.INVALID_DAY;
		transaction.edit(users.get(cardTo), money, dayNumber, description);
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
	
	/**
	 * 
	 * @return Maximum credit limit that this account may have.(Value may be increased according to bank decision.)
	 */
	public long getMaxCreditLimit(String cardNumber) {
		return users.get(cardNumber).getMaxCreditLimit();
	}
	
	/**
	 * 
	 * @return Current credit limit for this account.
	 */
	public long getCreditLimit(String cardNumber) {
		return users.get(cardNumber).getCreditLimit();
	}
	
	public String getName(String cardNumber) {
		return users.get(cardNumber).getName();
	}
	
	public long getBalance(String cardNumber) {
		return users.get(cardNumber).getBalance();
	}
	
	public List<AutoTransaction> getAutoTransactions(String cardNumber) {
		if (users.containsKey(cardNumber)) {
			Account account = users.get(cardNumber);
			//Not deep copy, just to avoid removing objects from account.autoTransactions
			List<AutoTransaction> result = new ArrayList<AutoTransaction>(account.autoTransactions);
			return result;
		}
		return null;
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
