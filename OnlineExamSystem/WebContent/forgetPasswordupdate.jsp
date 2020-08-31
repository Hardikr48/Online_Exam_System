<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update password</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<%
		if (session.getAttribute("error") != null) {
	%>
	<p style="color: red">Password Mismatch</p>
	<%
		session.removeAttribute("error");
	} else if (session.getAttribute("wrong") != null) {
	%>
	<p style="color: red">Email is invalid</p>
	<%
		session.removeAttribute("wrong");
	}
	%>


	<form action="<%=request.getContextPath()%>/Login" method="post">
		<input type="password" name="password" placeholder="password" required>
		<input type="password" name="cpassword" placeholder="confirm password"
			required> <input type="hidden" name="flag"
			value="updatepassword"> <input type="submit" value="Submit"
			name="submit">
	</form>
	<a href="Com_Login.jsp"> Login </a>

</body>
</html>