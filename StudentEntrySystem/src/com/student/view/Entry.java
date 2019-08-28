package com.student.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.student.dao.Dao;

import javax.swing.JButton;

public class Entry extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Entry frame;
	private Timer t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Entry(args);
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
	public Entry(String[] args) {
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
		Name.setBounds(50, 120, 400, 40);
		getContentPane().add(Name);

		JLabel Time = new JLabel("Time :");
		Time.setHorizontalAlignment(SwingConstants.RIGHT);
		Time.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Time.setBounds(100, 180, 50, 30);
		getContentPane().add(Time);

		JComboBox<String> course = new JComboBox<String>();
		course.setFont(new Font("Tahoma", Font.PLAIN, 13));
		course.setModel(new DefaultComboBoxModel<String>(new String[] { "Work", "Study ", "Project" }));
		course.setBounds(200, 235, 200, 30);
		getContentPane().add(course);

		JLabel lblSelectCourse = new JLabel("Select Task :");
		lblSelectCourse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelectCourse.setBounds(80, 233, 100, 30);
		getContentPane().add(lblSelectCourse);

		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dao.Entry(args[0], args[1], course.getSelectedItem().toString()) == 1) {
					JOptionPane.showMessageDialog(Entry.this, "Entry Done Successfully");
					MainContainer.main(new String[] {});
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(Entry.this, "Something is Wrong", "Failed!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(210, 328, 80, 30);
		getContentPane().add(btnNewButton);

		JLabel lblTimer = new JLabel("Time");
		lblTimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setBounds(200, 180, 200, 30);
		getContentPane().add(lblTimer);

		t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date objDate = new Date();
				String strDateFormat = "hh:mm:ss a dd-MMM-yyyy";
				SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
				lblTimer.setText(objSDF.format(objDate));

			}
		});
		t.start();
	}
}
