package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Create extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Create() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String designation = request.getParameter("designation");
		int salary = Integer.parseInt(request.getParameter("salary"));
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					"admin");
			System.out.println("Connection established successfully!");
						
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?)");
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setString(4, designation);
			pstmt.setInt(5, salary);
			
			pstmt.execute();
			pstmt.close();

			
			out.println("data inserted successfully!");
			out.println(id);
			out.println(name);
			out.println(age);
			out.println(salary);
			out.println(designation);
			

			
		}catch(Exception e) {
			System.out.println("Connection Failed!");
		    e.printStackTrace();

		}
	}

	
	

}
