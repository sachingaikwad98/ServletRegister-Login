package com.soft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.mysql.cj.protocol.Resultset;
import com.soft.dao.DbConnection;

public class LoginServlet extends GenericServlet{

	Connection con = null;
	public void init() {
		try {
			con = DbConnection.createconnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	
		try {
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			
			String Mail = req.getParameter("Mail");
			String Password = req.getParameter("password");
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM servlet.servlet3 where UserMail=? AND UserPassword =?");
			ps.setString(1,Mail );
			ps.setString(2, Password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				pw.println("Login Succesfull");
				pw.println("Welcome::"+ rs.getString(2));
			}else {
				pw.println("Error in LOgin");
			}
		
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void destroy() {

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
