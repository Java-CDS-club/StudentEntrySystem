package com.student.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import com.student.dao.Dao;

import javax.swing.JButton;

public class Entry extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Entry frame;
	private Timer t;
	String TIME_SERVER = "pool.ntp.org";
	NTPUDPClient timeClient = new NTPUDPClient();
	InetAddress inetAddress;
	long returnTime;
	TimeInfo timeInfo;
	Calendar c, c1;
	Integer sec;
	Date objDate;
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a dd-MMM-yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Entry(args);
					frame.setLocationRelativeTo(null);
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
				if (Dao.Entry(args[0], args[1], course.getSelectedItem().toString(),
						new java.sql.Time(c1.getTime().getTime())) == 1) {
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

		try {
			URL url = new URL("http://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(Entry.this, "Internet Connection Required", "Warning!",
					JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Entry.this, "Internet Connection Required", "Warning!",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			inetAddress = InetAddress.getByName(TIME_SERVER);
			timeInfo = timeClient.getTime(inetAddress);
			returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
			// long returnTime = timeInfo.getMessage().getReceiveTimeStamp().getTime();
			objDate = new Date(returnTime);
		} catch (Exception e) {
			e.printStackTrace();
			objDate = new Date();
		} finally {
			c = Calendar.getInstance();
			c.setTime(objDate);
			c1 = Calendar.getInstance();
			sec = c.get(Calendar.SECOND);
		}

		t = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sec += 1;
				c1.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY),
						c.get(Calendar.MINUTE), sec);
				lblTimer.setText(String.valueOf(sdf.format(c1.getTime())));
			}
		});
		t.start();
	}
}
