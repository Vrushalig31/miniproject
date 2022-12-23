package com.quiz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.quiz.connection.CreateConnection;

public class UserLogin {
	CreateConnection cc = new CreateConnection();
	Connection con = cc.getConnect();
	PreparedStatement ps = null;
	boolean flag;
	User user = new User();

	public void userLogin() {
		Scanner sc = new Scanner(System.in);

		System.out.println("***** USER LOGIN PAGE *****");
		System.out.println("Enter Your Email ");
		String email = sc.nextLine();
		System.out.println("Enter Password");
		String password = sc.nextLine();
		user.setEmail(email);
		user.setPassword(password);
		validateUser();
		if (flag == true) {
			System.out.println("User Login successful");
		UserDashboard.selectOption();
		} 
		else {
			System.out.println("Email or password incorrect please try again");
			userLogin();
		}

		sc.close();
	}

	public void validateUser() {
		try {
			String query = "select email,password from user where email=? and password=?";
			ps = con.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
