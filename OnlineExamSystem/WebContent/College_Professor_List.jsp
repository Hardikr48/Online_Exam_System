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
	<%String s1 = (String) session.getAttribute("flag2"); %>
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
		<h3>View Department</h3>
		<table border="1">
			<tr>
				<td>Professor Name</td>
				<td>Last Name</td>
				<td>Email</td>
				<td>ContactNo</td>
				<td>Address</td>
				<td>Gender</td>
				<td>Salary</td>
				<td>Designation</td>
				<td>JoiningDate</td>
				<% if (s1.equalsIgnoreCase("college")) {%>
					<td>Department</td>
					<td>Semester</td>
					<td>Subject</td>
					<td>Image</td>
				<% }else if(s1.equalsIgnoreCase("department")){%>
					<td>Department</td>
					<td>Semester</td>
					<td>Subject</td>
				<%}else if(s1.equalsIgnoreCase("semester")){%>
					<td>Subject</td>
				<%} %>
				
			</tr>
			
				
					
					<% if (s1.equalsIgnoreCase("college")) {%>
					<tr>
					<c:forEach items="${sessionScope.colprofessorlist }" var="q">
					
						<td>${q.firstName }</td>
						<td>${q.lastName }</td>
						<td>${q.email }</td>
						<td>${q.con_no }</td>
						<td>${q.address }</td>
						<td>${q.gender }</td>
						<td>${q.salary }</td>
						<td>${q.roll }</td>
						<td>${q.joiningdate }</td>
						</c:forEach>
						<c:forEach items="${sessionScope.departmentprofessorlist }" var="b">
							<td>${b.departmentid.department }</td>
						</c:forEach>
						
						<c:forEach items="${sessionScope.semprofessorlist }" var="a">
							<td>${a.semid.semname }</td>
						</c:forEach>
						
						<c:forEach items="${sessionScope.subjectrofessorlist }" var="b">
							<td>${b.subjectid.subject }</td>
						</c:forEach>
						
						<c:forEach items="${sessionScope.colprofessorlist }" var="q">
						<td><a href="<%=request.getContextPath()%>/PhotoUpload?flag=editprofile&id=${q.id }">Insert Image</a></td>
						
						</c:forEach>
					</tr>	
					<% }else if(s1.equalsIgnoreCase("department")){%>
					<tr>
					<c:forEach items="${sessionScope.depprofessorlist }" var="q">
						<td>${q.professorid.firstName }</td>
						<td>${q.professorid.lastName }</td>
						<td>${q.professorid.email }</td>
						<td>${q.professorid.con_no }</td>
						<td>${q.professorid.address }</td>
						<td>${q.professorid.gender }</td>
						<td>${q.professorid.salary }</td>
						<td>${q.professorid.roll }</td>
						<td>${q.professorid.joiningdate }</td>
						<td>${q.departmentid.department }</td>
						</c:forEach>
				
						<c:forEach items="${sessionScope.semprofessorlist }" var="a">
							<td>${a.semid.semname }</td>
						</c:forEach>
					
						<c:forEach items="${sessionScope.subjectrofessorlist }" var="b">
							<td>${b.subjectid.subject }</td>
						</c:forEach>
					</tr>
					<%} else if(s1.equalsIgnoreCase("semester")){%>
					<tr>
					<c:forEach items="${sessionScope.semprofessorlist }" var="q">
					
						<td>${q.professorid.firstName }</td>
						<td>${q.professorid.lastName }</td>
						<td>${q.professorid.email }</td>
						<td>${q.professorid.con_no }</td>
						<td>${q.professorid.address }</td>
						<td>${q.professorid.gender }</td>
						<td>${q.professorid.salary }</td>
						<td>${q.professorid.roll }</td>
						<td>${q.professorid.joiningdate }</td>
					</c:forEach>
						
						<c:forEach items="${sessionScope.subjectrofessorlist }" var="b">
							<td>${b.subjectid.subject }</td>
						</c:forEach>
					</tr>
					<%} else if(s1.equalsIgnoreCase("subject")){%>
					<tr>
					<c:forEach items="${sessionScope.subprofessorlist }" var="q">
						
						<td>${q.professorid.firstName }</td>
						<td>${q.professorid.lastName }</td>
						<td>${q.professorid.email }</td>
						<td>${q.professorid.con_no }</td>
						<td>${q.professorid.address }</td>
						<td>${q.professorid.gender }</td>
						<td>${q.professorid.salary }</td>
						<td>${q.professorid.roll }</td>
						<td>${q.professorid.joiningdate }</td>
					
					</c:forEach>
					</tr>	
					
				<%} %>
		</table>
	</div>
</div>
</body>
</html>