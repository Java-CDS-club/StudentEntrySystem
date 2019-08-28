package com.student.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.student.dao.Dao;

import javax.swing.JButton;

public class Exit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Exit frame;
	private Timer t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
							((LookAndFeelInfo) Array.get(UIManager.getInstalledLookAndFeels(), 1)).getClassName());
					frame = new Exit(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Exit(String[] args) {
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {
		setResizable(false);
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblTeamBdcoe = new JLabel("Team BDCoE");
		lblTeamBdcoe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamBdcoe.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTeamBdcoe.setBounds(150, 45, 200, 40);
		getContentPane().add(lblTeamBdcoe);

		JLabel Name = new JLabel("Hi Student");
		Name.setHorizontalAlignment(SwingConstants.LEFT);
		Name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Name.setBounds(50, 150, 400, 40);
		getContentPane().add(Name);

		JLabel Time = new JLabel("Time :");
		Time.setHorizontalAlignment(SwingConstants.RIGHT);
		Time.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Time.setBounds(100, 230, 50, 30);
		getContentPane().add(Time);

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dao.Exit(args[0], args[1]) == 1) {
					JOptionPane.showMessageDialog(Exit.this, "Lab Exit Successfull!");
					MainContainer.main(new String[] {});
					frame.dispose();
				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(210, 328, 80, 30);
		getContentPane().add(btnNewButton);

		JLabel lblTimer = new JLabel("Timer");
		lblTimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setBounds(200, 230, 200, 30);
		getContentPane().add(lblTimer);

		t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date objDate = new Date(); // Current System Date and time is assigned to objDate
//				System.out.println(objDate);
				String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; // Date format is Specified
				SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // Date format string is passed as an
																				// argument to the Date format object
				lblTimer.setText(objSDF.format(objDate));

			}
		});
		t.start();
	}
}
