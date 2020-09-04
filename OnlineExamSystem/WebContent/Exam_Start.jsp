<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script>

/* $(document).ready(function(){
	var username="exam";
   	$.post('Department1',{flag:username},function(response) {
   		var obj = JSON.parse(response);
   		alert(response)
   		var dataObject = obj.data;
   		
	    var dataMap = new Map(Object.entries(dataObject));
	    var resultMap = new Map();
	    for (const key of dataMap.keys())  {
	        console.log(key);
	        var keyMap = new Map(Object.entries(dataMap.get(key)));
	        resultMap.set(key, keyMap);
	    }
	
	    console.log("done!");
	    return resultMap;
 	});
}); */
$(document).ready(function(){
	const myMap = new Map();
	var username="exam";
   	$.post('Department1',{flag:username},function(response) {
   		var obj = JSON.parse(response);
   		
   		$.each( obj, function( key , value ) {
   		myMap[key] = value;
   		});
   	});
   	$(document).on('click', '.button', function(){
		var queid = $('#queclik').val();
		alert(queid);
		var getque= myMap[queid];
		$.each(getque, function(index, value) {
			$('<option>').val(obj[index].id).text(obj[index].subject).appendTo(select);
		 });
	 });
   	var h3 = document.getElementsByTagName("h3");
   	h3[0].innerHTML = "Countdown Timer With JS";

   		var sec = 1800,
   	    countDiv    = document.getElementById("timer"),
   	    secpass,
   	    countDown   = setInterval(function () {
   	        'use strict';
   	        
   	        secpass();
   	    }, 1000);

   	function secpass() {
   	    'use strict';
   	    
   	    var min     = Math.floor(sec / 60),
   	        remSec  = sec % 60;
   	    
   	    if (remSec < 10) {
   	        
   	        remSec = '0' + remSec;
   	    
   	    }
   	    if (min < 10) {
   	        
   	        min = '0' + min;
   	    
   	    }
   	    countDiv.innerHTML = min + ":" + remSec;
   	    
   	    if (sec > 0) {
   	        
   	        sec = sec - 1;
   	        
   	    } else {
   	        
   	        clearInterval(countDown);
   	        
   	        countDiv.innerHTML = 'countdown done';
   	        
   	    }
   	}
   	
   

});
	</script>
<style>
h1{
	font-size: 20px;
	border:1px solid black;		
	color:blue;
	background-color:lightgray;
}
.top{
	border:1px solid black;
	margin-top:-13px;
	height: 430px;

}
h2{
	border:1px solid black;
	width:924px;
	margin-left: 13px;
	margin-top: 10px;
	background-color: lightgray;
}
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.button1{
	background-color: orange;
}
.button2{
	background-color:blue;
}

.button5{
	background-color:red;
}
.box{
	border-top: 2px solid black;
	margin-top: 254px;
}
.hero{
	border:1px solid black;
	width: 20%;
	margin-left:1163px;
    margin-top:-509px;
}
.button6{
	background-color: black;
	border-radius: 50%;
	padding: 13px;
	color: white;
	border: 1px solid black;

}
footer {
  background-color: #777;
  text-align: center;
  color: white;
  margin-top: 122px;
}
.bottom{
	    margin-left:1163px;;
	    border:1px solid black;
	    width:20%;
	    margin-top:8px;
		height: 395px;
}
header {
  background-color: #666;

  text-align: center;
  font-size:20px;
  color: white;
}
</style>
</head>
<body>
	<header>
	 <h3>Apollo Institute Of Engineering</h3>
	</header>
  		<fieldset style="width:74%;height:497px">
	 	<legend>QUESTIONS FOR EXAM-1ST EXAM</legend>
		<h1>Ouestion No.20 Of 20</h1>
   		<div class="top">
	   		<h2 id="que"></h2>
			
			<input type="radio" name="fname" id="op1">
	  	  	<label for="fname"id="op1">Old Technology</label><br>
		  	
		  	<input type="radio" name="fname">
		  	<label for="fname" id="op2">New Technology</label><br>
		  	
		  	<input type="radio" name="fname">
		  	<label for="fname" id="op3">Middle Technology</label><br>
		  	
		  	<input type="radio" name="fname">
		  	<label for="fname" id="op4" >All of the abive</label><br>
		  	
		  	<div class="box">
			  	<button class="button button1">Review later</button>
			  	<button class="button button2">Clear</button>
			  	<button class="button button3">Black</button>
			  	<button class="button button4">Save& Next</button>
			  	<button class="button button5">Submit Quiz</button>
			</div>
		</div>
	</fieldset>
<div class="hero">
	<img src="img/student.jpg" width="71" height="71" style="  border-radius: 50%;
    margin-top: 14px;
    margin-left: 6px;
    margin-bottom: -11px;">
		<p style="margin-left: 93px;margin-top: -57px">Time left</p>
		<div class="count">
    		<div id="timer" style="margin-left: 100px; margin-top: -12px;"></div>
  		</div>
		<p style="margin-left: 95px;margin-top:5px">Hardik Ramani</p>
	</div>

<div class="bottom">
	<table style="margin-left: 19px;">
		<p style="margin-left: 19px">View Question palettle</p>
		<%int i = 1; %>
	 	<tr>
	 		<c:forEach items="${sessionScope.quelist }" var="q">
	 			<%if (i%5==0){%>
						<td><button class="button button6"  data-semester_id="${q.id }" id="queclik" value="${q.id }"><%=i %></button></td>
					<tr>
				<%}else{ %>
					<td><button class="button button6"id="queclik" data-semester_id="${q.id }" value="${q.id }"><%=i %></button></td>
				<%}i++; %>
			</c:forEach>
		</tr>
	</table>
</div>
<footer
        style="background-color:rgb(136, 127, 127); color: black; position: fixed;bottom: 0%;width: 100%; text-align: center;">
        <div class=" container">
            <p>© Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved </p>
        </div>
    </footer>
</body>
</html>