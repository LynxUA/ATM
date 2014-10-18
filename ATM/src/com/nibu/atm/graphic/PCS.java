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

public class PCS extends JPanel {

	private static JPanel instance = new PCS();
	//private JPanel contentPane;
	private JTextField limitField;
	private JTextField cardField;
	private JEditorPane editorPane;

	/**
	 * Create the frame.
	 */
	public PCS() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setBounds(100, 100, 600, 300);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(6, 6, 438, 288);
		this.add(panel_1);
		
		JLabel autoLabel = new JLabel("Автоматичне перерахування залишку");
		autoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		autoLabel.setBounds(120, 5, 285, 23);
		panel_1.add(autoLabel);
		
		JLabel limitLabel = new JLabel("Ліміт після якого переводимо:");
		limitLabel.setBounds(10, 40, 244, 16);
		panel_1.add(limitLabel);
		
		limitField = new JTextField();
		limitField.setColumns(10);
		limitField.setBounds(10, 55, 194, 28);
		panel_1.add(limitField);
		
		JLabel cardLabel = new JLabel("Карта (куди переводимо):");
		cardLabel.setBounds(10, 88, 178, 16);
		panel_1.add(cardLabel);
		
		cardField = new JTextField();
		cardField.setColumns(10);
		cardField.setBounds(10, 112, 194, 28);
		panel_1.add(cardField);
		
		JButton save = new JButton("Зберегти");
		save.setBounds(171, 253, 117, 29);
		panel_1.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BankOperationRes result = Bank.getInstance().setMoneyExcessLimit(ATM.getCardNumber(), cardField.getText(), Long.parseLong(limitField.getText()));
				if(result==BankOperationRes.COMPLETE){
					ATM.setConsole(ATM.getConsole()+"Limit changed\n");
					cardField.setText("");
					limitField.setText("");
				}else if(result == BankOperationRes.NO_ACCOUNT_TO_SEND){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nCheck the reciever card number\n");
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
				JFrame mainFrame = (JFrame)PCS.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = MainMenu.getInstance();
				mainFrame.getContentPane().add(ATPanel);
				mainFrame.setContentPane(ATPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel_1.add(back);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(10, 225, 424, 16);
		panel_1.add(label_2);
		
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
