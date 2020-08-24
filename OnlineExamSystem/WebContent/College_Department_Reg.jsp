<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>Insert title here</title>
</head>
<body>

<div>
	<ul>
	 <c:forEach items="${sessionScope.collegedata }" var="q">
		<li><a href="College_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Sem?flag=insert&id=${q.id }">Add Sem </a></li>
		<li><a href="<%=request.getContextPath()%>/Department?flag=insert&id=${q.id }">Add Department</a></li>
		<li><a href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">Add Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">Add Professor</a></li>
		<li><a href="<%=request.getContextPath()%>/Department?flag=departmentsearch&id=${q.id }">Add Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Employee?flag=companysearch&flag2=company&id=${q.id }">Add Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Sem?flag=viewsemlist&id=${q.id }">View SemList </a></li>
		<li><a href="<%=request.getContextPath()%>/Department?flag=viewdepartmentlist&id=${q.id }">View Department</a></li>
		<li><a href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">View Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">View Professor</a></li>
		<li><a href="<%=request.getContextPath()%>/Department?flag=departmentsearch&id=${q.id }">View Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Employee?flag=companysearch&flag2=company&id=${q.id }">View Exam</a></li>
		<li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<div style="padding-top:2%;" >
		<% 
	    if(session.getAttribute("adddepartment") != null ){
		%>
		 <p style="color:red"> Add Successfully </p>
		<%session.removeAttribute("adddepartment");
		}else if(session.getAttribute("erorr") != null ){
		%>
		 <p style="color:red"> Department already exists </p>
		<%session.removeAttribute("erorr");
		}else if(session.getAttribute("selectsem") != null ){
		%>
		 <p style="color:red"> Please select sem </p>
		<%session.removeAttribute("selectsem");
		}
		%>
		<h3>Add Sem</h3>
		<form action="<%=request.getContextPath()%>/Department" method="post">
		<span>*</span>Sem:-<br>
		<select name="semid" required>
			<option>Select</option>
			<c:forEach items="${sessionScope.semlist }" var="q">
				<option value="${q.id }">${q.semname }</option>
			</c:forEach>
		</select><br><br>
		<span>*</span>Department Name:-
		<input type="text" name="departmentname"  required ><br><br>
		<input type="hidden" name="flag" value="insert"/>
		<input type="submit" value="SUBMIT"/>
		</form>
	</div>
</div>
</body>
</html>