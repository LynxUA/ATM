package com.nibu.atm;
import java.io.Serializable;


public class Account implements Serializable{
	private String ownerName;
	private String cardCode;//XXXX-XXXX-XXXX-XXXX format
	private String password;
	
	public Account(String name, String code, String password) {
		this.ownerName = name;
		this.cardCode = code;
		this.password = password;
	}
	
	public boolean checkPassword(String cardCode, String password) {
		return this.cardCode.equals(cardCode) && this.password.equals(password);
	}
	
	public String getName() {
		return ownerName;
	}
	
	public String toString() {
		return ownerName + " " + cardCode;
	}

}
