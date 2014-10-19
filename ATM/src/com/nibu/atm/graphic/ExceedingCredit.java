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

public class ExceedingCredit extends JPanel {

	private static JPanel instance  = new ExceedingCredit();
	private JTextField limitField;
	private JEditorPane editorPane;
	/**
	 * Create the frame.
	 */
	public ExceedingCredit() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setBounds(100, 100, 600, 300);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(6, 6, 438, 288);
		this.add(panel);
		
		JLabel limitLabel = new JLabel("Кредитний ліміт");
		limitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		limitLabel.setBounds(119, 5, 240, 23);
		panel.add(limitLabel);
		
		JLabel currentLimitLabel = new JLabel("Поточний кредитний ліміт:");
		currentLimitLabel.setBounds(10, 40, 244, 16);
		panel.add(currentLimitLabel);
		
		limitField = new JTextField();
		limitField.setColumns(10);
		limitField.setBounds(10, 55, 194, 28);
		panel.add(limitField);
		
		JButton save = new JButton("Зберегти");
		save.setBounds(171, 253, 117, 29);
		panel.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BankOperationRes result = Bank.getInstance().changeCreditLimit(ATM.getCardNumber(), Integer.parseInt(limitField.getText()));
				if(result==BankOperationRes.COMPLETE){
					ATM.setConsole(ATM.getConsole()+"Limit changed to " + limitField.getText() +"\n");
					limitField.setText("");
					editorPane.setText(ATM.getConsole());
				}else if(result == BankOperationRes.LIMIT_OVERFLOW){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nThe limit is higher then bank can give\n");
					editorPane.setText(ATM.getConsole());
				}else{
					System.err.println("Unexpectable result");
				}
				
			}
		});
		
		JButton back = new JButton("Повернутися");
		back.setBounds(0, 3, 107, 29);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)ExceedingCredit.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = MainMenu.getInstance();
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
		panel_1.setBounds(456, 6, 138, 288);
		add(panel_1);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.getConsole());
		editorPane.setBounds(0, 0, 138, 288);
		panel_1.add(editorPane);
	}

	public static JPanel getInstance() {
		return instance;
	}

}
