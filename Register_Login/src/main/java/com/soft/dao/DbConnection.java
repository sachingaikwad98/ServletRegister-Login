package com.soft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static Connection createconnection() throws ClassNotFoundException, SQLException {
		
		Connection con = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","Jaigajanan@98");
		return con;
		
	}
	
}
