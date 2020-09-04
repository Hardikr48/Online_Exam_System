<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	var checkboxes = $("input[type='checkbox']"),
    submitButt = $("input[type='submit']");

checkboxes.click(function() {
    submitButt.attr("disabled", !checkboxes.is(":checked"));
});

});
</script>
  <style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}


header {
  background-color: #666;
  padding: 2px;
  text-align: center;
  font-size:20px;
  color: white;
}


footer {
  background-color: #777;
  padding: 10px;
  text-align: center;
  color: white;
  margin-top: 122px;
}
  
h1{
text-align: center;
font-size: 30px;
}
</style>
</head>
<body  style="background-color: lightgray">
<header>
  <h2>Apollo Institute Of  Engineering</h2>
</header>
<h1>Online Examination Rules</h1>

<ol>
	<li>
		There are total 3 Phases.
		<ul>
			<li>phase 1 contains 20 questions (1 mark each). Time Limit <span>-</span> 20minutes.</li>
			<li>phase 2 contains 20 questions (1 mark each). Time Limit <span>-</span> 20minutes.</li>
			<li>phase 3 contains 20 questions (1 mark each). Time Limit <span>-</span> 20minutes.</li>
		</ul>
	</li>
	<li> You will get 1 mark for 1 correct answer.</li>
	<li> You will get (-0.25) mark for 1 wrong answer.</li>
	<li> You will get 0 mark for 1 unattended.</li>
	
	<li> You will automatically redirect to Next Phase as soon as the time for Current Phase gets over.</li>
	
	<li> You can go to Next Phase even if you have not finished your Current Phase.</li>
	
	<li> Once you entered the Next Phase, you cannot go back to Previous Phase.</li>
</ol>
	<b style=" margin-left:20px;  ">NOTE: </b>
	<p style=" margin-left:50px;  "><b>CLEAR:</b>  Clears the selected options for current question.</p>
	<p style="margin-top: -18px; margin-left:50px;  "><b>Confirm & Next:</b> Saves your answer and displays the Next Question. (You can edit it later if you want)</p>
	<p style="margin-top: -18px; margin-left:50px;  "><b>Review & Next:</b> Answer in Review & Next will be consider as unattempted. You can Review and Confirm it later.
	Skip: Skip to Next Question.</p>
	<p style="margin-top: -21px; margin-left:50px;  "><b>Next Phase:</b> Starts the Next Phase. (You cannot go back to Previous Phase once you entered the Next Phase)
	Submit: Submit your Exam.</p>
	<form action="Exam" method="post" class="was-validated">
    
	<div class="form-group form-check"  style=" margin-left: 31px;">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember" required> I agree on not
      </label>
    </div>
     <a href="Student_List_Exam.jsp" role="button" class="btn btn-primary" style="margin-left: 30px;">Exit</a>
     <c:forEach items="${sessionScope.examlist }" var="q" end="0">
		<input type="hidden" name="examid" value="${q.examid.id }">
 		<input type="hidden" name="flag" value="examstart">
  		
  	</c:forEach>
  	<input type="submit" class="btn btn-primary" value="Next" disabled style="margin-left: 1066px">

  </form>
	

<footer> © Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved

</footer>

</body>
</html>