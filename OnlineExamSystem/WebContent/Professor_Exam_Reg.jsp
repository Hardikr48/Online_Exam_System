<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
$(document).ready(function() {
	var count = 0;
	var maxfield = 3;
	$(document).on('click','.add',function() {
		if (count < maxfield) {
			count++;
			var html = '';
			html += '<tr>';
			html += '<td> <input type="text" name="phase[]" class="form-control"  id="phaseid'+count+'" value="Phase'+count+'" readonly></td>';
			html += '<td> <input type="text" name="examtopic[]" class="form-control"  id="phaseid'+count+'" placeholder="Exam Topic"></td>';
			html += '<td> <input type="number" name="time[]" class="form-control"  id="timeid'+count+'"></td>';
			html += '<td> <input type="number" name="marks[]" class="form-control"  id="marks'+count+'"></td>';
			html += '<td><button type="button" name="remove" class="btn btn-danger btn-xs remove"><span class="glyphicon glyphicon-minus"></span></button></td>';
			$('tbody').append(html);

		} else {
			alert("Field limit reached")
			}
		})
		
		$(document).on('click', '.remove', function() {
		count--;
		$(this).closest('tr').remove();
	});                        
	$('#deaprtmentid').change(function(event) {  
        var username=$('#deaprtmentid').val();
     $.get('Department1',{q:username},function(response) {
    	 var obj = JSON.parse(response);
    	 var select = $('#semesterid');
    	 var selectsubject = $('#subjctid');
    	 selectsubject.find('option').remove();
    	 select.find('option').remove();
    	 $('<option>').val("").text("Select Semester").appendTo(select); 
    	 $('<option>').val("").text("Select Subject").appendTo(selectsubject);  
		    $.each(obj, function(index, value) {
		   $('<option>').val(obj[index].id).text(obj[index].semester).appendTo(select);
		 });
     });   
  });
	
	$('#semesterid').change(function(event) {  
        var semesterid=$('#semesterid').val();
        $.post('Department1',{semesterid:semesterid,flag:"subject"},function(response) {
    	 var obj = JSON.parse(response);
    	 var select = $('#subjctid');
    	 select.find('option').remove();
    	 $('<option>').val("").text("Select Subject").appendTo(select);  
		    $.each(obj, function(index, value) {
		    	$('<option>').val(obj[index].id).text(obj[index].subject).appendTo(select);
		 	});
     	});   
 	 });
});
</script>
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
<c:forEach items="${sessionScope.professordata }" var="q" end="0">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid" style="position: relative;">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" style="color: black">Onlie Examination System</a>
                <span style="text-align: center;"> <a class="navbar-brand" 
                        style="margin-left: 264px;color: black; font-size: 25px;">Apollo Institute Or Engineering</a>
                </span>
                <span style="right:5%; position: absolute; padding-top:5px;" >
                    <div class="dropdown">                    	
                        <img src="img/professor.jpg" alt="Cinque Terre" width="40" height="40" 
                            style="border-radius: 50%;">&nbsp;<i class="fa fa-ellipsis-v" style="font-size:24px; position: absolute; top: 20%; color: black;"></i>
                        <div class="dropdown-content">
                            <a href="#">View Profile</a><br>
                            <a href="<%=request.getContextPath()%>/College?flag=editprofile&id=${q.id }">Edit Profile</a><br>
                            <a href="#">Log out</a>
                         
                        </div>
                    </div>
                </span>
            </div>
        </div>
    </nav>
</c:forEach> 
	<ul>
	  <c:forEach items="${sessionScope.professordata }" var="q">
		<li><a href="Professor_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=stuexaminsert&id=${q.collegeid.id }">Add Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=insert&id=${q.id }">Add questions</a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=searchcollegesubject&id=${q.id }">View Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=searchcollegestudent&id=${q.id }">View Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchcollegeexam&id=${q.id }">View Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchcollegeexam&id=${q.id }">View Questions</a></li>
		<li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
	<div style="margin-left: 15%; padding: 1px 16px; height: 1000px;">
		<div style="padding-top: 4%;">
			<%
				if (session.getAttribute("examAdd") != null) {
			%>
			<p style="color: red">Exam Add Successfully</p>
			<%
				session.removeAttribute("examAdd");
			} else if (session.getAttribute("cahck") != null) {
			%>
			<p style="color: red">Please select department , semester and subject </p>
			<%
				session.removeAttribute("cahck");
			}
			%>
			
			<h3>Exam Form</h3>
			<form method="post" action="<%=request.getContextPath()%>/Exam">
				<span>*</span>Department:-<br>
				<select name="departmentid" id="deaprtmentid" required>
					<option>Select Department</option>
					<c:forEach items="${sessionScope.departmentist }" var="q">
						<option value="${q.id }">${q.department }</option>
					</c:forEach>
				</select><br><br>
				
				<span>*</span>Semester:-<br>
				<select	name="semid" id="semesterid" required>
					<option>Select Semester</option>
				</select><br> <br> 
				
				<span>*</span>Subject:-<br>
				<select	name="subjectid" id="subjctid" required>
					<option>Select Subject</option>
				</select><br> <br>

				<div class="table-repsonsive">
					<span id="error"></span>
					<table class="table table-bordered" id="item_table">
						<thead>
							<tr>
								<th>Phase</th>
								<th>Topic</th>
								<th>Time(In mintues)</th>
								<th>Marks</th>
								<th><button type="button" name="add"class="btn btn-success btn-xs add">
										<span class="glyphicon glyphicon-plus"></span>
									</button>
								</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				<input type="hidden" name="flag" value="professorinsert">
	            <input type="submit" name="submit">	
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