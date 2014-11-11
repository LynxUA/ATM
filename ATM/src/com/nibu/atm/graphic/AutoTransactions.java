package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
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

import com.nibu.atm.AutoTransaction;
import com.nibu.atm.Bank;
import com.nibu.atm.BankOperationRes;

public class AutoTransactions extends JPanel {

	private static JPanel instance = new AutoTransactions();
	private JTable table;
	private JTextField recieverField;
	private JTextField dayField;
	private JTextField amountField;
	private JTextField descriptionField;
	
	private JEditorPane pane;

	private static List<AutoTransaction> transactions;
	private static DefaultTableModel model;
	/**
	 * Create the frame.
	 */
	private AutoTransactions() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setBounds(100, 100, 600, 300);
		try {
			this.transactions = ATM.getDAO().getAutoTransactions(ATM.getCardNumber());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JPanel panel = new JPanel();
		panel.setBounds(0, 6, 238, 288);
		this.add(panel);
		panel.setLayout(null);
		//panel.add(list);
		
		
		
		
		model = new DefaultTableModel();
		refreshTransactions();
		table = new JTable(model);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(table != null){
		        	JFrame mainFrame = (JFrame)AutoTransactions.this.getTopLevelAncestor();
		        	if(mainFrame != null){
		        	mainFrame.remove(instance);
					//System.out.println(table.getSelectedRow());
					AutoTransactionInfo.setTransaction(transactions.get(table.getSelectedRow()));
					JPanel ATPanel = AutoTransactionInfo.getInstance();
					mainFrame.getContentPane().add(ATPanel);
					mainFrame.setContentPane(ATPanel);
					
					mainFrame.setVisible(true);
					mainFrame.repaint();
		        	}
	        	}
	        	//table.getValueAt(table.getSelectedRow(), 0).toString();
				
	        }
	    });
		table.setBounds(0, 0, 1, 1);
		//table.a
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 37, 226, 245);
		panel.add(scrollPane);
		
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
		
		dayField = new JTextField();
		dayField.setBounds(0, 105, 194, 28);
		panel_1.add(dayField);
		dayField.setColumns(10);
		
		JLabel dayLabel = new JLabel("День місяця для переказу (1-28):");
		dayLabel.setBounds(10, 88, 178, 16);
		panel_1.add(dayLabel);
		
		amountField = new JTextField();
		amountField.setBounds(0, 152, 194, 23);
		panel_1.add(amountField);
		amountField.setColumns(10);
		
		JLabel amountLabel = new JLabel("Сума переказу:");
		amountLabel.setBounds(10, 135, 178, 16);
		panel_1.add(amountLabel);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(0, 195, 194, 28);
		panel_1.add(descriptionField);
		descriptionField.setColumns(10);
		
		JButton proceedButton = new JButton("Обробити");
		proceedButton.setBounds(43, 253, 117, 29);
		panel_1.add(proceedButton);
		proceedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(recieverField.getText().equals(ATM.getCardNumber())){
					ATM.setConsole(ATM.getConsole()+"Operation denied\nYou can't give money to yourself\n");
					pane.setText(ATM.getConsole());
				}else{
					String reciever = recieverField.getText();
					int day = Integer.parseInt(dayField.getText());
					long amount = Long.parseLong(amountField.getText());
					String description = descriptionField.getText();
					BankOperationRes result = null;
					try {
						result = ATM.getDAO().addAutoTransaction(ATM.getCardNumber(), reciever, day, amount, description);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(result==BankOperationRes.COMPLETE){
						ATM.setConsole(ATM.getConsole()+"Auto transaction was added:\nSender card number:\n"
						+ATM.getCardNumber()+"\nReciever card number:\n" 
						+ reciever +"\nDay of a month for operation:\n"
						+day+"\nAmount:\n"
						+amount+"\nDescription:\n"
						+description+"\n");
						recieverField.setText("");
						amountField.setText("");
						descriptionField.setText("");
						dayField.setText("");
						AutoTransactions.refreshTransactions();
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
	private void refreshTable(){
		String [] names = {"Номер картки", "Опис", "Дата", "Сума"};
		int size = transactions.size();
		Object [][] data = new Object[size][4];
		for(int i = 0; i<transactions.size(); i++)
		{
			data[i][0]=transactions.get(i).getCardToNumber();
			data[i][1]=transactions.get(i).getDescription();
			data[i][2]=transactions.get(i).getDay();
			data[i][3]=transactions.get(i).getMoneyAmount();
		}
		table.removeAll();
		table = new JTable(data,names);
	}

	public static JPanel getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	public static void refreshTransactions(){
		try {
			transactions = ATM.getDAO().getAutoTransactions(ATM.getCardNumber());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] names = {"Номер картки", "Опис", "Дата", "Сума"};
		int size = transactions.size();
		Object [][] data = new Object[size][4];
		for(int i = 0; i<transactions.size(); i++)
		{
			data[i][0]=transactions.get(i).getCardToNumber();
			data[i][1]=transactions.get(i).getDescription();
			data[i][2]=transactions.get(i).getDay();
			data[i][3]=transactions.get(i).getMoneyAmount();
		}
		model.setDataVector(data, names);
	}
}
