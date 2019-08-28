package com.student.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.student.dao.Dao;

public class MainContainer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static MainContainer Frame;
	private JTextField Studnum, user;
	private JTextField name;
	private JPasswordField pass;

	public static ImageIcon resize(ImageIcon im, int w, int h) {
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D gd = (Graphics2D) bi.createGraphics();
		gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		gd.drawImage(im.getImage(), 0, 0, w, h, null);
		gd.dispose();
		return new ImageIcon(bi);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
							((LookAndFeelInfo) Array.get(UIManager.getInstalledLookAndFeels(), 1)).getClassName());
					Frame = new MainContainer();
					Frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainContainer() {
		initialize();
	}

	/**
	 * Initialize the contents of the Frame.
	 */
	private void initialize() {
		setResizable(false);
		setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

//		setContentPane(new JLabel(MainContainer.resize(
//				new ImageIcon("F:\\College\\Sem V\\stylish-hexagonal-line-pattern-background_1017-19742.jpg"),
//				getWidth(), getHeight())));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 500, 500);

		getContentPane().add(tabbedPane);

		JPanel student = new JPanel();
		tabbedPane.addTab("Student", null, student, null);
		student.setLayout(null);

		JPanel admin = new JPanel();
		tabbedPane.addTab("Admin", null, admin, null);
		admin.setLayout(null);

		JLabel lblLogInDetail = new JLabel("Student Portal");
		lblLogInDetail.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogInDetail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogInDetail.setBounds(150, 120, 200, 40);
		student.add(lblLogInDetail);

		JLabel lblStudentNumber = new JLabel("Student Number");
		lblStudentNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentNumber.setBounds(130, 180, 112, 30);
		student.add(lblStudentNumber);

		Studnum = new JTextField();
		Studnum.setColumns(10);
		Studnum.setBounds(260, 180, 100, 30);
		student.add(Studnum);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(130, 230, 107, 30);
		student.add(lblName);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Dao.validate(Studnum.getText(), name.getText())) {
					Entry.main(new String[] { Studnum.getText().trim(), name.getText().trim() });
				} else {
					Exit.main(new String[] { Studnum.getText().trim(), name.getText().trim() });
				}
				Frame.dispose();
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(210, 300, 80, 30);
		student.add(btnSubmit);

		JLabel lblTeamBdcoe = new JLabel("Team BDCoE");
		lblTeamBdcoe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamBdcoe.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTeamBdcoe.setBounds(150, 45, 200, 40);
		student.add(lblTeamBdcoe);

		JLabel lblTeamBdcoe1 = new JLabel("Team BDCoE");
		lblTeamBdcoe1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamBdcoe1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTeamBdcoe1.setBounds(150, 45, 200, 40);
		admin.add(lblTeamBdcoe1);

		JLabel lblAdminLogin = new JLabel("Admin Portal");
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdminLogin.setBounds(150, 120, 200, 40);
		admin.add(lblAdminLogin);

		JLabel lblUser = new JLabel("User :");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser.setBounds(130, 180, 112, 30);
		admin.add(lblUser);

		user = new JTextField();
		user.setColumns(10);
		user.setBounds(260, 180, 100, 30);
		admin.add(user);

		JLabel lblName1 = new JLabel("Name");
		lblName1.setHorizontalAlignment(SwingConstants.CENTER);
		lblName1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName1.setBounds(130, 230, 107, 30);
		student.add(lblName1);

		JButton btnLogIn1 = new JButton("Log In");
		btnLogIn1.setBounds(210, 300, 80, 30);
		student.add(btnLogIn1);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(260, 230, 100, 30);
		student.add(name);
		JButton Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (user.getText().trim().equals("admin") && String.valueOf(pass.getPassword()).equals("admin")) {
					Admin.main(new String[] {});
					Frame.dispose();
				} else {
					JOptionPane.showMessageDialog(MainContainer.this, "Sorry, Username or Password Error",
							"Login Error!", JOptionPane.ERROR_MESSAGE);
					user.setText("");
					pass.setText("");
				}
			}
		});
		Login.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Login.setBounds(200, 300, 100, 30);
		admin.add(Login);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(130, 230, 112, 30);
		admin.add(lblPassword);

		pass = new JPasswordField();
		pass.setBounds(260, 230, 100, 30);
		admin.add(pass);

		Studnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				if (!((key >= 48 && key <= 57) || (key == KeyEvent.VK_DELETE) || (key == KeyEvent.VK_BACK_SPACE))) {
					e.consume();
				}

			}
		});

		user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isAlphabetic(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});

		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isAlphabetic(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
	}
}
