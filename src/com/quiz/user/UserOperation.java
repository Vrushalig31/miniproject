package com.quiz.user;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.quiz.connection.CreateConnection;

public class UserOperation {
	static int userId;
	static String username;
	static String email;
	static String password;
	static String city;
	static long mobile;
	static int adminId=1;
	
	CreateConnection cc =new CreateConnection();
	Connection con=cc.getConnect();
	PreparedStatement ps =null;
	String query =null;
	User user =new User();
	UpdateUserInfo update =new UpdateUserInfo();
	Scanner sc=new Scanner(System.in);
	
	
	public  void getUser() {
		System.out.println("***** Register Yourself as User *****");
		System.out.println("Enter your fullName:");
		username =sc.nextLine();
		System.out.println("Enter Your Email:");
		email =sc.nextLine();
		System.out.println("Enter Your Password:");
		password =sc.nextLine();
		System.out.println("Enter Your City:");
		city=sc.nextLine();
		System.out.println("Enter Mobile Number:");
		mobile=sc.nextLong();
		
		User user =new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setCity(city);
		user.setMobile(mobile);
		user.setAdminId(adminId);
		insertUser(user);
		
		
	}
	public void createUser() {
		try {
			DatabaseMetaData db = con.getMetaData();
			ResultSet table = db.getTables(null, null, "User", new String[] { "TABLE" });
			if (table.next()) {
				System.out.println();
			} else {
				String query = "create table user(userid int not null auto_increment primary key, username varchar(255),email varchar(255),password varchar(15),city varchar(50),mobile numeric(10))";
				ps = con.prepareStatement(query);
				if (ps.execute() == false)
					System.out.println("User Table created successfully");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void insertUser(User user) {
		try {
			createUser();
			String query = "insert into user(username,email,password,city,mobile) values (?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getCity());
			ps.setLong(5, user.getMobile());
			int count = ps.executeUpdate();
			if (count > 0) {
				System.out.println("Registration Succeessful");
				UserDashboard.selectOption();
			} else {
				System.out.println("Registration unsuccessful please try again..");
				insertUser(user);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void getUpdateUser() {

		System.out.println("Enter User Id");
		userId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter which field you want to update [All/Username/Email&password/City/Mobile]");
		String option = sc.nextLine();

		switch (option) {

		case "All":
			System.out.println("Enter username");
			username = sc.nextLine();
			System.out.println("Enter email");
			email = sc.nextLine();
			System.out.println("Enter password");
			password = sc.nextLine();
			System.out.println("Enter city");
			city = sc.nextLine();
			System.out.println("Enter Mobile number");
			mobile = sc.nextLong();
			update.updateUser(userId, username, email, password, city, mobile);
			break;

		case "Username":
			System.out.println("Enter username");
			username = sc.nextLine();
			update.updateUser(username, userId);
			break;

		case "Email&password":
			System.out.println("Enter Email");
			email = sc.nextLine();
			System.out.println("Enter password");
			password = sc.nextLine();
			update.updateUser(userId, email, password);
			break;

		case "City":
			System.out.println("Enter City");
			city = sc.nextLine();
			update.updateUser(userId, city);
			break;

		default:
			System.out.println("Invalid input please enter correct option");
			getUpdateUser();
		}
	}

	public void deleteUser(int userId) {
		try {
			String query = "delete from user where userid=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			if (ps.execute() == false) {
				System.out.println("User record deleted successfully");
				getUser();
			} else {
				System.out.println("Deletion fail");
				UserDashboard.selectOption();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
