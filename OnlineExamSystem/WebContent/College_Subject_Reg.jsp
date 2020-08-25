<%@page import="VO.DepartmentVo"%>
<%@page import="VO.SemVo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="java.util.List"%>
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



<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>
$(document).ready(function() {                        
	$('#sem').change(function(event) {  
        var username=$('#sem').val();
     $.get('Department1',{q:username},function(department) {
    	 alert(department)
    	 var products = $.parseJSON(department);
			var s = '';
			for(var i = 0; i < products.length; i++) {
				s += 'Id: ' + products[i].id + '<br>';
			}
			$('#department').html(s);
     });   
  });
});

</script>



<!-- /* $(document).ready(function s() {
	$('#sem').ready(function(event) {
	 var sem = $("select#sem").val();
	 alert(sem);	
	 $.get('Department1', {
	  semname : sem},
	  function(jsonResponse) {
		 var select = $('#department');
		 select.find('option').remove();
		    $.each(jsonResponse, function(index, value) {
		   $('<option>').val(value).text(value).appendTo(select);
		 });
	 });
	});
}); */
/* function get(){
	semlist = document.getElementById("sem");
	var q = semlist.value;
	alert(q);
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.open("GET","Department1?q="+q,true);
    xmlhttp.send();
}
 */ -->
<!-- /* $(document).ready(

		function() { // When the HTML DOM is ready loading, then execute the following function...

		$(

		'#imgLoader').hide();

		$(
 */ -->
		
<%-- 

	  semlist = document.getElementById("sem");
	  var selectedCar = semlist.value;
	 
	  <%
	 	 List<DepartmentVo> list = (List) session.getAttribute("departmentist");
	  	for(DepartmentVo sem :list ){
	  		%>
	  		if()
	  	<%}%>
	  
	  
	  var selectedCar1 = somesession.value;
	  alert(somesession)
	  var options = somesession.filter(function(model) {
	    return somesession.sem === selectedCar;
	  });

	  removeOptions(modelsSelect);
	  removeOptions(configurationSelect);
	  addOptions(modelsSelect, options);
	}  --%>


</head>
<body>

	<div>
		<ul>
			<c:forEach items="${sessionScope.collegedata }" var="q">
		<li><a href="College_Login.jsp">Home</a></li>
		<li><a href="<%=request.getContextPath()%>/Sem?flag=insert&id=${q.id }">Add Sem </a></li>
		<li><a href="<%=request.getContextPath()%>/Department?flag=insert&id=${q.id }">Add Department</a></li>
		<li><a href="<%=request.getContextPath()%>/Subject?flag=insert&id=${q.id }">Add Subject</a></li>
		<li><a href="<%=request.getContextPath()%>/Professor?flag=insert&id=${q.id }">Add Professor</a></li>
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
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<div style="padding-top: 2%;">
			<%
				if (session.getAttribute("addsubject") != null) {
			%>
			<p style="color: red">Add Successfully</p>
			<%
				session.removeAttribute("addsubject");
			} else if (session.getAttribute("selectsemordepartment") != null) {
			%>
			<p style="color: red">Please select Sem or Department</p>
			<%
				session.removeAttribute("selectsemordepartment");
			} else if (session.getAttribute("cahcksubject") != null) {
			%>
			<p style="color: red">Subject already exist</p>
			<%
				session.removeAttribute("cahcksubject");
			}
			%>
			<h3>Add Sem</h3>
			<form action="<%=request.getContextPath()%>/Subject" method="post">
				<span>*</span>
				
				Sem:-<br> 
				<select name="semid" id="sem" required>
					<option>Select</option>
					<c:forEach items="${sessionScope.semlist }" var="q">
						<option value="${q.id }">${q.semname }</option>
					</c:forEach>
				</select><br><br> 

				<span>*</span>Department:-<br>
				<select	name="departmentid" id="department" required>
					<option>Select</option>
				</select><br> <br> 
				
				<span>*</span>Subject Name:- <input type="text"	name="subject" required><br> <br> 
				<input type="hidden" name="flag" value="insert" />
				<input type="submit"value="SUBMIT" />
			</form>
		</div>
	</div>
</body>
</html>