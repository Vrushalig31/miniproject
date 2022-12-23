package com.quiz.main;

import java.util.Scanner;

import com.quiz.admin.AdminLogin;
import com.quiz.category.*;
import com.quiz.connection.*;
import com.quiz.user.UserDashboard;
import com.quiz.user.UserLogin;
import com.quiz.user.UserOperation;

public class Quiz {
	public static void selectLogin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to login as Admin or User");
		String role = sc.nextLine();

		while (true) {
			if (role.equalsIgnoreCase("admin")) {
				AdminLogin admin = new AdminLogin();
				admin.adminLogin();
				break;
			} else if (role.equalsIgnoreCase("User")) {
				System.out.println("Do you have Account[yes/no]");
				String choice = sc.nextLine();
				if (choice.equalsIgnoreCase("yes")) {
					UserLogin userlogin = new UserLogin();
					userlogin.userLogin();
					UserDashboard.selectOption();
				} else {
					System.out.println("Create your Account");
					UserOperation userop = new UserOperation();
					userop.getUser();
				}
				break;

			} else {
				System.out.println("Please select correct role");
			}
		}
		sc.close();
	}


}
