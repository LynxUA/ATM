package com.nibu.atm.graphic;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PCS extends JPanel {

	private static JPanel instance = new PCS();
	//private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public PCS() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(6, 6, 438, 266);
		this.add(panel_1);
		
		JLabel label = new JLabel("Автоматичне перерахування залишку");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(120, 5, 285, 23);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Ліміт після якого переводимо:");
		label_1.setBounds(10, 40, 244, 16);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 55, 194, 28);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 112, 194, 28);
		panel_1.add(textField_1);
		
		JLabel label_3 = new JLabel("Карта (куди переводимо):");
		label_3.setBounds(10, 88, 178, 16);
		panel_1.add(label_3);
		
		JButton save = new JButton("Зберегти");
		save.setBounds(171, 231, 117, 29);
		panel_1.add(save);
		
		JButton back = new JButton("Повернутися");
		back.setBounds(0, 3, 107, 29);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame mainFrame = (JFrame)PCS.this.getTopLevelAncestor();
				mainFrame.remove(instance);
				JPanel ATPanel = MainMenu.getInstance();
				mainFrame.add(ATPanel);
				mainFrame.setContentPane(ATPanel);
				
				mainFrame.setVisible(true);
				mainFrame.repaint();
				
			}
		});
		panel_1.add(back);
	}

	public static JPanel getInstance() {
		// TODO Auto-generated method stub
		return instance ;
	}

}
