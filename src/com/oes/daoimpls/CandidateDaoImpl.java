package com.oes.daoimpls;

import java.sql.*;

import com.oes.daointerfaces.CandidateDao;
import com.oes.models.Candidate;
import com.oes.utilities.ConnectionFactory;

public class CandidateDaoImpl implements CandidateDao {

	@Override
	public int insertCandidate(Candidate candidate) {
		int result = 0;
		ResultSet rs = null;
		int candidate_id = 0;
		
		try {
		
			Connection con = ConnectionFactory.getConnection();
		
			String sql = "INSERT INTO Candidate(first_name,last_name,qualification,email,password,admin) VALUES(?,?,?,?,?,?)";
		
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, candidate.getFirst_name());
			ps.setString(2, candidate.getLast_name());
			ps.setString(3, candidate.getQualification());
			ps.setString(4, candidate.getEmail());
			ps.setString(5, candidate.getPassword());
			ps.setInt(6, candidate.getAdmin());
			
			result = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			rs.next();
			candidate_id = rs.getInt(1);
			
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("CandidateDao insert " + candidate_id);
		
		return candidate_id;
	}

	@Override
	public Candidate getCandidate(String email) {
		Connection con = null;
		Candidate candidate = null;
		int result = 0;
		
		try {
				con = ConnectionFactory.getConnection();
				Statement stmt = con.createStatement();
				
				String sql = "SELECT candidate_id,first_name,last_name,qualification,password,admin FROM Candidate WHERE email = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					int candidate_id = rs.getInt("candidate_id");
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String qualification = rs.getString("qualification");
					String password = rs.getString("password");
					int admin = rs.getInt("admin");
					
					candidate = new Candidate();
					candidate.setCandidate_id(candidate_id);
					candidate.setFirst_name(first_name);
					candidate.setLast_name(last_name);
					candidate.setQualification(qualification);
					candidate.setEmail(email);
					candidate.setPassword(password);
					candidate.setAdmin(admin);
					
				}
				
				ps.close();
				con.close();
				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("3" + candidate);

		return candidate;
	}

}
