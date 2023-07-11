package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Read extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Read() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root",
					"admin");
			System.out.println("Connection established successfully!");
			
			Statement st = con.createStatement();	
			
			ResultSet rs = st.executeQuery("select * from employee");
			
			while(rs.next()) {
				out.println(" " + rs.getInt(1) + " | " + rs.getString(2) 
				+ " 	| " + rs.getInt(3) + "	| "
				+ rs.getString(4) + "   | " + rs.getInt(5));
			}
			
		}catch(Exception e) {
			System.out.println("Connection Failed");
		}
	}

}
