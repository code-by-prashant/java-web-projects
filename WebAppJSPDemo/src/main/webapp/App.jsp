<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP Page</title>
</head>
<body>

	<%-- 
	<%! %> = declaration tag (used to declare variables and methods)
	<% %> = scriptlet tag (used to include Java code within the JSP page)
	<%=%> = expression tag (used to evaluate an expression and display its 
			result in the JSP page) 
	--%>

<%

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

	

	Class.forName("com.mysql.cj.jdbc.Driver");

	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company_schema", "root",
			"admin");
    
	out.println("Connection established successfully!");

	PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?)");

	pstmt.setInt(1, id);
	pstmt.setString(2, name);
	pstmt.setInt(3, age);
	pstmt.setFloat(4, salary);
	pstmt.setString(5, designation);

	pstmt.execute();
	pstmt.close();

	out.print("<h1>");
	out.println("Data inserted successfully!");
	out.print(id);
	out.print(name);
	out.print(age);
	out.print(salary);
	out.print(designation);

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