package com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Delete() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					"admin");
			
			System.out.println("Connection established!");
			
			String deleteQuery = "DELETE FROM Employee WHERE id=?";
			
			PreparedStatement ps = con.prepareStatement(deleteQuery);
			
			ps.setInt(1, id);
			
			ps.execute();
			ps.close();
			
		}catch(Exception e) {
			System.out.println("Connection Failed!");
		}
	}

}
