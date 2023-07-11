package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Update() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int salary = Integer.parseInt(request.getParameter("salary"));
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					"admin");
			
			System.out.println("Connection established!");
			String updateQuery = "UPDATE Employee SET Salary=? WHERE Id=?";
			
			PreparedStatement ps = con.prepareStatement(updateQuery);
			
			ps.setInt(1, salary);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			ps.close();
			
		}catch(Exception e) {
			System.out.println("Connection Failed!");
		}
	}

}
