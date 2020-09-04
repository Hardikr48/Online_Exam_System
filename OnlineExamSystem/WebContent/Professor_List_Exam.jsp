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
		<li><a href="Professor_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=stuexaminsert&id=${q.collegeid.id }">Add Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=insert&id=${q.id }">Add questions</a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=searchcollegesubject&id=${q.id }">View Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=searchcollegestudent&id=${q.id }">View Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchprofessordexam&id=${q.id }">View Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchcollegeexam&id=${q.id }">View Questions</a></li>
		<li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<div style="padding-top:2%;" >
		<h3>View Exam</h3>
		<%
			if (session.getAttribute("examdelete") != null) {
		%>
		<p style="color: red">Delete Successfully</p>
		<%
			session.removeAttribute("examdelete");
		}
		%><%int i=1; %>
		<table border="1" >
			<tr style="text-align: center;">
				<td>No</td>
				<td>Department</td>
				<td>Semester</td>
				<td>Subject</td>
				<td>Phase</td>
				<td>Topic</td>
				<td>Marks</td>
				<td>Time</td>
				<td>Date</td>
				<td>Edit</td>
				<td>Add Question</td>
			</tr>
			
			<c:forEach items="${sessionScope.examlist }" var="q">
				<tr style="text-align: center;">
					<td><%=i %></td>
					<td>${q.departmentid.department }</td>
					<td>${q.semesterid.semname }</td>
					<td>${q.subjectid.subject }</td>
					<td>${q.phase }</td>
					<td>${q.examtopic }</td>
					<td>${q.time }</td>
					<td>${q.marks }</td>
					<td>${q.examid.date }</td>
					<td><a href="<%=request.getContextPath()%>/Exam?flag=editexam&id=${q.id }">Edit</a></td>
					<td><a href="<%=request.getContextPath()%>/Question?flag=professoraddque&id=${q.id }">Add Question</a></td>
				</tr>
				<%i++; %>
			</c:forEach>
		</table>
	</div>
</div>

</body>
</html>