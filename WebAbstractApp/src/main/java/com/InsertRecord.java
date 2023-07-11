package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertRecord() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int salary = Integer.parseInt(request.getParameter("salary"));
		String designation = request.getParameter("designation");

		try {
			
			/*
			 * ServletConfig config=getServletConfig(); String
			 * drivername=config.getInitParameter("driver"); String
			 * url=config.getInitParameter("url"); String
			 * username=config.getInitParameter("username"); String
			 * password=config.getInitParameter("password");
			 */
	        
	        ServletContext context=getServletContext();
            
	        String drivername=context.getInitParameter("driver");
            String url=context.getInitParameter("url");
            String username=context.getInitParameter("username");
            String password=context.getInitParameter("password");

			
	        Class.forName(drivername);
	        Connection connection=DriverManager.getConnection(url, username,password);
	        
			System.out.println("Connection established successfully!");

			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?)");

			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setFloat(4, salary);
			pstmt.setString(5, designation);

			pstmt.execute();
			pstmt.close();

			PrintWriter out = response.getWriter();
			out.print("<h1>");
			out.println("Data inserted successfully!");
			out.print(id);
			out.print(name);
			out.print(age);
			out.print(salary);
			out.print(designation);

			out.print("<a href=\"Index.html\">HOME PAGE</a>");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}

	}

}
