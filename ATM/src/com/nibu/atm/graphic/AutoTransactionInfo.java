package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AutoTransactionInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoTransactionInfo frame = new AutoTransactionInfo();
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
	public AutoTransactionInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Редагувати автоматичну транзакцію");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(84, 5, 283, 23);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Номер картки адресата:");
		label_1.setBounds(10, 50, 178, 16);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 68, 428, 23);
		panel.add(textField);
		
		JLabel label_2 = new JLabel("Опис:");
		label_2.setBounds(10, 179, 178, 16);
		panel.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 107, 428, 28);
		panel.add(textField_1);
		
		JLabel label_3 = new JLabel("День місяця для переказу:");
		label_3.setBounds(10, 92, 178, 16);
		panel.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 152, 428, 23);
		panel.add(textField_2);
		
		JLabel label_4 = new JLabel("Сума переказу:");
		label_4.setBounds(10, 132, 178, 16);
		panel.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 195, 428, 28);
		panel.add(textField_3);
		
		JButton button = new JButton("Обробити");
		button.setBounds(166, 233, 117, 29);
		panel.add(button);
	}

}
