<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	var lastname = sessionStorage.getItem("listofans");
	var totalque = sessionStorage.getItem("que");
	var time =sessionStorage.getItem("time");
	var correctans =sessionStorage.getItem("correctans");
	var worngans = sessionStorage.getItem("worngans");
	var markans = sessionStorage.getItem("markans");
	var totalmark = sessionStorage.getItem("totalmark");
	var topic = sessionStorage.getItem("topic");
	var subject = sessionStorage.getItem("subject");
	var total = (parseInt(correctans)+parseInt(markans));
	var s =worngans*0.25;
	var total1 = total -(worngans*0.25);
	var total2=(100*total1)/totalmark;
	var totalque1 = total+parseInt(worngans);
	var notattended = parseInt(totalque) - totalque1;
	var result;
	var message = "";
	if (total2<=40){
		result="Fail";
		message="Sorry!! You have not cleared this exam.";
	}
	else if (total2<=40 && 50 >total2){
		result="Third Class";
		message= "Congratulations!! You have passed this exam.";
	}
	else if (total2<=50 && 60 >total2){
		result="Second Class";
		message= "Congratulations!! You have passed this exam.";
	}
	else if (total2 <= 60 && 70 >total2){
		result="First Class";
		message= "Congratulations!! You have passed this exam.";
	}
	else if (total2>=70){
		result="Distinction";
		message= "Congratulations!! You have passed this exam.";
	}
	$("#text1").text(message);
	$("#text").val(message);
	$("#result1").val(result);
	$("#result").val(total2);
	$("#time").val(time);
	$("#correct").val(correctans);
	$("#wrong").val(worngans);
	$("#notattended").val(notattended);
	$("#mark").val(markans);
	$("#totalmcq").val(totalque);
	$("#topic").val(topic);
	$("#subject").val(subject);
	$("#totalmark").val(totalmark);
	if(result == "Fail" ){
		$("#text1").css("color","red");
	}
	else {
		$("#text1").css("color","green");
	}
});

</script>

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
		input{
		 	pointer-events: none;
		 	width: 100px;
		}
		
		#Submit{
			pointer-events: auto;
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
                            style="border-radius: 50%;">
                    </div>
                </span>
            </div>
        </div>
    </nav>
</head>
<body style="background-color: lightgray">

<form action="Result" method="post">

	<label for="fname" id="text1" style="text-align: center;font-size: 34px;margin-left:345px; margin-top: 50px; " >Congrataltions.You Have Passed the Exam</label>
	<input type="hidden" name="text" id="text"  style="border:hidden;background-color: lightgray;color: black; " readonly="readonly" ><br>
	
	<label style="font-size: 28px;margin-left: 345px">Result :</label>
	<input type="text" name="result1" id="result1" style="border:hidden;font-size: 28px; margin-top:10px;  background-color: lightgray;color: black" readonly="readonly"><br><br>
	
	<label  style="font-size: 28px;margin-left: 345px">Mark obtained <b><span style="font-size: 25px;">&#37;</span>:</b></label>
	<input type="text" name="result" id="result" style="border:hidden;font-size: 28px;width:50px ;background-color: lightgray;color: black" readonly="readonly"&#37;><br><br>	
	
	<label  style="font-size: 28px;margin-left: 345px">Time :</label>
	<input type="text" name="time"  id="time" style="border:hidden;font-size: 28px;width:50px ; background-color: lightgray;color: black" readonly="readonly"><b><span style="font-size: 20px;">Minute</span></b><br><br>
	
	<label  style="font-size: 28px;margin-left: 345px">Correct :</label>
	<input type="text" name="correct" id="correct" style="border:hidden;font-size: 28px;background-color: lightgray;color: black" readonly="readonly"><br><br>
	
	<label  style="font-size: 28px;margin-left: 345px">Wrong :</label>
	<input type="text" name="wrong" id="wrong" style="border:hidden;font-size: 28px;background-color: lightgray;color: black" readonly="readonly"><br><br>
	
	<label  style="font-size: 28px;margin-left: 345px">Mark & Review :</label>
	<input type="text" name="mark" id="mark" style="border:hidden;font-size: 28px;background-color: lightgray;color: black" readonly="readonly"><br><br>
	
	<label  style="font-size: 28px;margin-left: 345px">Not Attended :</label>
	<input type="text" name="notattended" id="notattended" style="border:hidden;font-size: 28px;background-color: lightgray;color: black" readonly="readonly"><br><br>
	
	<label  style="font-size: 28px;margin-left: 345px">Total Mcq :</label>
	<input type="text" name="totalmcq" id="totalmcq" style="border:hidden;font-size: 28px;background-color: lightgray;color: black" readonly="readonly"><br><br>
	<input type="hidden" id="topic" name="topic">
	<input type="hidden" id="totalmark" name="totalmark">
	<input type="hidden" id="subject" name="subject" >
	<input type="hidden" name="flag" value="insert">
	<input type="Submit" id="Submit" value="Exit"  class="btn btn-info"style="float: right;margin-right:185px">
</form>
<footer
        style="background-color:rgb(136, 127, 127); color: black; position: fixed;bottom: 0%;width: 100%; text-align: center;">
        <div class=" container">
            <p>© Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved </p>
        </div>
    </footer>
</body>
</html>