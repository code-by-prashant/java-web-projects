<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EMI Calculator</title>
</head>
<body>
<div class="container">
		<h1>Welcome to EMI Calculator</h1>
		<form method="post">
		<table>
			<tr>
				<td><label for="principal">Principal Amount:</label></td>
				<td><input type="number" id="principal" name="principal" required></td>
			</tr>
			<tr>
				<td><label for="interest">Rate of Interest:</label></td>
				<td><input type="number" id="interest" name="interest" required></td>
			</tr>
			<tr>
				<td><label for="time">Time (in months):</label></td>
				<td><input type="number" id="time" name="time"></td>
			</tr>
		</table>
			<input type="submit" name="submit" value="Calculate EMI">
		</form>
	</div>
<%
    // Java code for calculating EMI
    if (request.getParameter("submit") != null) {
        double principal = Double.parseDouble(request.getParameter("principal"));
        double interestRate = Double.parseDouble(request.getParameter("interest"));
        int time = Integer.parseInt(request.getParameter("time"));

        double monthlyInterest = interestRate / 12 / 100;
        double numerator = principal * monthlyInterest * Math.pow(1 + monthlyInterest, time);
        double denominator = Math.pow(1 + monthlyInterest, time) - 1;

        double emi = numerator / denominator;

        // Display the calculated EMI
        %>
        <h2>EMI Calculation Result:</h2>
        <p>Principal Amount: <%= principal %></p>
        <p>Rate of Interest: <%= interestRate %></p>
        <p>Time (in months): <%= time %></p>
        <h3>EMI: <%= emi %></h3>
        <%
    }
%>
</body>
</html>