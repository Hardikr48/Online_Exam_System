<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
				var count = 0;
				var maxfield = 20;
				$(document).on('click', '.add', function(){
				  	if(count<maxfield){
						count++;
					   	var html = '';
					   	html += '<tr>';
					   	html += '<td><input type="text" name="departmentlist[]" class="form-control"  id="departmentid'+count+'" value="Question'+count+'" readonly></td>';
					   	html+= '<td> <input type="text" name="departmentlist[]" class="form-control"  id="departmentid'+count+'"></td>';
					   	html+= '<td> <input type="text" name="departmentlist[]" class="form-control"  id="departmentid'+count+'"></td>';
					   	html+= '<td> <input type="text" name="departmentlist[]" class="form-control"  id="departmentid'+count+'"></td>';
					   	html+= '<td> <input type="text" name="departmentlist[]" class="form-control"  id="departmentid'+count+'"></td>';
					   	html+= '<td> <input type="text" name="departmentlist[]" class="form-control"  id="departmentid'+count+'"></td>';
					   	html+= '<td> <input type="text" name="departmentlist[]" class="form-control"  id="departmentid'+count+'"></td>';
					   	html += '<td><button type="button" name="remove" class="btn btn-danger btn-xs remove"><span class="glyphicon glyphicon-minus"></span></button></td>';
					   	$('tbody').append(html);
					   	
					
				  	}else{
				  		alert("Field limit reached")
				  	}
				  	
				})
				

			  	$(document).on('click', '.remove', function(){
			  		count--;
			    	$(this).closest('tr').remove();
			  	});
				
});
		</script>

<style>
#inputs input {
	margin-left: 70px !important;
}
</style>
</head>
<body>

	<h3>Exam Form</h3>
	<form name="add_name" id="add_name">
	<label>Semester :</label>
	<select id="sem" name="sem">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
	</select>
	<br>
	<br>
	<label>Choose Subject:</label>
	<select id="subject" name="subject">
		<option value="Java">Java</option>
		<option value="Python">Python</option>
		<option value="AI">AI</option>
	</select>
	<br>
	<br>
	<label>Choose Phase:</label>
	<select id="phase" name="phase">
		<option value="phase1">Phase1</option>
		<option value="phase2">Phase2</option>
		<option value="phase3">Phase3</option>
	</select>
	<br>
	<br> 
			<div class="table-repsonsive">
		          <span id="error"></span>
		         	 <table class="table table-bordered" id="item_table" >
		            <thead>
		              <tr>
		                <th >Question No</th>
		                <th>Question</th>
		             	 <th>OptionA</th>
		             	 <th>OptionB</th>
		             	 <th>OptionC</th>
		             	 <th>OptionD</th>
		             	<th>Correct Answer</th>
		                <th><button type="button" name="add" class="btn btn-success btn-xs add"><span class="glyphicon glyphicon-plus"></span></button></th>
		              </tr>
		            </thead>
		            <tbody></tbody>
		          </table>
		          </div>
		</form>
		<footer
        style="background-color:rgb(136, 127, 127); color: black; position: fixed;bottom: 0%;width: 100%; text-align: center;">
        <div class=" container">
            <p>© Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved </p>
        </div>
    </footer>
</body>
</html>
