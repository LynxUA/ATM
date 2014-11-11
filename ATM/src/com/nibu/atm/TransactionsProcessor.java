package com.nibu.atm;

import java.util.ArrayList;
import java.util.List;

public class TransactionsProcessor {
	//These should be processed automatically.
	private List<AutoTransaction> autoTransactions;
	private BankAccountAccess accountAccess;
	
	public TransactionsProcessor(ArrayList<AutoTransaction> autoTransactions, BankAccountAccess accountAccess) {
		this.autoTransactions = autoTransactions;
		this.accountAccess = accountAccess;
		for (int i = 0; i < autoTransactions.size(); i++) {
			Account from = accountAccess.get(autoTransactions.get(i).getCardFromNumber());
			from.autoTransactions.add(autoTransactions.get(i));
		}
	}
	
	public boolean add(Transaction transaction) {
		if (transaction instanceof AutoTransaction) {
			AutoTransaction t = (AutoTransaction) transaction;
			autoTransactions.add(t);
			Account from = accountAccess.get(t.from);
			from.autoTransactions.add(t);
		} else {
			Account from = accountAccess.get(transaction.from);
			Account to = accountAccess.get(transaction.to);
			if (transaction.process(from, to)) {//Transaction was successful
				if (to.protectingAccount != null) {
					if (to.balance > to.protectMoneyAmount) {
						add(new Transaction(to.getCardNumber(), to.getProtectingAccount(), to.balance - to.protectMoneyAmount));
					}
				}
			} else
				return false;
		}
		return true;
	}
	
	public ArrayList<Transaction> getAutoTransactions() {
		return new ArrayList<Transaction>(autoTransactions);
	}
}
