<%@page import="VO.DepartmentVo"%>
<%@page import="VO.SemVo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script>


$(document).ready(function(){
	var count = 0;
	var maxfield = 5;
	$(document).on('click', '.add', function(){
	  	if(count<maxfield){
			count++;
		   	var html = '';
		   	html += '<tr>';
		   	html += '<td><select name="departmentlist[]" class="form-control department"  data-semester_id="'+count+'" id="departmentid'+count+'"><option value="">Select Department</option></select></td>';
		   	html += '<td><select name="semesterlist[]" class="form-control semestr" data-subject_id="'+count+'" id="semesterid'+count+'"><option value="">Select Semester</option></select></td>';
		   	html += '<td><select name="subjectlist[]" class="form-control subject" data-subject_id="'+count+'" id="subjectid'+count+'"><option value="">Select Subject</option></select></td>';
		   	html += '<td><button type="button" name="remove" class="btn btn-danger btn-xs remove"><span class="glyphicon glyphicon-minus"></span></button></td>';
		   	$('tbody').append(html);
		   	var username="deaprtment";
		   	$.post('Department1',{flag:username},function(response) {
		   		var obj = JSON.parse(response);
	    		var select = $('#departmentid'+count);
		    	$.each(obj, function(index, value) {
		 		   	$('<option>').val(obj[index].id).text(obj[index].department).appendTo(select);
		 		 });
		 	});
	  	}else{
	  		alert("maximum 5 files add")
	  	}
	});
  	$(document).on('click', '.remove', function(){
  		count--;
    	$(this).closest('tr').remove();
  	});
  	$(document).on('change', '.department', function(){
    	var departmetid = $(this).val();
    	var semester = $(this).data('semester_id');
    	$.post('Department1',{department:departmetid,flag:"semester"},function(response) {
    		var obj = JSON.parse(response);
     		var select = $('#semesterid'+semester);
     		var select1 = $('#subjectid'+semester);
     		select.find('option').remove();
     		select1.find('option').remove();
     		$('<option>').val("").text("Select Subject").appendTo(select1);
     		$('<option>').val("").text("Select Semester").appendTo(select);
     	 	$.each(obj, function(index, value) {
  		   		$('<option>').val(obj[index].id).text(obj[index].semester).appendTo(select);
  		 	});
  		});
  	});
  	$(document).on('change', '.semestr','.department', function(){
      	var semesterid = $(this).val();
      	var subject = $(this).data('subject_id');
      	$.post('Department1',{semesterid:semesterid,flag:"subject"},function(response) {
      		var obj = JSON.parse(response);
       		var select = $('#subjectid'+subject);
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
			} 
			%>
			<h3>Add Professor</h3>
			<form method="post" action="<%=request.getContextPath()%>/Professor" enctype="multipart/form-data">
				<span>*</span> First_Name: 
				<input type="text" name="firstName"required><br><br> 
				<span>*</span>Last_Name: 
				<input type="text" name="lastName" required><br> <br> <span>*</span>Contact_No:
				<input type="text" name="Con_no" required><br> <br>
				
				<div class="table-repsonsive">
		          <span id="error"></span>
		          <table class="table table-bordered" id="item_table">
		            <thead>
		              <tr>
		                <th>Department</th>
		                <th>Semester</th>
		                <th>Subject</th>
		                <th><button type="button" name="add" class="btn btn-success btn-xs add"><span class="glyphicon glyphicon-plus"></span></button></th>
		              </tr>
		            </thead>
		            <tbody></tbody>
		          </table>
		          </div>
				<input type="hidden" name="roll" value="Professor">
				<span>*</span>Address:
				<textarea rows="2" cols="10" name="address" style="margin: 0px; width: 192px; height: 27px;"required></textarea>
				<br> <br> <span>*</span>Gender:<br> Male: <input
					type="radio" name="gender" value="male" required><br>
				Female: <input type="radio" name="gender" value="female" required><br>
				<br>
				<span>*</span>Salary: <input type="text" name="salary"required><br><br> 
				<span>*</span>Email: 
				<input type="email" name="email" required><br><br> 
				<span>*</span>Password:
				<input type="password" name="pass" required><br><br>
				<input type="hidden" name="flag" value="insert">
				<input type="file" name="myfile" accept="image/*"> <br /><br> 
	            <input type="submit" name="submit" class="btn btn-info" value="Insert" />
			</form>
		</div>
	</div>
</body>
</html>