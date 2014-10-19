package com.nibu.atm.graphic;

import javax.swing.JEditorPane;

public class Console extends JEditorPane{
	private static Console instance = new Console();
	private Console(){
		this.setEditable(false);
		this.setText("");
		this.setBounds(0, 0, 138, 288);
	}
	public static JEditorPane getInstance(){
		return instance;
	}
}
