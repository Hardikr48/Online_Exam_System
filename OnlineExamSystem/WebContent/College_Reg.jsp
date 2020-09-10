<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("collegeadd") != null) {
	%>
	<p style="color: red">Successfully Add</p>
	<%
		session.removeAttribute("collegeadd");
	} else if (session.getAttribute("collegeemailwrong") != null) {
	%>
	<p style="color: red">Email Id is wrong</p>
	<%
		session.removeAttribute("collegeemailwrong");
	} else if (session.getAttribute("emailidadd") != null) {
	%>
	<p style="color: red">Email id already exists</p>
	<%
		session.removeAttribute("emailidadd");
	}
	%>
	<div class="loginbox">
		<img src="img/expertlogo.png" class="user">
		<form action="College" method="post" enctype="multipart/form-data">
			<input type="text" name="collegename" placeholder="College name" required><br>
			<input type="text" name="collegeaddress" placeholder="Address"
				required><br> <input type="email" name="email"
				placeholder="College email" required> <br> <input
				type="password" name="pass" placeholder="Password" required><br>
				<input type="file" name="myimg"><br><br>
			<input type="hidden" name="flag" value="insert"> <input
				type="submit" value="Submit" name="submit">
		</form>
		<a href="Com_Login.jsp">Login</a>
	</div>
	<footer
        style="background-color:rgb(136, 127, 127); color: black; position: fixed;bottom: 0%;width: 100%; text-align: center;">
        <div class=" container">
            <p>© Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved </p>
        </div>
    </footer>
</body>
</html>