package com.nibu.atm;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ATM extends JFrame{

	public ATM() {
	Toolkit kit=Toolkit.getDefaultToolkit();
	Dimension screenSize=kit.getScreenSize();
	int screenHeight=screenSize.height;
	int screenWidth=screenSize.width;
	setSize(screenWidth/2,screenHeight/2);
	setLocation(screenWidth/4,screenHeight/4);
	setTitle("Login");
	final JPanel panel = new JPanel();
	final JTextField field = new JTextField("Введіть текст");
	//field.set
	JButton button = new JButton("OK");
	ActionListener listener = new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
	//JOptionPane.showMessageDialog(panel, "Hello, "+field.getText());
		ATM.this.remove(panel);
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel(" Hello, "+field.getText()));
		ATM.this.add(panel1);
		ATM.this.setVisible(true);
		ATM.this.repaint();
	//JLabel label = new JLabel("Hello, "+field.getText());

	}
	};
	button.addActionListener(listener);
	field.addActionListener(listener);
	panel.add(field);
	panel.add(button);

	this.add(panel);
	}

	public static void main(String[] args) {
	ATM atm = new ATM();
	atm.setDefaultCloseOperation(EXIT_ON_CLOSE);
	atm.setVisible(true);
	
	//JFrame frame = new JFrame();
	
	//frame.pack();
	//frame.setVisible(true);

	}

}
