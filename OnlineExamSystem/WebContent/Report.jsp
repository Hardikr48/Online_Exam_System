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
	<div style="margin-left:15%;padding:1px 16px;height:1000px;">
	<div style="padding-top:3%;" >
		<h3>View Report</h3>
		<%int i=1; %>
		<table border="1">
			<tr>
				<td>No</td>
				<td>Subject</td>
				<td>Topic</td>
				<td>Result</td>
				<td>Mark obtained</td>
				<td>Total MArk</td>
				<td>Time</td>
				<td>Correct</td>
				<td>Wrong</td>
				<td>Mark & Review </td>
				<td>Not Attended</td>
				<td>Mcq</td>
				<td>Date</td>				
			</tr>
				<c:forEach items="${sessionScope.studentresult }" var="q">
					<tr>
						<td><%=i %></td>
						<td>${q.subjectid.subject }</td>
						<td>${q.topic }</td>
						<td>${q.result }</td>
						<td>${q.markobtained }&#37;</td>
						<td>${q.totalmark }</td>
						<td>${q.time }</td>
						<td>${q.correct }</td>
						<td>${q.wrong }</td>
						<td>${q.markreview }</td>
						<td>${q.notattended }</td>
						<td>${q.totalmcq }</td>
						<td>${q.date }</td>
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