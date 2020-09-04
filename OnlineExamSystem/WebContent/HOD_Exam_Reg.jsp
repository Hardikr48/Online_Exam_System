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
</style>

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
</head>
<body>

<div>
	<ul>
	  <c:forEach items="${sessionScope.professordata }" var="q">
		<li><a href="Professor_HOD_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=hodinsert&id=${q.collegeid.id }">Add Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=hodinsert&id=${q.collegeid.id }">Add Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=hodinsert&id=${q.id }">Add Exam</a></li>
		<li><a href="<%=request.getContextPath()%>/Sem?flag=viewsemlist&id=${q.id }">View SemList </a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=searchcollegesubject&id=${q.id }">View Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Professor?flag2=college&flag=searchcollegeprofessor&id=${q.id }">View Professor</a></li>
		<li><a href="<%=request.getContextPath()%>/Student?flag=searchcollegestudent&id=${q.id }">View Student</a></li>
		<li><a href="<%=request.getContextPath()%>/Exam?flag=searchcollegeexam&id=${q.id }">View Exam</a></li>
		<li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<div style="padding-top: 2%;">
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
				<input type="hidden" name="flag" value="hodinsert">
	            <input type="submit" name="submit">	
			</form>
		</div>
	</div>
</body>
</html>