package com.masai.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButils {
	
public static Connection getConnection() throws SQLException {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/Automated_Student_Registration_System";
		
		try {
			connection = DriverManager.getConnection(url, "root", "one@20");
		} 
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return connection;
   }

}
