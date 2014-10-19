package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import com.nibu.atm.Bank;
import com.nibu.atm.BankOperationRes;

public class AutoTransactionInfo extends JPanel {

	private static JPanel instance = new AutoTransactionInfo();
	//private JPanel contentPane;
	private JTextField reciverField;
	private JTextField descriptionField;
	private JTextField dayField;
	private JTextField amountField;
	private JEditorPane editorPane;

	public AutoTransactionInfo() {
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
		
		JLabel receiverLabel = new JLabel("Номер картки адресата:");
		receiverLabel.setBounds(10, 50, 178, 16);
		panel.add(receiverLabel);
		
		reciverField = new JTextField();
		reciverField.setColumns(10);
		reciverField.setBounds(10, 68, 428, 23);
		panel.add(reciverField);
		
		JLabel descriptionLabel = new JLabel("Опис:");
		descriptionLabel.setBounds(10, 179, 178, 16);
		panel.add(descriptionLabel);
		
		descriptionField = new JTextField();
		descriptionField.setColumns(10);
		descriptionField.setBounds(10, 107, 428, 28);
		panel.add(descriptionField);
		
		JLabel dayLabel = new JLabel("День місяця для переказу:");
		dayLabel.setBounds(10, 92, 178, 16);
		panel.add(dayLabel);
		
		dayField = new JTextField();
		dayField.setColumns(10);
		dayField.setBounds(10, 152, 428, 23);
		panel.add(dayField);
		
		JLabel amountLabel = new JLabel("Сума переказу:");
		amountLabel.setBounds(10, 132, 178, 16);
		panel.add(amountLabel);
		
		amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(10, 195, 184, 28);
		panel.add(amountField);
		
		JButton proceed = new JButton("Обробити");
		proceed.setBounds(43, 255, 117, 29);
		panel.add(proceed);
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*BankOperationRes result = Bank.getInstance().addAutoTransaction(ATM.getCardNumber(), reciverField.getText(), dayNumber, money);
				if(result==BankOperationRes.COMPLETE){
					ATM.setConsole(ATM.getConsole()+"Limit changed to " + limitField.getText() +"\n");
					limitField.setText("");
				}else if(result == BankOperationRes.LIMIT_OVERFLOW){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nThe limit is higher then bank can give\n");
					editorPane.setText(ATM.getConsole());
				}else{
					System.err.println("Unexpectable result");
				}*/
				
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
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.getConsole());
		editorPane.setBounds(0, 0, 138, 288);
		panel_1.add(editorPane);
	}
	public static JPanel getInstance(){
		return instance;
	}

}
