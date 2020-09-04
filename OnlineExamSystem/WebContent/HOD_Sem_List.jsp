<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

/* Firefox */
input[type=number] {
  -moz-appearance: textfield;
}
a {
	text-decoration: none;
	display: inline-block;
	padding: 8px 16px;
}

a:hover {
	background-color: #555;
	color: white;
}
</style>
</head>
<body>
<body>


<div>
	<ul>
	  <c:forEach items="${sessionScope.professordata }" var="q">
		<li><a href="Professor_HOD_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=hodinsert&id=${q.collegeid.id }">Add Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=hodinsert&id=${q.collegeid.id }">Add Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=hodinsert&id=${q.collegeid.id }">Add Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Sem?flag=hodviewsemlist&id=${q.id }">View SemList </a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=hodviewsubject&id=${q.id }">View Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Professor?flag2=college&flag=searchcollegeprofessor&id=${q.id }">View Professor</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=searchcollegestudent&id=${q.id }">View Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchcollegeexam&id=${q.id }">View Exam</a></li>
		<li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<div style="padding-top:2%;" >
		<h3>View Semester</h3>
		<%int i=1; %>
		<table border="1" style="text-align: center;">
			<tr>
				<td>No</td>
				<td>Semester</td>
				<td>View Department</td>
				<td>View Subject</td>
				<td>View Professor</td>
				<td>View Student</td>
				<td>Exam</td>
				<td>Delete</td>
			</tr>
			
			<c:forEach items="${sessionScope.hodsemlist }" var="q">
				<tr>
				<td><%=i %></td>
				<td>${q.semname }</td>
				<td>${q.departmentid.department }</td>
				<td><a href="<%=request.getContextPath()%>/Subject?flag=viewcollegesemestersubject&id=${q.id }">View Subject</a></td>
				<td><a href="<%=request.getContextPath()%>/Professor?flag=viewcollegesemesterprofessor&id=${q.id }">View Professor</a></td>
				<td><a href="<%=request.getContextPath()%>/Student?flag=viewcollegesemesterstudent&id=${q.id }">View Student</a></td>
				<td><a href="<%=request.getContextPath()%>/Exam?flag=viewcollegesemesterexam&id=${q.id }">View Exam</a></td>
				<td><a href="<%=request.getContextPath()%>/Sem?flag=deletesem&id=${q.id }">Delete</a></td>
				</tr>
				<%i++; %>
			</c:forEach>
			
		</table>
	</div>
</div>
</body>
</html>