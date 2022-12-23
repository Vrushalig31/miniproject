package com.quiz.questions;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.quiz.admin.*;
import com.quiz.connection.*;



public class QuestionsOperation {
	static int queId;
	static String question;
	static String option1;
	static String option2;
	static String option3;
	static String option4;
	static String answer;
	static int catId;

	CreateConnection cc = new CreateConnection();
	Connection con = cc.getConnect();
	PreparedStatement ps = null;
	String query = null;
	Scanner sc = new Scanner(System.in);
	UpdateQuestions update = new UpdateQuestions();

	public void getQuestion() {
		String a[] = { "A", "B", "C", "D", "a", "b", "c", "d" };
		System.out.println("** QUESTION PAGE **");
		while (true) {
			System.out.println("Enter question");
			question = sc.nextLine();
			System.out.println("Enter option1");
			option1 = sc.nextLine();
			System.out.println("Enter option2");
			option2 = sc.nextLine();
			System.out.println("Enter option3");
			option3 = sc.nextLine();
			System.out.println("Enter option4");
			option4 = sc.nextLine();
			System.out.println("Enter Answer");
			answer = sc.nextLine();
			System.out.println("Enter category Id");
			catId = Integer.parseInt(sc.nextLine());

			Questions que = new Questions();
			que.setQuestion(question);
			que.setOption1(option1);
			que.setOption2(option2);
			que.setOption3(option3);
			que.setOption4(option4);
			que.setAnswer(answer);
			que.setCatid(catId);

			if (Arrays.asList(a).contains(answer)) {
				insertQuestion(que);
				System.out.println("Question added successfully");
				System.out.println("Do you want to add one more question [yes/no]");
				String choice = sc.nextLine();
				if (choice.equalsIgnoreCase("no")) {
					AdminDashboard.selectOption();
					break;
				}
			} else if (!(Arrays.asList(a).contains(answer))) {
				System.out.println("Invalid Answer");

			} else {
				System.out.println("Question Not Added");
				AdminDashboard.selectOption();
			}
		}

	}

	public void createQuestion() {
		try {
			DatabaseMetaData db = con.getMetaData();
			ResultSet table = db.getTables(null, null, "Questions", new String[] { "TABLE" });
			if (table.next()) {
				System.out.println();
			} else {
				query = "create table questions(queid int not null auto_increment primary key,question varchar(255),option1 varchar(255),option2 varchar(255),option3 varchar(255),option4 varchar(255),answer varchar(10),catid int,foreign key(catid) references quizcategory(catid))";
				ps = con.prepareStatement(query);
				if (ps.execute() == false) {
					System.out.println("Question table created successfully");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public boolean insertQuestion(Questions que) {
		boolean status = false;
		try {
			createQuestion();
			query = "insert into questions(question,option1,option2,option3,option4,answer,catid) values (?,?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, que.getQuestion());
			ps.setString(2, que.getOption1());
			ps.setString(3, que.getOption2());
			ps.setString(4, que.getOption3());
			ps.setString(5, que.getOption4());
			ps.setString(6, que.getAnswer());
			ps.setInt(7, que.getCatid());
			int c = ps.executeUpdate();
			if (c > 0) {
				status = true;
			} else {
				status = false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public void getUpdateQuestion() {
		System.out.println("Enter Question id");
		queId = Integer.parseInt(sc.nextLine());
		System.out.println("Enter which field do you eant to update [All/Question/Options/Answer]");
		String option = sc.nextLine();

		switch (option) {
		case "All":
			System.out.println("Enter question");
			question = sc.nextLine();
			System.out.println("Enter option1");
			option1 = sc.nextLine();
			System.out.println("Enter option2");
			option2 = sc.nextLine();
			System.out.println("Enter option3");
			option3 = sc.nextLine();
			System.out.println("Enter option4");
			option4 = sc.nextLine();
			System.out.println("Enter answer");
			answer = sc.nextLine();
			update.updateQuestion(queId, question, option1, option2, option3, option4, answer);
			break;

		case "Question":
			System.out.println("Enter question");
			question = sc.nextLine();
			update.updateQuestion(queId, question);
			break;

		case "Option":
			System.out.println("Enter option1");
			option1 = sc.nextLine();
			System.out.println("Enter option2");
			option2 = sc.nextLine();
			System.out.println("Enter option3");
			option3 = sc.nextLine();
			System.out.println("Enter option4");
			option4 = sc.nextLine();
			update.updateQuestion(queId, option1, option2, option3, option4);
			break;

		case "Answer":
			System.out.println("Enter answer");
			answer = sc.nextLine();
			update.updateQuestion(answer, queId);
			break;

		default:
			System.out.println("Invalid option please enter correct option");
			getUpdateQuestion();
		}
	}

	public void deleteQuestion(int queId) {
		try {
			query = "delete from questions where queid=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, queId);
			int c = ps.executeUpdate();
			if (c > 0) {
				System.out.println("Question deleted successfully");
				AdminDashboard.selectOption();
			} else {
				System.out.println("Deletion fail");
				AdminDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Questions> selectQuestion()
	{
		List<Questions> list=new ArrayList<Questions>();
		try
		{
		query="select * from questions";
		ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			Questions que=new Questions();
			que.setQueid(rs.getInt(1));
			que.setQuestion(rs.getString(2));
			que.setOption1(rs.getString(3));
			que.setOption2(rs.getString(4));
			que.setOption3(rs.getString(5));
			que.setOption4(rs.getString(6));
			que.setAnswer(rs.getString(7));
			que.setCatid(rs.getInt(8));
			list.add(que);
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return list;
	}

}
