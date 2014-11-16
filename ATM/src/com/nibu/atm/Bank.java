package com.nibu.atm;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank extends UnicastRemoteObject implements ClientInterface {
	private static final String oracle = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String jdbcURL = oracle;
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static Connection conn;
	private TransactionsProcessor transactionsProcessor;
	
	/** From the start this map is empty. If someone requests account operation, 
	 * this account will be searched in this map and if not found - 
	 * in database. After that account will be created from database information
	 * and put in this map.
	 * String - cardNumber
	 * Account - Account associated with this cardCode */
	private HashMap<String, Account> loadedAccounts = new HashMap<String, Account>() {
		@Override
		public Account get(Object o) {
			if (super.get(o) == null) {
				try {
					PreparedStatement statement = conn.prepareStatement("SELECT * FROM accounts WHERE cardNumber = ?");
					statement.setString(1, (String) o);
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						Account result = new Account(rs.getString("ownerName"), rs.getString("cardNumber"), 
											 rs.getString("password"), rs.getLong("maxCreditLimit"), 
											 rs.getLong("creditLimit"), rs.getLong("balance"), 
											 rs.getString("protectingAccount"), rs.getLong("protectMoneyAmount"));
						put((String) o, result);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return super.get(o);
		}
		
		@Override
		public boolean containsKey(Object o) {
			return get(o) != null;
		}
	};
	
	public void initialiseTablesAndData() {
		try (Statement s = conn.createStatement()) {
			s.execute("CREATE TABLE accounts (" +
			 		"cardNumber VARCHAR(19) NOT NULL PRIMARY KEY," +
					"maxCreditLimit NUMBER(18) NOT NULL," +
					"creditLimit NUMBER(18) NOT NULL," +
					"ownerName VARCHAR(20) NOT NULL," +
					"password VARCHAR(50) NOT NULL," +
					"balance NUMBER(18)," +
					"protectingAccount VARCHAR(19)," +
					"protectMoneyAmount NUMBER(18))");
			
			s.executeUpdate("CREATE TABLE autotransactions (" +
					"transaction_id NUMBER(18) PRIMARY KEY," +
					"fromAccount VARCHAR(19) NOT NULL," +
					"toAccount VARCHAR(19) NOT NULL," +
					"moneyAmount NUMBER(18) NOT NULL," +
					"monthDay NUMBER(5) NOT NULL," +
					"monthLastTransfer NUMBER(5) NOT NULL," +
					"description VARCHAR(200) NOT NULL)");
					
			s.executeUpdate("CREATE SEQUENCE TRANSACTION_SEQ " +
							"START WITH 0 " +
							"MAXVALUE 999999999999999999 " +
							"MINVALUE 0 ");
			
			addUser("1111-2222-3333-4444", //cardNumber
					 5000,				   //maxCreditLimit
					 1500,				   //creditLimit (current one)
					 "Alex Nikitin",	   //name of owner
					 "alex123",			   //password
					 534789,			   //balance
					 null,				   //protecting account
					 0);				   //protecting money
			
			addUser("5555-6666-7777-8888",
					 5000,
					 1500,
					 "Denis Burlakov",
					 "denis456",
					 534789,
					 null,
					 0);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addUser(String s1, long s2, long s3, String s4, String s5, long s6, String s7, long s8) {
		try (PreparedStatement statement = conn.prepareStatement("INSERT INTO accounts " +
				"(cardNumber, maxCreditLimit, creditLimit, ownerName, password, balance, protectingAccount, protectMoneyAmount) values" +
				"(?, ?, ?, ?, ?, ?, ?, ?)");) {
			
			statement.setString(1, s1);
			statement.setLong(2, s2);
			statement.setLong(3, s3);
			statement.setString(4, s4);
			statement.setString(5, s5);
			statement.setLong(6, s6);
			statement.setString(7, s7);
			statement.setLong(8, s8);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	Bank() throws RemoteException {
		try {
			Class.forName (driver);
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver not found: " + driver);
		}
		try {
			conn = DriverManager.getConnection(jdbcURL, "alex", "*");
		} catch (SQLException e) {
			System.out.println("Unable to establish connection");
		}
		
		//System.out.println("STARTING TO MAKE TABLES");   initialiseTablesAndData();
		
		
		ArrayList<AutoTransaction> transactions = new ArrayList<AutoTransaction>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM autotransactions");
			while (rs.next()) {
				AutoTransaction t = new AutoTransaction(rs.getString("fromAccount"), 
														rs.getString("toAccount"), 
														rs.getLong("moneyAmount"), rs.getInt("monthDay"), 
														rs.getInt("monthLastTransfer"), rs.getString("description"));
				transactions.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		transactionsProcessor = new TransactionsProcessor(transactions, new BankAccountAccess() {
			@Override
			public Account get(String cardNumber) {
				return loadedAccounts.get(cardNumber);
			}
		});
		
		System.out.println("EVERYTHING INITIALISED OKAY");
	}
	
	public BankOperationRes authorize(String cardCode, String password) {
		if (!loadedAccounts.containsKey(cardCode))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!loadedAccounts.get(cardCode).checkPassword(cardCode, password))
			return BankOperationRes.INVALID_PASSWORD;
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes changeCreditLimit(String cardCode, long newLimit) {
		if (!loadedAccounts.containsKey(cardCode))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!loadedAccounts.get(cardCode).expandCreditLimit(newLimit))
			return BankOperationRes.LIMIT_OVERFLOW;
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes sendMoney(String cardFrom, String cardTo, long money) {
		if (!loadedAccounts.containsKey(cardFrom))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!loadedAccounts.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		if (!transactionsProcessor.add(new Transaction(cardFrom, cardTo, money)))
			return BankOperationRes.NOT_ENOUGH_MONEY;
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes addAutoTransaction(String cardFrom, String cardTo, int dayNumber, long money, String description) {
		if (!loadedAccounts.containsKey(cardFrom))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!loadedAccounts.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		if (dayNumber < 1 || dayNumber > 28)
			return BankOperationRes.INVALID_DAY;
		try (PreparedStatement statement = conn.prepareStatement("INSERT INTO autoTransactions " +
				"(transaction_id, fromAccount, toAccount, moneyAmount, monthDay, monthLastTransfer, description) values" +
				"(transaction_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)", new String[] { "transaction_id" });) {
			
			statement.setString(1, cardFrom);
			statement.setString(2, cardTo);
			statement.setLong(3, money);
			statement.setInt(4, dayNumber);
			statement.setInt(5, -1);
			statement.setString(6, description);
			
			long id = 0;
			if (statement.executeUpdate() > 0) {
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys != null && generatedKeys.next()) {
						id = generatedKeys.getLong(1);
						transactionsProcessor.add(new AutoTransaction(cardFrom, cardTo, money, dayNumber, description, id));
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		return BankOperationRes.COMPLETE;
	}
	
	public BankOperationRes editAutoTransaction(AutoTransaction transaction, String cardTo, int dayNumber, long money, String description) {
		if (!loadedAccounts.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		if (dayNumber < 1 || dayNumber > 28)
			return BankOperationRes.INVALID_DAY;
		long id = transaction.getId();
		ArrayList<Transaction> list = transactionsProcessor.getAutoTransactions();
		for (int i = 0; i < list.size(); i++) {
			AutoTransaction a = (AutoTransaction) list.get(i);
			if (a.getId() == id) {
				a.edit(cardTo, money, dayNumber, description);
				break;
			}
		}
		try {
			PreparedStatement statement = conn.prepareStatement("UPDATE autotransactions SET " +
					"toaccount = ?, moneyamount = ?, monthday = ?, description = ? " +
					"WHERE transaction_id = ?");
			statement.setString(1, cardTo);
			statement.setLong(2, money);
			statement.setInt(3, dayNumber);
			statement.setString(4, description);
			statement.setLong(5, transaction.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		if (!loadedAccounts.containsKey(cardFrom))
			return BankOperationRes.NO_SUCH_ACCOUNT;
		if (!loadedAccounts.containsKey(cardTo))
			return BankOperationRes.NO_ACCOUNT_TO_SEND;
		loadedAccounts.get(cardFrom).setMoneyExcessLimit(cardTo, limit);
		return BankOperationRes.COMPLETE;
	}
	
	/**
	 * 
	 * @return Maximum credit limit that this account may have.(Value may be increased according to bank decision.)
	 */
	public long getMaxCreditLimit(String cardNumber) {
		return loadedAccounts.get(cardNumber).getMaxCreditLimit();
	}
	
	/**
	 * 
	 * @return Current credit limit for this account.
	 */
	public long getCreditLimit(String cardNumber) {
		return loadedAccounts.get(cardNumber).getCreditLimit();
	}
	
	public String getName(String cardNumber) {
		return loadedAccounts.get(cardNumber).getName();
	}
	
	public long getBalance(String cardNumber) {
		return loadedAccounts.get(cardNumber).getBalance();
	}
	
	public ArrayList<AutoTransaction> getAutoTransactions(String cardNumber) {
		System.out.println("CARD NUMBER: " + cardNumber);
		if (loadedAccounts.containsKey(cardNumber)) {
			Account account = loadedAccounts.get(cardNumber);
			//Not deep copy, just to avoid removing objects from account.autoTransactions
			return account.autoTransactions;
		}
		System.out.println("NEAR NULL");
		return null;
	}
	
	@Override
	public void exit() {
		try(PreparedStatement statement = conn.prepareStatement("UPDATE accounts SET " +
				"maxCreditlimit = ?, creditLimit = ?, balance = ?, " +
				"protectingAccount = ?, protectMoneyAmount = ? WHERE cardNumber = ?");){
			for (Map.Entry<String, Account> entry : loadedAccounts.entrySet()) {
			    Account a = entry.getValue();
			    statement.setLong(1, a.getMaxCreditLimit());
				statement.setLong(2, a.getCreditLimit());
				statement.setLong(3, a.getBalance());
				statement.setString(4, a.protectingAccount);
				statement.setLong(5, a.protectMoneyAmount);
				statement.setString(6, a.getCardNumber());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
