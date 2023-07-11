<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw Money</title>
</head>
<body>
	<%
	long accountNumber = Long.parseLong(request.getParameter("acno"));
	long phoneNumber = Long.parseLong(request.getParameter("phno"));
	String panNumber = request.getParameter("pan");
	int amount = Integer.parseInt(request.getParameter("amount"));

	try {

		ServletContext context = getServletContext();

		String drivername = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");

		Class.forName(drivername);

		Connection connection = DriverManager.getConnection(url, username, password);

		out.println("Connection established successfully!");
		
		// Start a transaction
	    connection.setAutoCommit(false);


		// Check if the customer exists and get their current balance
		String selectSql = "SELECT Balance FROM Customer WHERE AccountNumber=? AND PhoneNumber=? AND PANNumber=?";
		PreparedStatement selectStatement = connection.prepareStatement(selectSql);
		selectStatement.setLong(1, accountNumber);
		selectStatement.setLong(2, phoneNumber);
		selectStatement.setString(3, panNumber);
		
		ResultSet resultSet = selectStatement.executeQuery();

		if (resultSet.next()) {
			int currentBalance = resultSet.getInt("Balance");
			int newBalance = currentBalance - amount;

			// Update the customer's balance
			String updateSql = "UPDATE Customer SET Balance=? WHERE AccountNumber=? AND PhoneNumber=? AND PANNumber=?";
			PreparedStatement updateStatement = connection.prepareStatement(updateSql);
			updateStatement.setInt(1, newBalance);
			updateStatement.setLong(2, accountNumber);
			updateStatement.setLong(3, phoneNumber);
			updateStatement.setString(4, panNumber);
			updateStatement.executeUpdate();

			// Insert the deposit record into the Deposit table
			String depositSql = "INSERT INTO Withdraw (AccountNumber, PhoneNumber, PANNumber, Amount) VALUES (?, ?, ?, ?)";
			PreparedStatement depositStatement = connection.prepareStatement(depositSql);
			depositStatement.setLong(1, accountNumber);
			depositStatement.setLong(2, phoneNumber);
			depositStatement.setString(3, panNumber);
			depositStatement.setInt(4, amount);
			depositStatement.executeUpdate();

			// Commit the transaction
			connection.commit();

			// Redirect to a success page
		} else {
			// Customer not found
			// Rollback the transaction
			connection.rollback();

			// Redirect to an error page or display an error message
		}

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