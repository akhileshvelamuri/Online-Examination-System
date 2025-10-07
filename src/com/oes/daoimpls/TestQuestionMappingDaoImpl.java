package com.oes.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.oes.daointerfaces.TestQuestionMappingDao;
import com.oes.models.Question;
import com.oes.models.Subject;
import com.oes.models.Test;
import com.oes.models.TestQuestionMapping;
import com.oes.utilities.ConnectionFactory;

public class TestQuestionMappingDaoImpl implements TestQuestionMappingDao {

	@Override
	public int addQuestionstoList(List<TestQuestionMapping> questionList) {
		
		int result = 0;
		Connection con = ConnectionFactory.getConnection();
		ResultSet rs = null;
		int id = 0;
		
		try
		{
			for(TestQuestionMapping tq: questionList) {
				String sql = "INSERT INTO testquestionmapping (test_id, question_id) VALUES (?,?)";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, tq.getTest_id());
				ps.setInt(2,  tq.getQuestion_id());
				
				result = ps.executeUpdate();
				
				ps.close();
	    }
			con.close();
			
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(result > 0)
		{
			System.out.println("Questions INSERTED Successfully");
		}
		else
		{
			System.out.println("Questions INSERTION UNSUCCESSFUL");
		}
		
		return result;
	}

	@Override
	public int addQuestionstoListbyTestID(List<TestQuestionMapping> questionList2, int test_id) {
		int result = 0;
		Connection con = ConnectionFactory.getConnection();
		ResultSet rs = null;
		int id = 0;
		
		try
		{
			for(TestQuestionMapping tq: questionList) {
				String sql = "INSERT INTO testquestionmapping (question_id) VALUES (?) WHERE test_id = ?";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1,  tq.getQuestion_id());
				ps.setInt(2, test_id);
				
				result = ps.executeUpdate();
				
				ps.close();
	    }
			con.close();
			
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(result > 0)
		{
			System.out.println("Questions INSERTED Successfully");
		}
		else
		{
			System.out.println("Questions INSERTION UNSUCCESSFUL");
		}
		
		return result;
	}

	@Override
	public List<TestQuestionMapping> getQuestionsfromTest(int test_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		List<TestQuestionMapping> testquestions = new ArrayList<TestQuestionMapping>();
		
		try{
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM testquestionmapping WHERE test_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, test_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int question_id = rs.getInt("question_id");
				
				TestQuestionMapping tqm = new TestQuestionMapping();
				tqm.setTest_id(test_id);
				tqm.setQuestion_id(question_id);
				
				testquestions.add(tqm);
			}
			
			ps.close();
			conn.close();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
		System.out.println("DAO " + testquestions.size());
		
		return testquestions;
	}

	@Override
	public List<TestQuestionMapping> getQuestionsfromTests() {
		Connection conn = null;
		PreparedStatement ps = null;
		List<TestQuestionMapping> testquestionsList = new ArrayList<TestQuestionMapping>();
		
		try{
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM testquestionmapping";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int test_id = rs.getInt("test_id");
				int question_id = rs.getInt("question_id");
				
				TestQuestionMapping tqm = new TestQuestionMapping();
				tqm.setTest_id(test_id);
				tqm.setQuestion_id(question_id);
				
				testquestionsList.add(tqm);
			}
			
			ps.close();
			conn.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		System.out.println("DAO " + testquestionsList.size());

		return testquestionsList;
	}

	@Override
	public boolean deleteQuestionsfromTest(int test_id, int question_id) {

		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
			
			String sql = "DELETE FROM testquestionmapping WHERE test_id = ? AND question_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, test_id);
			ps.setInt(2, question_id);
			
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
		
		System.out.println("DeleteQuestionsfromTest Dao delete " + test_id + question_id);
		
		return false;
	}
}
