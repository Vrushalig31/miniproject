package com.quiz.questions;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.quiz.admin.AdminDashboard;
import com.quiz.connection.CreateConnection;

public class UpdateQuestions {
	CreateConnection cc = new CreateConnection();
	Connection con = cc.getConnect();
	PreparedStatement ps = null;
	String query = null;

	public void updateQuestion(int queId, String question, String opt1, String opt2, String opt3, String opt4,
			String answer) {
		try {
			query = "update questions set question=?,option1=?,option2=?,option3=?,option4=?,answer=? where queId=?";
			ps = con.prepareStatement(query);
			ps.setString(1, question);
			ps.setString(2, opt1);
			ps.setString(3, opt2);
			ps.setString(4, opt3);
			ps.setString(5, opt4);
			ps.setString(6, answer);
			ps.setInt(7, queId);
			int c = ps.executeUpdate();
			if (c > 0) {
				System.out.println("Question updated successfully");
				AdminDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				AdminDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateQuestion(int queId, String question) {
		try {
			query = "update questions set question=? where queid=?";
			ps = con.prepareStatement(query);
			ps.setString(1, question);
			ps.setInt(2, queId);
			int c = ps.executeUpdate();
			if (c > 0) {
				System.out.println("Question updated successfully");
				AdminDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				AdminDashboard.selectOption();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void updateQuestion(int queId, String opt1, String opt2, String opt3, String opt4) {
		try {
			query = "update questions set option1=?,option2=?,option3=?,option4=? where queId=?";
			ps = con.prepareStatement(query);
			ps.setString(1, opt1);
			ps.setString(2, opt2);
			ps.setString(3, opt3);
			ps.setString(4, opt4);
			ps.setInt(5, queId);
			int c = ps.executeUpdate();
			if (c > 0) {
				System.out.println("Options updated successfully");
				AdminDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				AdminDashboard.selectOption();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void updateQuestion(String answer, int queId) {
		try {
			query = "update questions set answer=? where queid=?";
			ps = con.prepareStatement(query);
			ps.setString(1, answer);
			ps.setInt(2, queId);
			int c = ps.executeUpdate();
			if (c > 0) {
				System.out.println("Answer updated successfully");
				AdminDashboard.selectOption();
			} else {
				System.out.println("Updation fail");
				AdminDashboard.selectOption();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
