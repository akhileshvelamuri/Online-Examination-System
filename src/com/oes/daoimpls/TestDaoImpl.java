package com.oes.daoimpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.oes.daointerfaces.TestDao;
import com.oes.models.Question;
import com.oes.models.Subject;
import com.oes.models.Test;
import com.oes.utilities.ConnectionFactory;

public class TestDaoImpl implements TestDao {

	@Override
	public int createTest(Test test) {
		int result = 0;
		ResultSet rs = null;
		int test_id = 0;
		
			try {
				
					Connection con = ConnectionFactory.getConnection();
				
					String sql = "INSERT INTO Test (test_id, name, duration, max_score ,subject_id) VALUES (?,?,?,?,?)";
				
					PreparedStatement ps = con.prepareStatement(sql);
					
					ps.setInt(1, test.getTest_id());
					ps.setString(2, test.getName());
					ps.setString(3, test.getDuration().toString());
					ps.setInt(4, test.getMax_score());
					ps.setInt(5, test.getSubject_id());
					
					result = ps.executeUpdate();
					
					ps.close();
					con.close();
					
					test_id = test.getTest_id();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("TestDao insert " + test_id);
				
				return test_id;
	}

	@Override
	public List<Test> getTestList() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		List<Test> testList = new ArrayList<Test>();
		
		try{
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM Test";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int test_id = rs.getInt("test_id");
				String name = rs.getString("name");
				Time duration = rs.getTime("duration");
				int max_score = rs.getInt("max_score");
				Subject sub = new Subject();
				sub.setSubject_id(rs.getInt("subject_id"));
				
				Test tst = new Test();
				tst.setTest_id(test_id);
				tst.setName(name);
				tst.setDuration(duration);
				tst.setMax_score(max_score);
				tst.setSubject_id(sub.getSubject_id());
				
				testList.add(tst);
			}
			
			ps.close();
			conn.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		System.out.println("DAO " + testList.size());

		return testList;
	}

	@Override
	public int editTest(Test test) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		int tst_id = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
			String sql = "UPDATE Test SET name = ? , duration = ? , max_score = ? , subject_id = ? WHERE test_id = ?";
			
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, test.getName());
			ps.setString(2, test.getDuration().toString());
			ps.setInt(3, test.getMax_score());
			ps.setInt(4, test.getSubject_id());
			ps.setInt(5, test.getTest_id());

			result = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			rs.next();
			tst_id = test.getTest_id();
			

			ps.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("TestDao update " + tst_id);
		
		return tst_id;
	}

	@Override
	public boolean deleteTest(int test_id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
		
			String query = "DELETE FROM Test WHERE test_id = ?";
			ps = con.prepareStatement(query);
		    ps.setInt(1, test_id);
		    
		    result = ps.executeUpdate();
		    
		    if(result > 0)
		    {
		    	return true;
		    }
			
		    ps.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("TestDao delete " + test_id);
	
		return false;
	}

	@Override
	public Test getTest(int test_id) {
		Connection con = null;
		Test tst = null;
		
		try
		{
			con = ConnectionFactory.getConnection();
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM Test WHERE test_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, test_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				String name = rs.getString("name");
				Time duration = rs.getTime("duration");
				int max_score = rs.getInt("max_score");
				Subject sub = new Subject();
				sub.setSubject_id(rs.getInt("subject_id"));
				
				tst = new Test();
				tst.setTest_id(test_id);
				tst.setName(name);
				tst.setDuration(duration);
				tst.setMax_score(max_score);
				tst.setSubject_id(sub.getSubject_id());		
			}
			
			ps.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return tst;
	}

	@Override
	public int getMaxScoreOfTest(int test_id) {
		Connection con = null;
		Test tst = null;
		int max_score = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
			Statement stmt = con.createStatement();
			
			String sql = "SELECT max_score FROM Test WHERE test_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, test_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				max_score = rs.getInt("max_score");
				
				tst = new Test();
				tst.setMax_score(max_score);	
			}
			
			ps.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return max_score;
	}
}
