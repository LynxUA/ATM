package com.nibu.atm.graphic;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;

import javax.swing.*;


public class ATM extends JFrame{

	public ATM() {
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int screenHeight=screenSize.height;
		int screenWidth=screenSize.width;
		setSize(screenWidth/2,screenHeight/2);
		setLocation(screenWidth/4,screenHeight/4);
		setTitle("Банкомат банка \"Nibu\"");
		
		final JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBackground(Color.DARK_GRAY);
		//backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
		
		final JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.GRAY);
		mainPanel.setPreferredSize(new Dimension(200, 100));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setAlignmentX(CENTER_ALIGNMENT);
		//mainPanel.setLayout(new BorderLayout());
		//panel.setLayout(new GridLayout());
		final JTextField login = new JTextField("Введіть логін");
		//login.setSize(150, 250);
		login.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(((JTextField)(e.getSource())).getText().equals("")){
					((JTextField)(e.getSource())).setText("Введіть логін");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				((JTextField)(e.getSource())).setText("");
				
			}
		});
		final JPasswordField password = new JPasswordField();
		JButton button = new JButton("OK");
		ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//JOptionPane.showMessageDialog(panel, "Hello, "+field.getText());
				ATM.this.remove(backgroundPanel);
				JPanel panel1 = new JPanel();
				panel1.add(new JLabel(" Hello, "+login.getText()));
				ATM.this.add(panel1);
				ATM.this.setVisible(true);
				ATM.this.repaint();
			//JLabel label = new JLabel("Hello, "+field.getText());
		
			}
		};
		button.addActionListener(listener);
		//button.setHorizontalAlignment(SwingConstants.CENTER);
		//button.setAlignmentX(25);
		login.addActionListener(listener);
		backgroundPanel.add(mainPanel);
		mainPanel.add(login);
		mainPanel.add(password);
		mainPanel.add(button);
	
		this.add(backgroundPanel);
		this.setVisible(true);
		}
	
		public static void main(String[] args) {
		ATM atm = new ATM();
		
		//JFrame frame = new JFrame();
		
		//frame.pack();
		//frame.setVisible(true);

	}

}
