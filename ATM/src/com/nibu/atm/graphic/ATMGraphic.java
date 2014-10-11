package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class ATMGraphic extends JFrame {

	private JPanel backgroundPanel;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public ATMGraphic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		backgroundPanel = new JPanel();
		backgroundPanel.setBackground(new Color(169, 169, 169));
		backgroundPanel.setForeground(new Color(112, 128, 144));
		backgroundPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(backgroundPanel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setForeground(new Color(176, 196, 222));
		GroupLayout gl_backgroundPanel = new GroupLayout(backgroundPanel);
		gl_backgroundPanel.setHorizontalGroup(
			gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_backgroundPanel.createSequentialGroup()
					.addGap(105)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		gl_backgroundPanel.setVerticalGroup(
			gl_backgroundPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_backgroundPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(153, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Пароль");
		passwordField.setBounds(0, 36, 234, 29);
		panel.add(passwordField);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setToolTipText("Номер картки");
		textField.setText("Номер картки");
		textField.setBounds(0, 6, 234, 29);
		panel.add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		//textField.add
		
		JButton btnNewButton = new JButton("Ввійти в систему");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(48, 77, 150, 29);
		panel.add(btnNewButton);
		backgroundPanel.setLayout(gl_backgroundPanel);
	}
}
