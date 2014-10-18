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
import javax.swing.JEditorPane;

public class ExceedingCredit extends JPanel {

	private static JPanel instance  = new ExceedingCredit();
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public ExceedingCredit() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setBounds(100, 100, 600, 300);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(6, 6, 438, 288);
		this.add(panel);
		
		JLabel label = new JLabel("Кредитний ліміт");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(74, 5, 285, 23);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Поточний кредитний ліміт:");
		label_1.setBounds(10, 40, 244, 16);
		panel.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 55, 194, 28);
		panel.add(textField);
		
		JButton save = new JButton("Зберегти");
		save.setBounds(171, 253, 117, 29);
		panel.add(save);
		
		JButton back = new JButton("Повернутися");
		back.setBounds(0, 3, 107, 29);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)ExceedingCredit.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = MainMenu.getInstance();
				mainFrame.getContentPane().add(ATPanel);
				mainFrame.setContentPane(ATPanel);
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel.add(back);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(10, 225, 424, 16);
		panel.add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(456, 6, 138, 288);
		add(panel_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText(ATM.getConsole());
		editorPane.setBounds(0, 0, 138, 288);
		panel_1.add(editorPane);
	}

	public static JPanel getInstance() {
		return instance;
	}

}
