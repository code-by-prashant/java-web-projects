package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteRecord() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root",
					"admin");
			System.out.println("Connection established successfully!");

			PreparedStatement pstmt = connection.prepareStatement("DELETE FROM Employee WHERE Id = ?");

			pstmt.setInt(1, id);

			pstmt.execute();
			pstmt.close();
			
			PrintWriter out = response.getWriter();

			out.println("\nData deleted successfully!");
			out.print("<a href=\"Index.html\">HOME PAGE</a>");


		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Failed to delete record from the database!");
			e.printStackTrace();
		}

	}

}
