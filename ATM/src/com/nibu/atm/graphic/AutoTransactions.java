package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AutoTransactions extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
					AutoTransactions frame = new AutoTransactions();
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
	public AutoTransactions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 6, 238, 266);
		contentPane.add(panel);
		panel.setLayout(null);
		//panel.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 5, 226, 255);
		panel.add(scrollPane);
		
		String [] names = {"Номер картки", "Опис", "Дата", "Сума"};
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        }
	    });
		table.setBounds(0, 0, 1, 1);
		//table.a
		scrollPane.add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(250, 6, 194, 266);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Додати транзакцію");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 5, 147, 23);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Номер картки адресата:");
		lblNewLabel_1.setBounds(10, 40, 178, 16);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(0, 57, 194, 28);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Опис:");
		lblNewLabel_2.setBounds(10, 179, 178, 16);
		panel_1.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(0, 105, 194, 28);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("День місяця для переказу:");
		lblNewLabel_3.setBounds(10, 88, 178, 16);
		panel_1.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(0, 152, 194, 23);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label = new JLabel("Сума переказу:");
		label.setBounds(10, 135, 178, 16);
		panel_1.add(label);
		
		textField_3 = new JTextField();
		textField_3.setBounds(0, 195, 194, 28);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Обробити");
		btnNewButton.setBounds(43, 224, 117, 29);
		panel_1.add(btnNewButton);
	}
}
