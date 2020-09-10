<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	margin-top: 50px;
	width: 15%;
	background-color: lightgray;
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

li a:hover:not (.active ) {
	background-color: #555;
	color: white;
	text-decoration: none;
}

.navbar-inverse {
	background-color: rgb(136, 127, 127);
	border-color: #080808;
	color: black;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	padding: 12px 16px;
	z-index: 1;
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>
</head>

<body>
	<c:forEach items="${sessionScope.collegedata }" var="q" end="0">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid" style="position: relative;">
				<div class="navbar-header">
					<a class="navbar-brand" href="#" style="color: black">Online
						Examination System</a> <span style="text-align: center;"> <a
						class="navbar-brand"
						style="margin-left: 264px; color: black; font-size: 25px;">Apollo
							Institute Of Engineering</a>
					</span> <span style="right: 5%; position: absolute; padding-top: 5px;">
						<div class="dropdown">
							<img src="img/remco.jpg" alt="Cinque Terre" width="40"
								height="40" style="border-radius: 50%;">&nbsp;<i
								class="fa fa-ellipsis-v"
								style="font-size: 24px; position: absolute; top: 20%; color: black;"></i>
							<div class="dropdown-content">
								<a href="#">View Profile</a><br> <a
									href="<%=request.getContextPath()%>/College?flag=editprofile&id=${q.id }">Edit
									Profile</a><br> <a href="#">Log out</a>

							</div>
						</div>
					</span>
				</div>
			</div>
		</nav>
	</c:forEach>
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

<div style="margin-left:15%;padding:1px 16px;height:1000px;">
	<div style="padding-top:3%;" >
		<h3>View Department</h3>
		<%int i=1; %>
		<a class="previous"
			href="CollegeDepartmentProfessorList.jsp">&laquo;
			Previous</a>
		<table border="1">
			<tr>
				<td>No</td>
				<td>Professor Name</td>
				<td>Subject</td>
				<td>Semester</td>
				<td>Department</td>
				<td>Delete</td>
				
			</tr>
				<c:forEach items="${sessionScope.collegeProfessorDepartmentSubjectList }" var="q">
					<tr>
						<td><%=i %></td>
						<td>${q.professorid.firstName }</td>
						<td>${q.subjectid.subject }</td>
						<td>${q.semprofessorid.semid.semname }</td>
						<td>${q.departmentprofessorid.departmentid.department }</td>
						<td><a href="<%=request.getContextPath()%>/Professor?flag=deleteProfessorSubject&id=${q.id }">Delete</a></td>
					</tr>
					<%i++; %>
				</c:forEach>
		</table>
	</div>
</div>
<footer
        style="background-color:rgb(136, 127, 127); color: black; position: fixed;bottom: 0%;width: 100%; text-align: center;">
        <div class=" container">
            <p>© Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved </p>
        </div>
    </footer>
</body>
</html>