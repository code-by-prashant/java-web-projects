package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReadRecord() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root",
					"admin");
			System.out.println("Connection established successfully!");

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Employee");

			out.println("<h4>");
			while (rs.next()) {
				out.println(" " + rs.getInt(1) + " | " + rs.getString(2) 
								+ " 	| " + rs.getInt(3) + "	| "
								+ rs.getFloat(4) + "   | " + rs.getString(5));
				out.println("<br>");
			}

			rs.close();
			statement.close();
			
			out.println("");
            out.println("\nRecords displayed successfully!");
			out.print("<a href=\"Index.html\">HOME PAGE</a>");


		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver not found!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Failed to read records from the database!");
			e.printStackTrace();
		}
	}


}
