package com.nibu.atm;

import java.io.Serializable;
import java.util.Objects;


public class Account implements Serializable {
	private String ownerName;
	private String cardCode;//XXXX-XXXX-XXXX-XXXX format //
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
	
	@Override
	public int hashCode() {
		return Objects.hash(ownerName, cardCode, password);
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
