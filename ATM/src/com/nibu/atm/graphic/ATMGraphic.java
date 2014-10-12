package com.nibu.atm.graphic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.nibu.atm.Bank;

public class ATMGraphic extends JFrame {
	private static JFrame instance = new ATMGraphic();
	private JPanel backgroundPanel;
	private JTextField cardField;
	private JPasswordField passwordField;
	private JPanel panel_1;
	private JEditorPane editorPane;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMGraphic frame = new ATMGraphic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	private ATMGraphic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 325);
		backgroundPanel = new JPanel();
		backgroundPanel.setBackground(new Color(169, 169, 169));
		backgroundPanel.setForeground(new Color(112, 128, 144));
		backgroundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(backgroundPanel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setForeground(new Color(176, 196, 222));
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.getConsole());
		editorPane.setBounds(0, 0, 138, 288);
		panel_1.add(editorPane);
		GroupLayout gl_backgroundPanel = new GroupLayout(backgroundPanel);
		gl_backgroundPanel.setHorizontalGroup(
			gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_backgroundPanel.createSequentialGroup()
					.addGap(105)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
		);
		gl_backgroundPanel.setVerticalGroup(
			gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_backgroundPanel.createSequentialGroup()
					.addGroup(gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_backgroundPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		cardField = new JTextField();
		cardField.setToolTipText("Номер картки");
		cardField.setText("Номер картки");
		cardField.setBounds(0, 6, 234, 29);
		panel.add(cardField);
		cardField.setHorizontalAlignment(SwingConstants.CENTER);
		cardField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Пароль");
		passwordField.setBounds(0, 36, 234, 29);
		panel.add(passwordField);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		
		//textField.add
		
		JButton enter = new JButton("Ввійти в систему");
		enter.setBackground(new Color(255, 255, 255));
		enter.setBounds(48, 77, 150, 29);
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(cardField.getText() + passwordField.getPassword());
				if(Bank.getInstance().authorize(cardField.getText(), String.valueOf(passwordField.getPassword()))){
					ATM.setConsole(ATM.getConsole()+"Login succeded");
					ATMGraphic.this.remove(backgroundPanel);
					JPanel mainMenu = MainMenu.getInstance();
					getContentPane().add(mainMenu);
					ATMGraphic.this.setContentPane(mainMenu);
					
					ATMGraphic.this.setVisible(true);
					ATMGraphic.this.repaint();
				}else{
					ATM.setConsole(ATM.getConsole()+"Login denied\nCheck card number or password\n");
					//ATM.console+="Login denied\nCheck card number or password\n";
					editorPane.setText(ATM.getConsole());
				}
			}
		});
		panel.add(enter);
		backgroundPanel.setLayout(gl_backgroundPanel);
	}
	public static JFrame getInstance(){
		return instance;
	}
}
