package com.soft.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.soft.dao.DbConnection;

public class RegisterServlet extends GenericServlet {

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
			
			int UserId = Integer.parseInt(req.getParameter("UserId"));
			String UserName = req.getParameter("Name");
			String Mail = req.getParameter("Mail");
			String Password = req.getParameter("password");
			
			PreparedStatement ps = con.prepareStatement("insert into servlet.servlet3 values (?,?,?,?)");
			ps.setInt(1, UserId);
			ps.setString(2, UserName);
			ps.setString(3,Mail );
			ps.setString(4, Password);
			
			int k = ps.executeUpdate();
			
			if (k>0) {
				pw.println("Registractionis Successfull");
				pw.println("<a href='Login.html'>Login");
			} else {
				pw.println("Error in Registration");
			}
			
		} catch (Exception e) {
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
