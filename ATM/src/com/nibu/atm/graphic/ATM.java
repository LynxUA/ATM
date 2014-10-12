package com.nibu.atm.graphic;

import java.awt.EventQueue;

public class ATM {
	private static ATMGraphic atm = null;
	static String console = "";
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

}
