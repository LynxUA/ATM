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

public class MainMenu extends JPanel {

	private static  JPanel instance = new MainMenu();
	private JTextField textField;
	private JTextField textField_1;

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
		
		JLabel label = new JLabel("Переказати кошти");
		label.setBounds(66, 6, 117, 16);
		panel_1.add(label);
		
		JLabel label2 = new JLabel("Номер карти одержувача:");
		label2.setBounds(6, 33, 177, 16);
		panel_1.add(label2);
		
		textField = new JTextField();
		textField.setBounds(6, 50, 233, 28);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Сума:");
		label_1.setBounds(6, 80, 61, 16);
		panel_1.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 100, 233, 28);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_2 = new JButton("Надіслати");
		button_2.setBounds(66, 253, 117, 29);
		panel_1.add(button_2);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(6, 225, 233, 16);
		panel_1.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(456, 6, 138, 288);
		add(panel_2);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.console);
		editorPane.setBounds(0, 0, 138, 288);
		panel_2.add(editorPane);
	}
	public static JPanel getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

}
