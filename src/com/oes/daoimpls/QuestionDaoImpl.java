package com.oes.daoimpls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.oes.daointerfaces.QuestionDao;
import com.oes.models.Question;
import com.oes.models.Subject;
import com.oes.utilities.ConnectionFactory;

public class QuestionDaoImpl implements QuestionDao {

	@Override
	public int insertQuestion(Question question) {
		int result = 0;
		ResultSet rs = null;
		int qn_id = 0;
		
		try {
		
			Connection con = ConnectionFactory.getConnection();
		
			String sql = "INSERT INTO Question (question,choice1,choice2,choice3,choice4,answer,marks,subject_id) VALUES (?,?,?,?,?,?,?,?)";
		
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, question.getQuestion());
			ps.setString(2, question.getChoice1());
			ps.setString(3, question.getChoice2());
			ps.setString(4, question.getChoice3());
			ps.setString(5, question.getChoice4());
			ps.setString(6, question.getAnswer());
			ps.setInt(7, question.getMarks());
			ps.setInt(8, question.getSubject_id());
			
			result = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			rs.next();
			qn_id = rs.getInt(1);
			
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("QuestionDao insert " + qn_id);
		
		
		return qn_id;
	}

	@Override
	public boolean deleteQuestion(int question_id) {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
			
			String sql = "DELETE FROM Question WHERE question_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, question_id);
			
			result = ps.executeUpdate();
			
			if(result > 0)
			{
				return true;
			}
			
			ps.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("QuestionDao delete " + question_id);
		
		return false;
	}

	@Override
	public Question getQuestion(int question_id) {
		Connection con = null;
		Question quesn = null;
		
		try
		{
			con = ConnectionFactory.getConnection();
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM Question WHERE question_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, question_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				String qn = rs.getString("question");
				String choice1 = rs.getString("choice1");
				String choice2 = rs.getString("choice2");
				String choice3 = rs.getString("choice3");
				String choice4 = rs.getString("choice4");
				String answer = rs.getString("answer");
				int marks = rs.getInt("marks");
				int subject_id = rs.getInt("subject_id");
				
				quesn = new Question();
				quesn.setQuestion_id(question_id);
				quesn.setQuestion(qn);
				quesn.setChoice1(choice1);
				quesn.setChoice2(choice2);
				quesn.setChoice3(choice3);
				quesn.setChoice4(choice4);
				quesn.setAnswer(answer);
				quesn.setMarks(marks);
				quesn.setSubject_id(subject_id);
			}
			
			ps.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return quesn;
	}
	
	@Override
	public List<Question> getQuestionList() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		List<Question> questionList = new ArrayList<Question>();
		
		try{
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM Question";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int question_id = rs.getInt("question_id");
				String question = rs.getString("question");
				String choice1 = rs.getString("choice1");
				String choice2 = rs.getString("choice2");
				String choice3 = rs.getString("choice3");
				String choice4 = rs.getString("choice4");
				String answer = rs.getString("answer");
				int marks = rs.getInt("marks");
				Subject sub = new Subject();
				sub.setSubject_id(rs.getInt("subject_id"));
			
				Question qn = new Question();
				qn.setQuestion_id(question_id);
				qn.setQuestion(question);
				qn.setChoice1(choice1);
				qn.setChoice2(choice2);
				qn.setChoice3(choice3);
				qn.setChoice4(choice4);
				qn.setAnswer(answer);
				qn.setMarks(marks);
				qn.setSubject_id(sub.getSubject_id());
				
				questionList.add(qn);
			}
			
			ps.close();
			conn.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
			System.out.println("DAO " + questionList.size());

				return questionList;
	}

	@Override
	public int editQuestion(Question qn) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		int qn_id = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
			String sql = "UPDATE Question SET question = ? , choice1 = ? , choice2 = ? , choice3 = ? , choice4 = ? , answer = ? , marks = ? , subject_id = ? WHERE question_id = ?";
			
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, qn.getQuestion());
			ps.setString(2, qn.getChoice1());
			ps.setString(3, qn.getChoice2());
			ps.setString(4, qn.getChoice3());
			ps.setString(5, qn.getChoice4());
			ps.setString(6, qn.getAnswer());
			ps.setInt(7, qn.getMarks());
			ps.setInt(8, qn.getSubject_id());
			ps.setInt(9, qn.getQuestion_id());
			
			result = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			rs.next();
			qn_id = qn.getQuestion_id();
			
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("QuestionDao update " + qn_id);
		
		return qn_id;
	}

	@Override
	public List<Question> getQuestionList(int subject_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		Question qn = null;
		List<Question> questionList = new ArrayList<Question>();
		
		try{
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM Question WHERE subject_id = ?";
			
			ps.setInt(1, subject_id);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int question_id = rs.getInt("question_id");
				String question = rs.getString("question");
				String choice1 = rs.getString("choice1");
				String choice2 = rs.getString("choice2");
				String choice3 = rs.getString("choice3");
				String choice4 = rs.getString("choice4");
				String answer = rs.getString("answer");
				int marks = rs.getInt("marks");
				
				Question quesn = new Question();
				quesn.setQuestion_id(question_id);
				quesn.setQuestion(question);
				quesn.setChoice1(choice1);
				quesn.setChoice2(choice2);
				quesn.setChoice3(choice3);
				quesn.setChoice4(choice4);
				quesn.setAnswer(answer);
				quesn.setMarks(marks);
				quesn.setSubject_id(subject_id);
				
				questionList.add(quesn);
			}
			
			ps.close();
			conn.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
			System.out.println("DAO " + questionList.size());

				return questionList;
	}

}