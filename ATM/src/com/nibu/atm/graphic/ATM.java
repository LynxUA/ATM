package com.nibu.atm.graphic;

import java.awt.EventQueue;

public class ATM {
	private static ATMGraphic atm = null;
	private static String console = "";
	private static String cardNumber = null;
	public static void main(String[] args) {
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
	

}
