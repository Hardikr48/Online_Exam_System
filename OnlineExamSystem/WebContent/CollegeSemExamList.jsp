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
		<a class="previous" href="College_Sem_List.jsp">&laquo; Previous</a>
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
				<td>Delete</td>
			</tr>
			
			<c:forEach items="${sessionScope.collegesubjectexamlist }" var="q">
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
					<td><a href="<%=request.getContextPath()%>/Exam?flag=examdelete&id=${q.id }">Delete</a></td>
				</tr>
				<%i++; %>
			</c:forEach>
		</table>
	</div>
</div>

</body>
</html>