package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

import com.nibu.atm.Bank;
import com.nibu.atm.BankOperationRes;

public class MainMenu extends JPanel {

	private static  JPanel instance = new MainMenu();
	private JTextField cardField;
	private JTextField summField;
	private JEditorPane editorPane;

	/**
	 * Create the frame.
	 */
	private MainMenu() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setBounds(100, 100, 600, 300);
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 181, 288);
		this.add(panel);
		panel.setLayout(null);
		
		JButton exceedingCredit = new JButton("Кредитний ліміт");
		exceedingCredit.setBounds(6, 5, 169, 29);
		exceedingCredit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)MainMenu.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ECPanel = ExceedingCredit.getInstance();
				mainFrame.getContentPane().add(ECPanel);
				mainFrame.setContentPane(ECPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel.add(exceedingCredit);
		
		JButton pcs = new JButton("Грошові залишки");
		pcs.setBounds(6, 46, 169, 29);
		pcs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)MainMenu.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel PCSPanel = PCS.getInstance();
				mainFrame.getContentPane().add(PCSPanel);
				mainFrame.setContentPane(PCSPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel.add(pcs);
		
		JButton autoTransactions = new JButton("Автоматичні перекази");
		autoTransactions.setBounds(6, 87, 169, 29);
		autoTransactions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)MainMenu.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = AutoTransactions.getInstance();
				mainFrame.getContentPane().add(ATPanel);
				mainFrame.setContentPane(ATPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel.add(autoTransactions);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(199, 6, 245, 288);
		this.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel sendLabel = new JLabel("Переказати кошти");
		sendLabel.setBounds(66, 6, 117, 16);
		panel_1.add(sendLabel);
		
		JLabel cardLabel = new JLabel("Номер карти одержувача:");
		cardLabel.setBounds(6, 33, 177, 16);
		panel_1.add(cardLabel);
		
		cardField = new JTextField();
		cardField.setBounds(6, 50, 233, 28);
		panel_1.add(cardField);
		cardField.setColumns(10);
		
		JLabel summLabel = new JLabel("Сума:");
		summLabel.setBounds(6, 80, 61, 16);
		panel_1.add(summLabel);
		
		summField = new JTextField();
		summField.setBounds(6, 100, 233, 28);
		panel_1.add(summField);
		summField.setColumns(10);
		
		JButton send = new JButton("Надіслати");
		send.setBounds(66, 253, 117, 29);
		panel_1.add(send);
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BankOperationRes result = Bank.getInstance().sendMoney(ATM.getCardNumber(), cardField.getText(), Long.parseLong(summField.getText()));
				if(result==BankOperationRes.COMPLETE){
					ATM.setConsole(ATM.getConsole()+"Money was sent\n");
					cardField.setText("");
					summField.setText("");
				}else if(result == BankOperationRes.NO_ACCOUNT_TO_SEND){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nCheck the reciever card number\n");
					editorPane.setText(ATM.getConsole());
				}else if(result == BankOperationRes.NOT_ENOUGH_MONEY){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nYou don't have enough money\n");
					editorPane.setText(ATM.getConsole());
				}else{
					System.err.println("Unexpectable result");
				}
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(456, 6, 138, 288);
		add(panel_2);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.getConsole());
		editorPane.setBounds(0, 0, 138, 288);
		panel_2.add(editorPane);
	}
	public static JPanel getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

}
