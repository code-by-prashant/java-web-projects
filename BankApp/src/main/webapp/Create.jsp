<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<%
	
	int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");
	long phoneNum = Long.parseLong(request.getParameter("phno"));
	long accNum = Long.parseLong(request.getParameter("acno"));
	String ifscCode = request.getParameter("ifsc");
	String panNum = request.getParameter("pan");
	long aadharNum = Long.parseLong(request.getParameter("aadhar"));
	int balance = Integer.parseInt(request.getParameter("balance"));

	try {

		ServletContext context = getServletContext();

		String drivername = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");

		Class.forName(drivername);

		Connection connection = DriverManager.getConnection(url, username, password);

		out.println("Connection established successfully!");

		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CUSTOMER VALUES (?,?,?,?,?,?,?,?)");

		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		pstmt.setLong(3, phoneNum);
		pstmt.setLong(4, accNum);
		pstmt.setString(5, ifscCode);
		pstmt.setString(6, panNum);
		pstmt.setLong(7, aadharNum);
		pstmt.setInt(8, balance);

		pstmt.execute();
		pstmt.close();

		out.print("<h1>");
		out.println("Account created successfully!");
		out.print(id);
		out.print(name);
		out.print(phoneNum);
		out.print(accNum);
		out.print(ifscCode);
		out.print(panNum);
		out.print(aadharNum);
		out.print(balance);

		/* out.print("<a href=\"Index.html\">HOME PAGE</a>"); */

	} catch (ClassNotFoundException e) {
		out.println("JDBC driver not found!");
		e.printStackTrace();
	} catch (SQLException e) {
		out.println("Connection failed!");
		e.printStackTrace();
	}
	
	%>
</body>
</html>