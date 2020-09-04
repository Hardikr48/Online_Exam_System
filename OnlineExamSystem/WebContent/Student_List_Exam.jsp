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
	padding: 2px 16px;
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
	  <c:forEach items="${sessionScope.studentdata }" var="q">
		<li><a href="Professor_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=studentexamlist&id=${q.id }">View Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchcollegeexam&id=${q.id }">View Report</a></li>
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
				
				<td>Subject</td>
				<td>Phase</td>
				<td>Topic</td>
				<td>Marks</td>
				<td>Time</td>
				<td>Date</td>
				<td>Exam Start</td>
			</tr>
			
			<c:forEach items="${sessionScope.examlist }" var="q">
					<c:choose>
						<c:when test="${q.phase eq 'Phase1'}">
							<tr style="text-align: center;">
							<td><%=i %></td>
							<td>${q.subjectid.subject }</td>
							<td>${q.phase }</td>
							<td>${q.examtopic }</td>
							<td>${q.time }</td>
							<td>${q.marks }</td>
							<td>${q.examid.date }</td>
							<td><a href="<%=request.getContextPath()%>/Exam?flag=examrules&id=${q.id }">Exam Start</a></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr style="text-align: center;">
							<td><%=i %></td>
							<td>${q.subjectid.subject }</td>
							<td>${q.phase }</td>
							<td>${q.examtopic }</td>
							<td>${q.time }</td>
							<td>${q.marks }</td>
							<td>${q.examid.date }</td>
							</tr>
						</c:otherwise>
					</c:choose>
				
				<%i++; %>
			</c:forEach>
		</table>
	</div>
</div>

</body>
</html>