<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

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
});
</script>
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

	<div style="margin-left: 15%; padding: 1px 16px; height: 1000px;">
		<div style="padding-top: 3%;">
			<%
				if (session.getAttribute("studentadd") != null) {
			%>
			<p style="color: red">Add Successfully <%out.print(session.getAttribute("rollno")); %></p>
			<%
				session.removeAttribute("studentadd");
			} else if (session.getAttribute("emailidadd") != null) {
			%>
			<p style="color: red">Email id already exist</p>
			<%
				session.removeAttribute("emailidadd");
			} else if (session.getAttribute("cahck") != null) {
			%>
			<p style="color: red">Please select department and semester</p>
			<%
				session.removeAttribute("cahck");
			}
			%>
			<h3>Add Student</h3>
			<form method="post" action="<%=request.getContextPath()%>/Student" enctype="multipart/form-data">
				<span>*</span> First_Name: 
				<input type="text" name="firstName"required><br><br> 
				
				<span>*</span>Last_Name: 
				<input type="text" name="lastName" required><br> <br> 
				
				<span>*</span>Contact_No:
  				<input type="tel" id="phone" name="Con_no" placeholder="123-456-7890"pattern="[0-9]{3}[0-9]{3}[0-9]{4}"><br><br>
  				
				<span>*</span>Department:-<br>
				<select name="departmentid" id="deaprtmentid" required>
					<option>Select</option>
					<c:forEach items="${sessionScope.departmentist }" var="q">
						<option value="${q.id }">${q.department }</option>
						
					</c:forEach>
				</select><br><br>
				
				<span>*</span>Semester:-<br>
				<select	name="semid" id="semesterid" required>
					<option value="">Select Semester</option>
				</select><br> <br> 
				
				<span>*</span>Address:
				<textarea rows="2" cols="10" name="address" style="margin: 0px; width: 192px; height: 27px;"required></textarea>
				
				<br> <br> <span>*</span>Gender:<br>
				Male: <input type="radio" name="gender" value="male" required><br>
				Female: <input type="radio" name="gender" value="female" required><br><br>
				
				<span>*</span>Email: 
				<input type="email" name="email" required><br><br> 
				
				<span>*</span>Password:
				<input type="password" name="pass" required><br><br>
				
				
				<c:forEach items="${sessionScope.departmentist }" var="q">
					<input type="hidden" name="departmentcode" value="${q.departmentcode }">
				</c:forEach>
				<input type="file" name="myfile" accept="image/*"><br><br>
	            <input type="hidden" name="flag" value="insert">
	            <input type="submit" name="submit">
			</form>
		</div>
	</div>
</body>
<footer
        style="background-color:rgb(136, 127, 127); color: black; position: fixed;bottom: 0%;width: 100%; text-align: center;">
        <div class=" container">
            <p>© Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved </p>
        </div>
    </footer>
</html>