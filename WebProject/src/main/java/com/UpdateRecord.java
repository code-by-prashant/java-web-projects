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

public class UpdateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateRecord() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int salary = Integer.parseInt(request.getParameter("salary"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root",
					"admin");
			System.out.println("Connection established successfully!");

			PreparedStatement pstmt = connection.prepareStatement("UPDATE Employee SET Salary = ? WHERE Id = ?");

			pstmt.setInt(2, id);
			pstmt.setInt(1, salary);

			pstmt.execute();
			pstmt.close();

			PrintWriter out = response.getWriter();
			out.print("<h1>");
			out.println("Data updated successfully!");
			out.print(id);

			out.print("<a href=\"Index.html\">HOME PAGE</a>");

		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Failed to update record in the database!");
			e.printStackTrace();
		}
	}

}
