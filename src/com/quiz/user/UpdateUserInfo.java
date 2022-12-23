package com.quiz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.quiz.connection.CreateConnection;

public class UpdateUserInfo {
	CreateConnection cc = new CreateConnection();
	Connection con = cc.getConnect();
	PreparedStatement ps = null;
	String query = null;

	public void updateUser(int userId, String username, String email, String password, String city, long mobile) {
		try {
			query = "update user set username=? ,email=?,password=?,city=?,mobile=? where userid=?";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, city);
			ps.setLong(5, mobile);
			ps.setInt(6, userId);
			if (ps.execute() == false) {
				System.out.println("User updated successfully");
				UserDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				UserDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateUser(String username, int userId) {
		try {
			query = "update user set username=? where userid=? ";
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setInt(2, userId);
			if (ps.execute() == false) {
				System.out.println("Name updated successfully");
				UserDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				UserDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateUser(int userId, String email, String password) {
		try {
			query = "update user set email=?,password=? where userid=? ";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, userId);
			if (ps.execute() == false) {
				System.out.println("Email and password updated successfully");
				UserDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				UserDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateUser(int userId, String city) {
		try {
			query = "update user set city=? where userid=? ";
			ps = con.prepareStatement(query);
			ps.setString(1, city);
			ps.setInt(2, userId);
			if (ps.execute() == false) {
				System.out.println("city updated successfully");
				UserDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				UserDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateUser(int userId, long mobile) {
		try {
			query = "update user set mobile=? where userid=? ";
			ps = con.prepareStatement(query);
			ps.setLong(1, mobile);
			ps.setInt(2, userId);
			if (ps.execute() == false) {
				System.out.println("Email and password updated successfully");
				UserDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				UserDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}



}
