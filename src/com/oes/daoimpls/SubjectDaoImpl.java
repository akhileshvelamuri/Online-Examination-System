package com.oes.daoimpls;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.oes.daointerfaces.SubjectDao;
import com.oes.models.Question;
import com.oes.models.Subject;
import com.oes.utilities.ConnectionFactory;


public class SubjectDaoImpl implements SubjectDao {

	@Override
	public int addSubject(Subject subject) {
		int result = 0;
		ResultSet rs = null;
		int sub = 0;
		
		try {
			
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "INSERT INTO Subject (subject_id, name) VALUES (?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, subject.getSubject_id());
		ps.setString(2, subject.getName());
		
		result = ps.executeUpdate();
		
		con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return subject.getSubject_id();
	}

	@Override
	public List<Subject> getSubjectList() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		List<Subject> subjectList = new ArrayList<Subject>();
		
		try{
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM Subject";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int id = rs.getInt("subject_id");
				String name = rs.getString("name");
				
				
				Subject subject = new Subject();
				subject.setSubject_id(id);
				subject.setName(name);
				
				subjectList.add(subject);
			}
			
			ps.close();
			conn.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
			System.out.println("DAO " + subjectList.size());

				return subjectList;
	}
	
	@Override
	public boolean deleteSubject(int subject_id) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
			
			String sql = "DELETE FROM Subject WHERE subject_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject_id);
			
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
		
		System.out.println("SubjectDao delete " + subject_id);
		
		return false;
	}

	@Override
	public int editSubject(Subject sub) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		int sub_id = 0;
		
		try
		{
			con = ConnectionFactory.getConnection();
			String sql = "UPDATE Subject SET name = ? WHERE subject_id = ?";
			
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, sub.getName());
			ps.setInt(2, sub.getSubject_id());
			
			result = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			rs.next();
			sub_id = sub.getSubject_id();
			
			ps.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("SubjectDao update " + sub_id);
		
		return sub_id;
	}

	@Override
	public Subject getSubject(int subject_id) {
		Connection con = null;
		Subject sub = null;
		
		try
		{
			con = ConnectionFactory.getConnection();
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * FROM Subject WHERE subject_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, subject_id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				String name = rs.getString("name");
				
				sub = new Subject();
				sub.setSubject_id(subject_id);
				sub.setName(name);
				
				
			}
			
			ps.close();
			con.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return sub;
	}
}