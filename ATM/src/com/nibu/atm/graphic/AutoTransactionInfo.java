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

public class AutoTransactionInfo extends JPanel {

	private static JPanel instance = new AutoTransactionInfo();
	//private JPanel contentPane;
	private JTextField reciverField;
	private JTextField descriptionField;
	private JTextField dayField;
	private JTextField amountField;

	public AutoTransactionInfo() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		//contentPane = new JPanel();
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Редагувати автоматичну транзакцію");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(119, 5, 283, 23);
		panel.add(label);
		
		JLabel receiver = new JLabel("Номер картки адресата:");
		receiver.setBounds(10, 50, 178, 16);
		panel.add(receiver);
		
		reciverField = new JTextField();
		reciverField.setColumns(10);
		reciverField.setBounds(10, 68, 428, 23);
		panel.add(reciverField);
		
		JLabel description = new JLabel("Опис:");
		description.setBounds(10, 179, 178, 16);
		panel.add(description);
		
		descriptionField = new JTextField();
		descriptionField.setColumns(10);
		descriptionField.setBounds(10, 107, 428, 28);
		panel.add(descriptionField);
		
		JLabel day = new JLabel("День місяця для переказу:");
		day.setBounds(10, 92, 178, 16);
		panel.add(day);
		
		dayField = new JTextField();
		dayField.setColumns(10);
		dayField.setBounds(10, 152, 424, 23);
		panel.add(dayField);
		
		JLabel amount = new JLabel("Сума переказу:");
		amount.setBounds(10, 132, 178, 16);
		panel.add(amount);
		
		amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(10, 195, 184, 28);
		panel.add(amountField);
		
		JButton proceed = new JButton("Обробити");
		proceed.setBounds(43, 224, 117, 29);
		panel.add(proceed);
		
		JButton back = new JButton("Повернутися");
		back.setBounds(0, 3, 107, 29);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)AutoTransactionInfo.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = AutoTransactions.getInstance();
				mainFrame.add(ATPanel);
				mainFrame.setContentPane(ATPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel.add(back);
	}
	public static JPanel getInstance(){
		return instance;
	}

}
