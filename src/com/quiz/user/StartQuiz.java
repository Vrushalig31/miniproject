package com.quiz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.quiz.category.*;
import com.quiz.connection.*;
import com.quiz.questions.*;
import com.quiz.result.*;

public class StartQuiz {

	CreateConnection cc = new CreateConnection();
	Connection con=cc.getConnect();
	PreparedStatement ps=null;
	String query=null;
	ResultSet rs=null;
	Scanner sc=new Scanner(System.in);
	
	public void getQuiz()
	{
		Category cat=new Category();
		CreateCategory category=new CreateCategory();
		List<Questions> list=new ArrayList<Questions>();
		System.out.println("Select which quiz you want to solve ");
		category.selectCategory();
		String choice=sc.nextLine();
		
		try
		{
		query="select * from questions,quizcategory where questions.catid=quizcategory.catid and category=?";
		ps=con.prepareStatement(query);
		ps.setString(1, choice);
		rs=ps.executeQuery();
		while(rs.next())
		{
			Questions que = new Questions();
			que.setQueid(rs.getInt(1));
			que.setQuestion(rs.getString(2));
			que.setOption1(rs.getString(3));
			que.setOption2(rs.getString(4));
			que.setOption3(rs.getString(5));
			que.setOption4(rs.getString(6));
			que.setAnswer(rs.getString(7));
			cat.setCatid(rs.getInt(8));
			cat.setCategory(rs.getString(10));
			
			list.add(que);
		}
		
		Collections.shuffle(list);
		int i=0,correctAns=0,wrongAns=0;
		
		for(Questions que:list)
		{
			System.out.println("Que : "+ ++i +".");
			System.out.println(que.getQuestion());
			System.out.println("A."+que.getOption1());
			System.out.println("B."+que.getOption2());
			System.out.println("C."+que.getOption3());
			System.out.println("D."+que.getOption4());
			String ans=getAnswer();
			if(ans.equals(que.getAnswer()))
			{
				correctAns++;
			}
			else
			{
				wrongAns++;
			}
			if(i==2)
			{
				System.out.println("***** Thanks for the attempt *****\n");
				String grade=getGrade(correctAns,wrongAns);
				System.out.println("Grade : "+grade);
				System.out.println("Enter UserId Id to Exit page");
				int userId=Integer.parseInt(sc.nextLine());
				
				Result result=new Result();
				result.setCatId(cat.getCatid());
				result.setMarks(correctAns);
				result.setGrade(grade);
				result.setUserId(userId);
				
				CreateResult cr=new CreateResult();
				cr.insertResult(result);
				UserDashboard.selectOption();
				break;
			}
		}		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public String getAnswer()
	{
		System.out.println("Enter Answer : ");
		String ans=sc.nextLine();
		return ans;	
	}
	
	public String getGrade(int correctAns,int wrongAns)
	{
		System.out.println("***** Result *****");
		System.out.println("Total questions : "+10);
		System.out.println("Correct Answer : "+correctAns);
		System.out.println("Wrong Answer : "+wrongAns);
		System.out.println("Total Marks : "+correctAns);
		if(correctAns>8 && correctAns<=10)
			return "Grade A";
		else if(correctAns>=6 && correctAns<=8)
			return "Grade B";
		else if(correctAns==5)
			return "Grade C";
		else
			return "Fail";
	}
	



}
