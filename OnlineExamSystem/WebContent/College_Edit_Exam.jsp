<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	margin: 0;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 25%;
	background-color: #f1f1f1;
	position: fixed;
	height: 100%;
	overflow: auto;
}

li a {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}

li a.active {
	background-color: #4CAF50;
	color: white;
}

li a:hover:not(.active) {
	background-color: #555;
	color: white;
}
</style>
</head>
<body>

<div>
	<ul>
	  <c:forEach items="${sessionScope.collegedata }" var="q">
		<li><a href="College_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Department?flag=insert&id=${q.id }">Add Department</a></li>
		<li><a href="<%=request.getContextPath()%>/Sem?flag=insert&id=${q.id }">Add Semester </a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=insert&id=${q.id }">Add Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Professor?flag2=professor&flag=insert&id=${q.id }">Add Professor</a></li>
		<li><a href="<%=request.getContextPath()%>/Professor?flag2=hod&flag=insert&id=${q.id }">Add Head Of Department</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=insert&id=${q.id }">Add Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=insert&id=${q.id }">Add Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Sem?flag=viewsemlist&id=${q.id }">View SemList </a></li>
		<li><a href="<%=request.getContextPath()%>/Department?flag=viewdepartmentlist&id=${q.id }">View Department</a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=searchcollegesubject&id=${q.id }">View Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Professor?flag=searchcollegeprofessor&id=${q.id }">View Professor</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=searchcollegestudent&id=${q.id }">View Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchcollegeexam&id=${q.id }">View Exam</a></li>
		<li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<div style="padding-top: 2%;">
			<%
				if (session.getAttribute("loginResult") != null) {
			%>
			<p style="color: red">Updated Successfully....</p>
			<%
				session.removeAttribute("loginResult");
			}
			if (session.getAttribute("not") != null) {
			%>
			<p style="color: red">Email id is not velid.</p>
			<%
				session.removeAttribute("not");
			} else if (session.getAttribute("wrong") != null) {
			%>
			<p style="color: red">Email is invalid</p>
			<%
				session.removeAttribute("wrong");
			}
			%>
			<h3>Edit Exam</h3>
			<form action="<%=request.getContextPath()%>/Exam" method="post">
			<%int i=0; %>
				<c:forEach items="${sessionScope.examlist }" var="q">
				<%=i++ %>
			Phase<%=i%>:-<input type="text" name="phase"
						value="${q.phase }"required>
					<br>
					<br>
			Time<%=i%>:-<input type="text" name="time"
						value="${q.time }"required>
					<br>	
					<br>
			Marks<%=i%>:-<input type="email" name="maeks" value="${q.marks }"required>
					<br>
					<br>
					<input type="hidden" name="id" value="${q.id}"required>
					<input type="hidden" name="departmentid" value="${q.departmentid.id }"required>
					<input type="hidden" name="semid" value="${q.semesterid.id }"required>
					<input type="hidden" name="semid" value="${q.subjectid.id }"required>
					<input type="hidden" name="collegeid" value="${q.collegeid.id }"required>
					<input type="hidden" name="flag" value="updateprofile">
				</c:forEach>
				<input type="submit">
			</form>
		</div>
	</div>
			
</body>
</html>