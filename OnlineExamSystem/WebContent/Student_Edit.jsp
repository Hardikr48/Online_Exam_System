<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            margin-top:50px;
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

        li a:hover:not(.active) {
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
<c:forEach items="${sessionScope.studentdata }" var="q" end="0">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid" style="position: relative;">
            <div class="navbar-header">
                <a class="navbar-brand"  style="color: black">Onlie Examination System</a>
                <span style="text-align: center;"> <a class="navbar-brand" 
                        style="margin-left: 264px;color: black; font-size: 25px;">Apollo Institute Or Engineering</a>
                </span>
                <span style="right:5%; position: absolute; padding-top:5px;" >
                    <div class="dropdown">                    	
                        <img src="img/student.jpg" alt="Cinque Terre" width="40" height="40" 
                            style="border-radius: 50%;">&nbsp;<i class="fa fa-ellipsis-v" style="font-size:24px; position: absolute; top: 20%; color: black;"></i>
                        <div class="dropdown-content">
                            <a href="#">View Profile</a><br>
                            <a href="<%=request.getContextPath()%>/Student?flag=editprofilestudent&id=${q.id }">Edit Profile</a><br>
                            <a href="#">Log out</a>
                        </div>
                    </div>
                </span>
            </div>
        </div>
    </nav>
</c:forEach> 
	<ul>
	  <c:forEach items="${sessionScope.studentdata }" var="q">
		<li><a href="Professor_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=studentexamlist&id=${q.id }">View Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Result?flag=studentid&id=${q.id }">View Report</a></li>
		<li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
	<div style="margin-left: 15%; padding: 1px 16px; height: 1000px;">
	<div style="padding-top: 3%;">
			<%
				if (session.getAttribute("studentadd") != null) {
			%>
			<p style="color: red">Add Successfully</p>
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
			<form method="post" action="<%=request.getContextPath()%>/Student">
				
				<c:forEach items="${sessionScope.studentlist }" var="q">
				
				<span>*</span>Email: 
				<input type="email" name="email" value="${q.email }" required><br><br> 
				
				<span>*</span>Password:
				<input type="text" name="pass" value="${q.password }" required><br><br>
				<input type="hidden" name="firstName" value="${q.firstName}">
				<input type="hidden" name="lastName" value="${q.lastName }" required> 
  				<input type="hidden" id="phone" name="Con_no" placeholder="123-456-7890"pattern="[0-9]{3}[0-9]{3}[0-9]{4}" value="${q.con_no }">
				<input type="hidden" name="roll" value="${q.roll }">
				<input type="hidden" name="departmentid" value="${q.departmentid.id }">
				<input type="hidden" name="semid" value="${q.semesterid.id }">
				<input type="hidden" name="address" value="${q.address }">
				<input type="hidden" name="gender" value="${q.gender }">
				<input type="hidden" name="collegeid" value="${q.collegeid.id }">
				<input type="hidden" name="id" value="${q.id }">
				<input type="hidden" name="joiningdate" value="${q.joiningdate }">
	            <input type="hidden" name="flag" value="updatestudent">
	            <input type="submit" name="submit">
	            </c:forEach>
			</form>
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