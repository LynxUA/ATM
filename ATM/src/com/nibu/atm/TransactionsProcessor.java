package com.nibu.atm;

import java.util.ArrayList;
import java.util.List;

public class TransactionsProcessor {
	//These should be processed automatically.
	private List<Transaction> autoTransactions;
	
	public TransactionsProcessor() {
		autoTransactions = new ArrayList<Transaction>();
	}
	
	public boolean add(Transaction transaction) {
		if (transaction instanceof AutoTransaction) {
			autoTransactions.add(transaction);
			AutoTransaction autoTransaction = (AutoTransaction) transaction;
			autoTransaction.from.autoTransactions.add(autoTransaction);
		} else {
			if (transaction.process()) {//Transaction was successful
				Account to = transaction.to;
				if (to.protectingAccount != null) {
					if (to.balance > to.protectMoneyAmount) {
						add(new Transaction(to, to.protectingAccount, to.balance - to.protectMoneyAmount));
					}
				}
			} else
				return false;
		}
		return true;
	}
}
