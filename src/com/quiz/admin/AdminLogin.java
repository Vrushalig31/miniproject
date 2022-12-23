package com.quiz.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.quiz.connection.CreateConnection;

public class AdminLogin {
	CreateConnection cc=new CreateConnection();
	Connection con=cc.getConnect();
	Admin admin=new Admin();
	boolean status;
	
	public void adminLogin() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Username:");
		String username=sc.nextLine();
		System.out.println("Enter password:");
		String password=sc.nextLine();
		admin.setUsername(username);
		admin.setPassword(password);
		validateLogin();
		if(status==true) {
			System.out.println("Login Successfully!");
			AdminDashboard.selectOption();
		}
		else {
			System.out.println("Username & password incorrect!");
			adminLogin();
		}
		sc.close();
		
	}

	public void validateLogin() {
		try {
			String query="select * from Admin where username=? and password=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, admin.getUsername());
			ps.setString(2, admin.getPassword());
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				status=true;
				System.out.println(rs.getString(2));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

	
}
