package com.quiz.result;

import com.quiz.connection.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateResult {
	CreateConnection cc = new CreateConnection();
	Connection con=cc.getConnect();
	PreparedStatement ps=null;
	String query=null;
	
	public void createResult()
	{
		try
		{
			DatabaseMetaData db=con.getMetaData();
			ResultSet table=db.getTables(null,null, "Result", new String[] {"TABLE"});
			if(table.next())
			{
				System.out.println();
			}
			else
			{
				query="create table Result(resultid int not null auto_increment primary key,userid int,catid int,marks int,grade varchar(10),date date,foreign key(userid) references user(userid),foreign key(catid) references quizcategory(catid))";
				ps=con.prepareStatement(query);
				if(ps.execute()==false)
				{
					System.out.println("Table created successfully");
				}
				else
				{
					System.out.println("Something went wrong try again");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void insertResult(Result result)
	{
		try
		{
			createResult();
			query="insert into result(userid,catid,marks,grade,date) values(?,?,?,?,?)";
			ps=con.prepareStatement(query);
			long millis=System.currentTimeMillis();  
	        Date date=new Date(millis); 
			ps.setInt(1,result.getUserId());
			ps.setInt(2,result.getCatId());
			ps.setInt(3,result.getMarks());
			ps.setString(4,result.getGrade());
			ps.setDate(5,date);
			int c=ps.executeUpdate();
			if(c>0)
			{
				System.out.println("Result added");
			}
			else
			{
				System.out.println("Insertion fail");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


}
