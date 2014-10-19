package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

import com.nibu.atm.Bank;
import com.nibu.atm.BankOperationRes;

public class AutoTransactions extends JPanel {

	private static JPanel instance = new AutoTransactions();
	private JTable table;
	private JTextField recieverField;
	private JTextField descriptionField;
	private JTextField dayField;
	private JTextField amountField;
	
	private JEditorPane pane;

	/**
	 * Create the frame.
	 */
	public AutoTransactions() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setBounds(100, 100, 600, 300);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 6, 238, 288);
		this.add(panel);
		panel.setLayout(null);
		//panel.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 37, 226, 245);
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
		
		JButton back = new JButton("Повернутися");
		back.setBounds(0, 6, 107, 29);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)AutoTransactions.this.getTopLevelAncestor();
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
		panel_1.setBounds(250, 6, 194, 288);
		this.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel addTransactionLabel = new JLabel("Додати транзакцію");
		addTransactionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addTransactionLabel.setBounds(0, 5, 147, 23);
		panel_1.add(addTransactionLabel);
		
		JLabel recieverLabel = new JLabel("Номер картки адресата:");
		recieverLabel.setBounds(10, 40, 178, 16);
		panel_1.add(recieverLabel);
		
		recieverField = new JTextField();
		recieverField.setBounds(0, 57, 194, 28);
		panel_1.add(recieverField);
		recieverField.setColumns(10);
		
		JLabel descriptionLabel = new JLabel("Опис:");
		descriptionLabel.setBounds(10, 179, 178, 16);
		panel_1.add(descriptionLabel);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(0, 105, 194, 28);
		panel_1.add(descriptionField);
		descriptionField.setColumns(10);
		
		JLabel dayLabel = new JLabel("День місяця для переказу (1-28):");
		dayLabel.setBounds(10, 88, 178, 16);
		panel_1.add(dayLabel);
		
		dayField = new JTextField();
		dayField.setBounds(0, 152, 194, 23);
		panel_1.add(dayField);
		dayField.setColumns(10);
		
		JLabel amountLabel = new JLabel("Сума переказу:");
		amountLabel.setBounds(10, 135, 178, 16);
		panel_1.add(amountLabel);
		
		amountField = new JTextField();
		amountField.setBounds(0, 195, 194, 28);
		panel_1.add(amountField);
		amountField.setColumns(10);
		
		JButton proceedButton = new JButton("Обробити");
		proceedButton.setBounds(43, 253, 117, 29);
		panel_1.add(proceedButton);
		proceedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String reciever = recieverField.getText();
				int day = Integer.parseInt(dayField.getText());
				long amount = Long.parseLong(amountField.getText());
				String description = descriptionField.getText();
				BankOperationRes result = Bank.getInstance().addAutoTransaction(ATM.getCardNumber(), reciever, day, amount, description);
				if(result==BankOperationRes.COMPLETE){
					ATM.setConsole(ATM.getConsole()+"Auto transaction was added:\nSender card number:\n"
					+ATM.getCardNumber()+"\nReciever card number:\n" 
					+ reciever +"\nDay of a month for operation:\n"
					+day+"\nAmount:\n"
					+amount+"\nDescription:\n"
					+description+"\n");
					recieverField.setText("");
					dayField.setText("");
					amountField.setText("");
					descriptionField.setText("");
					
					pane.setText(ATM.getConsole());
				}else if(result == BankOperationRes.NO_ACCOUNT_TO_SEND){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nCheck account number\n");
					pane.setText(ATM.getConsole());
				}else if(result == BankOperationRes.INVALID_DAY){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nInvalid day\n");
					pane.setText(ATM.getConsole());
				}else{
					System.err.println("Unexpectable result");
				}
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(456, 6, 138, 288);
		add(panel_2);
		panel_2.setLayout(null);
		
		pane= new JEditorPane();
		pane.setEditable(false);
		pane.setText(ATM.getConsole());
		pane.setBounds(0, 0, 138, 288);
		panel_2.add(pane);
		//System.out.println(this.getX()+"*"+this.getY());
	}

	public static JPanel getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
}
