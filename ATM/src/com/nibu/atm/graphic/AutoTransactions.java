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

public class AutoTransactions extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		panel.setBounds(0, 6, 279, 266);
		contentPane.add(panel);
		panel.setLayout(null);
		//panel.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 5, 267, 255);
		panel.add(scrollPane);
		
		String [] names = {"Опис", "Дата", "Сума"};
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
	}
}
