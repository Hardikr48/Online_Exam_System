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
</style>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function() {                        
	$('#deaprtmentid').change(function(event) {  
        var username=$('#deaprtmentid').val();
     $.get('Department1',{q:username},function(response) {
    	 var obj = JSON.parse(response);
    	 var select = $('#semesterid');
    	 select.find('option').remove();
    	 $('<option>').val("").text("Select Semester").appendTo(select);   
    	 $.each(obj, function(index, value) {
		   	$('<option>').val(obj[index].id).text(obj[index].semester).appendTo(select);
		 });
     });   
  });
	
	$('#semesterid').change(function(event) {  
        var semesterid=$('#semesterid').val();
        $.post('Department1',{semesterid:semesterid,flag:"subject"},function(response) {
    	 var obj = JSON.parse(response);
    	 var select = $('#subjectid');
    	 select.find('option').remove();
    	 $('<option>').val("").text("Select Subject").appendTo(select);  
		    $.each(obj, function(index, value) {
		    	$('<option>').val(obj[index].id).text(obj[index].subject).appendTo(select);
		 	});
     	});   
  	});
});
</script>
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
				if (session.getAttribute("professoradd") != null) {
			%>
			<p style="color: red">Add Successfully</p>
			<%
				session.removeAttribute("professoradd");
			} else if (session.getAttribute("selecterorr") != null) {
			%>
			<p style="color: red">Please select Sem or Department or subject</p>
			<%
				session.removeAttribute("selecterorr");
			} else if (session.getAttribute("emailwrong") != null) {
			%>
			<p style="color: red">Please valid email id</p>
			<%
				session.removeAttribute("emailwrong");
			}else if (session.getAttribute("emailidadd") != null) {
			%>
			<p style="color: red">Email ID already exist</p>
			<%
				session.removeAttribute("emailidadd");
			}else if (session.getAttribute("hodexist") != null) {
			%>
			<p style="color: red">Head Of Department Already exist</p>
			<%
				session.removeAttribute("hodexist");
			} 
			%> 
			
			<h3>Add Head Of Department</h3>
			<form method="post" action="<%=request.getContextPath()%>/Professor">
				<span>*</span> First_Name: 
				<input type="text" name="firstName"required><br><br> 
				
				<span>*</span>Last_Name: 
				<input type="text" name="lastName" required><br> <br> 
				
				<span>*</span>Contact_No:
  				<input type="tel" id="phone" name="Con_no" placeholder="123-456-7890"pattern="[0-9]{3}[0-9]{3}[0-9]{4}"><br><br>
  				
				<span>*</span>Department:-<br>
				<select name="departmentlist[]" id="deaprtmentid" required>
					<option>Select</option>
					<c:forEach items="${sessionScope.departmentist }" var="q">
						<option value="${q.id }">${q.department }</option>
					</c:forEach>
				</select><br><br>
				
				<span>*</span>Semester:-<br>
				<select	name="semesterlist[]" id="semesterid" required>
					<option value="">Select Semester</option>
				</select><br> <br> 
				
				<span>*</span>Subject:-<br>
				<select	name="subjectlist[]" id="subjectid" required>
					<option value="">Select Semester</option>
				</select><br> <br> 
				<input type="hidden" name="roll" value="HOD">
				<span>*</span>Address:
				<textarea rows="2" cols="10" name="address" style="margin: 0px; width: 192px; height: 27px;"required></textarea>
				
				<br> <br> <span>*</span>Gender:<br>
				Male: <input type="radio" name="gender" value="male" required><br>
				Female: <input type="radio" name="gender" value="female" required><br><br>
				<span>*</span>Salary: <input type="text" name="salary"required><br><br> 
				<span>*</span>Email: 
				<input type="email" name="email" required><br><br> 
				
				<span>*</span>Password:
				<input type="password" name="pass" required><br><br>
				
				<input type="hidden" name="flag" value="insert">
	            <input type="submit" name="submit">
			</form>
		</div>
	</div>
</body>
</body>
</html>