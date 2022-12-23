package com.quiz.admin;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.quiz.category.CreateCategory;
import com.quiz.main.Quiz;
import com.quiz.questions.Questions;
import com.quiz.questions.QuestionsOperation;
import com.quiz.user.SearchUser;


public class AdminDashboard {

	public static void selectOption() {
		Scanner sc = new Scanner(System.in);
		QuestionsOperation queop=new QuestionsOperation();
		CreateCategory cat=new CreateCategory();
		SearchUser search=new SearchUser();
		String choice;
		String category;
		int catId;
		System.out.println("** ADMIN DASHBOARD **");
		System.out.println("1.Add Question");
		System.out.println("2.Update Question");
		System.out.println("3.Delete Question");
		System.out.println("4.Add Quiz");
		System.out.println("5.Update Quiz");
		System.out.println("6.Delete Quiz");
		System.out.println("7.View Questions");
		System.out.println("8.View Quiz");
		System.out.println("9.View all Users");
		System.out.println("10.Search User");
		System.out.println("11.Logout");

		System.out.println("Which operation do you want to perform [1/2/3/4/5/6/7/8/9/10/11] :");
		int option = Integer.parseInt(sc.nextLine());

		switch (option) {
		case 1 :
			System.out.println("Do you want to continue with add question [yes/no]");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("no"))
			{
				selectOption();
				break;
			}
			queop.getQuestion();	
			break;

		case 2 :
			System.out.println("Do you want to continue with update [yes/no]");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("no"))
			{
				selectOption();
				break;
			}
			queop.getUpdateQuestion();
			break;

		case 3 :
			System.out.println("Enter question id to delete the question");
			int queId=Integer.parseInt(sc.nextLine());
			System.out.println("Are you sure you want to delete the question [yes/no]");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("no"))
			{
				selectOption();
				break;
				
			}
			queop.deleteQuestion(queId);
			break;

		case 4 :
			System.out.println("Do you want to continue with add [yes/no]");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("no"))
			{
				selectOption();
				break;
			}
			System.out.println("Enter category ");
			category=sc.nextLine();
			cat.insertCategory(category);
			break;
			
		case 5 :
			System.out.println("Do you want to continue with update [yes/no]");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("no"))
			{
				selectOption();
				break;
			}
			System.out.println("Enter category ");
			category=sc.nextLine();
			System.out.println("Enter category id");
			catId=sc.nextInt();
			cat.updateCategory(category, catId);
			break;

		case 6 :
			System.out.println("Do you want to continue with delete [yes/no]");
			choice=sc.nextLine();
			if(choice.equalsIgnoreCase("no"))
			{
				selectOption();
				break;
			}
			System.out.println("Enter category ");
			category=sc.nextLine();
			cat.deleteCategory(category);
			break;

		case 7 :
		
			List<Questions> al=queop.selectQuestion();
			for(Questions que:al)
			{
				System.out.println("Que. "+que.getQueid());
				System.out.println(que.getQuestion());
				System.out.println("A."+que.getOption1());
				System.out.println("B."+que.getOption2());
				System.out.println("C."+que.getOption3());
				System.out.println("D."+que.getOption4());
				System.out.println("Answer : "+que.getAnswer());
			}
			selectOption();
			break;

		case 8 :
			cat.selectCategory();
			selectOption();
			break;

		case 9 :
			search.getAllUser();
			selectOption();
			break;

		case 10 :
			System.out.println("Enter Id to Search user");
			int userId=Integer.parseInt(sc.nextLine());
			search.getUserById(userId);
			selectOption();
			break;

		case 11 :
			Quiz.selectLogin();
			break;
		}
		sc.close();
	}

}