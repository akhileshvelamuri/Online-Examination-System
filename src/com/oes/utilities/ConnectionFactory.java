package com.oes.utilities;

import java.sql.*;

public class ConnectionFactory {
	
	public static Connection getConnection()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oes","root","root");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return con;
	}
}