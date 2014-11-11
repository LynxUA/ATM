package com.nibu.atm.graphic;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.nibu.atm.ClientInterface;

public class ATM {
	private static ATMGraphic atm = null;
	private static String console = "";
	private static String cardNumber = null;
	private static ClientInterface dao;
	public static void main(String[] args) {
		try {
			dao = (ClientInterface) Naming.lookup("//" + args[0] + ":" + args[1] + "/Bank");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					atm = (ATMGraphic) ATMGraphic.getInstance();
					atm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public static String getConsole(){
		return console;
	}
	public static void setConsole(String console1){
		console = console1;
	}

	public static void setCardNumber(String text) {
		cardNumber = text;
		
	}
	public static String getCardNumber(){
		return cardNumber;
	}
	
	public static ClientInterface getDAO(){
		return dao;
	}
	

}
