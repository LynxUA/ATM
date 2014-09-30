import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Bank {
	private static Bank bank = new Bank();
	public ArrayList<Account> users;
	
	private Bank() {
		File accountsStored = new File("Data.txt");
		if(accountsStored.exists() && !accountsStored.isDirectory()) {
			users = (ArrayList<Account>)FileIO.loadFile(accountsStored.getName());
		}
		if (users == null)
			users = new ArrayList<Account>();
	}
	
	public static Bank getInstance() {
		return bank;
	}
	
	//Testing
	public static void main(String[] args) {
		
		Bank bank = Bank.bank;
		//bank.users.add(new Account("Alex", "XXXX-XXXX-XXXX-XXXX", "qwerty"));
		System.out.println(bank.users.get(0));
		{
			//do some job
		}
		FileIO.saveFile(bank.users, "Data.txt");
	}
}
