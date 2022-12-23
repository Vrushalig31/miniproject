package com.quiz.user;

import java.util.Scanner;

import com.quiz.main.Quiz;

public class UserDashboard {
	public static void selectOption() {
		String choice;
		int userId;
		UserOperation userop = new UserOperation();
		Scanner sc = new Scanner(System.in);
		System.out.println("** USER DASHBOARD **");
		System.out.println("1.Start Quiz");
		System.out.println("2.View Profile");
		System.out.println("3.Update Profile");
		System.out.println("4.Delete Profile");
		System.out.println("5.Logout");
		System.out.println("Choose which operation you want to perform [1/2/3/4/5] : ");
		int option = Integer.parseInt(sc.nextLine());
		switch (option) {
		case 1 :
			StartQuiz start=new StartQuiz();
			start.getQuiz();
			break;

		case 2 :
			SearchUser search=new SearchUser();
			System.out.println("Enter Id to Search user");
			userId=Integer.parseInt(sc.nextLine());
			search.getUserById(userId);
			selectOption();
			break;

		case 3 :
			System.out.println("Do you want to continue with update [yes/no]");
			choice = sc.nextLine();
			if (choice.equalsIgnoreCase("no")) {
				selectOption();
				break;
			} else
				userop.getUpdateUser();
			break;

		case 4 :
			System.out.println("Enter User Id to delete record");
			userId = Integer.parseInt(sc.nextLine());
			System.out.println("Are you sureyou want to delete record[yes/no]");
			choice = sc.nextLine();
			if (choice.equalsIgnoreCase("no")) {
				selectOption();
				break;
			} else
				userop.deleteUser(userId);
			break;

		case 5 :
			Quiz.selectLogin();
			break;

		default:
			System.out.println("Invalid Input please enter correct option");
			selectOption();
		}
		sc.close();
	}

	
}
