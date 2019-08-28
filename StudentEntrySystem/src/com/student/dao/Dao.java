package com.student.dao;

import java.sql.*;
import java.util.Date;

public class Dao {
	private static Connection con;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(DBIntializer.DRIVER);
			con = DriverManager.getConnection(DBIntializer.CON_STRING, DBIntializer.USERNAME, DBIntializer.PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int Entry(String studno, String name, String work) {
		int status = 0;
		Time time = new Time(System.currentTimeMillis());
		try {
			Connection con = Dao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into Entry(studno, name, work, date, entry ) values (?,?,?,?,?)");
			ps.setString(1, studno);
			ps.setString(2, name);
			ps.setString(3, work);
			ps.setDate(4, new java.sql.Date((new Date()).getTime()));
			ps.setString(5, time.toString());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static boolean validate(String studno, String name) {
		boolean status = true;
		try {
			Connection con = Dao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from Entry where studno = ? and name = ?;");
			ps.setString(1, studno);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String entry = rs.getString("entry");
				String exit = rs.getString("exit");
				if (entry != null && exit == null) {
					status = false;
					break;
				}
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int Exit(String studno, String name) {
		int status = 0;
		Time time = new Time(System.currentTimeMillis());
		try {
			Connection con = Dao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE Entry SET `exit`=? WHERE studno=? AND name=? AND `exit` IS NULL;");
			ps.setString(1, time.toString());
			ps.setString(2, studno);
			ps.setString(3, name);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static String[][] fetchByDate(Date from, Date to) {
		String[][] arr = null;
		try {
			int i = 0;
			Connection con = Dao.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Entry WHERE date BETWEEN ? AND ?;");
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			int totalRows = 0;
			rs.last();
			totalRows = rs.getRow();
			rs.beforeFirst();
			arr = new String[totalRows][7];
			if (rs.next() == false) {
				return null;
			} else {
				do {
					arr[i][0] = rs.getString(1);
					arr[i][1] = rs.getString(2);
					arr[i][2] = rs.getString(3);
					arr[i][3] = rs.getString(4);
					arr[i][4] = rs.getString(5);
					arr[i][5] = rs.getString(6);
					arr[i][6] = rs.getString(7);
					i++;
				} while (rs.next());
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
}
