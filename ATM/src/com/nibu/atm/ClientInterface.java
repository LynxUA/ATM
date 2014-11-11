package com.nibu.atm;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientInterface extends Remote {
	
	public BankOperationRes authorize(String cardNum, String password) throws RemoteException;
	
	public BankOperationRes changeCreditLimit(String cardNum, long newLimit) throws RemoteException;
	
	public BankOperationRes sendMoney(String cardNumFrom, String cardNumTo, long money) throws RemoteException;
	
	public BankOperationRes addAutoTransaction(String cardNumFrom, String cardNumTo, int dayNumber, long money, String description) throws RemoteException;

	public BankOperationRes editAutoTransaction(AutoTransaction transaction, String cardNumTo, int dayNumber, long money, String description) throws RemoteException;
	
	public BankOperationRes setMoneyExcessLimit(String cardNumFrom, String cardNumTo, long limit) throws RemoteException;
	
	public long getMaxCreditLimit(String cardNum) throws RemoteException;
	
	public long getCreditLimit(String cardNum) throws RemoteException;
	
	public String getName(String cardNum) throws RemoteException;
	
	public long getBalance(String cardNum) throws RemoteException;
	
	public List<AutoTransaction> getAutoTransactions(String cardNum) throws RemoteException;
}
