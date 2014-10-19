package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import com.nibu.atm.Bank;
import com.nibu.atm.BankOperationRes;

public class Info extends JPanel {

	private static JPanel instance = new Info();
	//private JPanel contentPane;
	private JTextField limitField;
	private JTextField cardField;
	private JEditorPane editorPane;

	/**
	 * Create the frame.
	 */
	public Info() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setBounds(100, 100, 600, 300);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(6, 6, 438, 288);
		this.add(panel_1);
		
		JLabel autoLabel = new JLabel("Інформація про аккаунт");
		autoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		autoLabel.setBounds(120, 5, 285, 23);
		panel_1.add(autoLabel);
		
		JLabel cardNumberLabel = new JLabel("Номер картки: " + Bank.getInstance().getName(ATM.getCardNumber())+" - "+ATM.getCardNumber());
		cardNumberLabel.setBounds(10, 44, 422, 16);
		panel_1.add(cardNumberLabel);
		
		JLabel balanceLabel = new JLabel("Поточний баланс: " + Bank.getInstance().getBalance(ATM.getCardNumber()));
		balanceLabel.setBounds(10, 72, 422, 16);
		panel_1.add(balanceLabel);
		
		JLabel limitLabel = new JLabel("Кредитний ліміт: " + Bank.getInstance().getCreditLimit(ATM.getCardNumber()));
		limitLabel.setBounds(10, 100, 422, 16);
		panel_1.add(limitLabel);
		
		JLabel maxLimitLabel = new JLabel("Максимальний особистий кредитний ліміт: " + Bank.getInstance().getMaxCreditLimit(ATM.getCardNumber()));
		maxLimitLabel.setBounds(10, 128, 422, 16);
		panel_1.add(maxLimitLabel);
		
		
		JButton back = new JButton("Повернутися");
		back.setBounds(0, 3, 107, 29);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)Info.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = MainMenu.getInstance();
				MainMenu.refresh();
				mainFrame.getContentPane().add(ATPanel);
				mainFrame.setContentPane(ATPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel_1.add(back);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(456, 6, 138, 288);
		add(panel);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.getConsole());
		editorPane.setBounds(0, 0, 138, 288);
		panel.add(editorPane);
	}

	public static JPanel getInstance() {
		// TODO Auto-generated method stub
		return instance ;
	}
}
