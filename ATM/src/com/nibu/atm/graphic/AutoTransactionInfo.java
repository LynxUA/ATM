package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.net.ssl.TrustManagerFactorySpi;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import com.nibu.atm.AutoTransaction;
import com.nibu.atm.Bank;
import com.nibu.atm.BankOperationRes;

public class AutoTransactionInfo extends JPanel {

	private static AutoTransaction transaction = null;
	private static JPanel instance = null;
	//private JPanel contentPane;
	private static JTextField recieverField;
	private static JTextField descriptionField;
	private static JTextField dayField;
	private static JTextField amountField;
	private static JEditorPane editorPane;

	private AutoTransactionInfo() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		//contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setBounds(100, 100, 600, 300);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 439, 290);
		panel.setLayout(null);
		this.add(panel);
		
		JLabel editLabel = new JLabel("Редагувати автоматичну транзакцію");
		editLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editLabel.setBounds(119, 5, 283, 23);
		panel.add(editLabel);
		
		JLabel recieverLabel = new JLabel("Номер картки адресата:");
		recieverLabel.setBounds(10, 50, 178, 16);
		panel.add(recieverLabel);
		
		recieverField = new JTextField();
		recieverField.setColumns(10);
		recieverField.setBounds(10, 68, 428, 23);
		recieverField.setText(transaction.getCardToNumber());
		
		panel.add(recieverField);
		
		JLabel descriptionLabel = new JLabel("Опис:");
		descriptionLabel.setBounds(10, 179, 178, 16);
		panel.add(descriptionLabel);
		
		descriptionField = new JTextField();
		descriptionField.setColumns(10);
		descriptionField.setBounds(10, 197, 428, 28);
		descriptionField.setText(transaction.getDescription());
		panel.add(descriptionField);
		
		JLabel dayLabel = new JLabel("День місяця для переказу:");
		dayLabel.setBounds(10, 92, 178, 16);
		panel.add(dayLabel);
		
		dayField = new JTextField();
		dayField.setColumns(10);
		dayField.setBounds(10, 109, 428, 23);
		dayField.setText(String.valueOf(transaction.getDay()));
		panel.add(dayField);
		
		JLabel amountLabel = new JLabel("Сума переказу:");
		amountLabel.setBounds(10, 132, 178, 16);
		panel.add(amountLabel);
		
		amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(10, 152, 184, 28);
		amountField.setText(String.valueOf(transaction.getMoneyAmount()));
		panel.add(amountField);
		
		JButton proceed = new JButton("Обробити");
		proceed.setBounds(43, 255, 117, 29);
		panel.add(proceed);
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(recieverField.getText().equals(ATM.getCardNumber())){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nYou can't give money to yourself\n");
					editorPane.setText(ATM.getConsole());
				}else{
					String reciever = recieverField.getText();
					int day = Integer.parseInt(dayField.getText());
					long amount = Long.parseLong(amountField.getText());
					String description = descriptionField.getText();
					BankOperationRes result = null;
					try {
						result = ATM.getDAO().editAutoTransaction(transaction, reciever, day, amount, description);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(result==BankOperationRes.COMPLETE){
						ATM.setConsole(ATM.getConsole()+"Auto transaction was added:\nSender card number:\n"
						+ATM.getCardNumber()+"\nReciever card number:\n" 
						+ reciever +"\nDay of a month for operation:\n"
						+day+"\nAmount:\n"
						+amount+"\nDescription:\n"
						+description+"\n");
						recieverField.setText("");
						amountField.setText("");
						descriptionField.setText("");
						dayField.setText("");
						editorPane.setText(ATM.getConsole());
						
						AutoTransactions.refreshTransactions();
					}else if(result == BankOperationRes.NO_ACCOUNT_TO_SEND){
						ATM.setConsole(ATM.getConsole()+"Operation denied\nCheck account number\n");
						editorPane.setText(ATM.getConsole());
					}else if(result == BankOperationRes.INVALID_DAY){
						ATM.setConsole(ATM.getConsole()+"Operation denied\nInvalid day\n");
						editorPane.setText(ATM.getConsole());
					}else{
						System.err.println("Unexpectable result");
					}
				}
			}
		});
		
		JButton back = new JButton("Повернутися");
		back.setBounds(0, 3, 107, 29);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)AutoTransactionInfo.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = AutoTransactions.getInstance();
				MainMenu.refresh();
				mainFrame.getContentPane().add(ATPanel);
				mainFrame.setContentPane(ATPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel.add(back);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(456, 7, 138, 288);
		add(panel_1);
		
		editorPane= new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.getConsole());
		//pane.setBounds(0, 0, 138, 288);
		
		JScrollPane scrollPane2 = new JScrollPane(editorPane);
		scrollPane2.setBounds(0, 0, 138, 288);
		panel_1.add(scrollPane2);
	}
	public static void setTransaction(AutoTransaction transaction){
		AutoTransactionInfo.transaction = transaction;
		System.out.println("Транзакція: \nНомер картки: "+transaction.getCardToNumber()+"\nAmount: "+transaction.getMoneyAmount() +"\nDescription: "+transaction.getDescription()+ "\nDay: "+ transaction.getDay());
		
		if(instance == null)
			instance = new AutoTransactionInfo();
		recieverField.setText(transaction.getCardToNumber());
		descriptionField.setText(transaction.getDescription());
		dayField.setText(String.valueOf(transaction.getDay()));
		amountField.setText(String.valueOf(transaction.getMoneyAmount()));
	}
	public static JPanel getInstance(){
		return instance;
	}

}
