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

$(document).ready(function(){
	var username="exam";
	var getans;
	var queid;
	var selectedans;
	var selectedid;
	var correct_ans=0;
	var wrong_ans=0;
	var mark_ans=0;
	var totalmarks=0;
	var val;
	var time = 0;
	var topic;
	var subject;
	var i =1;
	var ans= [];  
	let obj ;
   	$.post('Department1',{flag:username},function(response) {
   		obj = JSON.parse(response);
   		
   		var a=0;
   		$.each(obj, function(index, value) {
 		   	console.log(obj[index]);
 		   	var html = '';
 		   html += '<li><button class="button8"  data-id="'+a+'" data-id2= "'+i+' "id="queclik'+i+'">'+i+'</button></li>';
 		   $('.box1212').append(html);
 		   	i++;
 		   	a++;
 		   	console.log("i ==>"+i+"a==>"+a);
 		 });
   		time = obj[0].time;
   		totalmarks = obj[0].marks;
   		topic=obj[0].topic;
   		subject=obj[0].subject;
   		console.log(time);
   		
   		var sec = time*60;
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
    	        
    	        var json = JSON.stringify(ans);
    	        --i;
    	        sessionStorage.setItem("topic", topic);
    	        sessionStorage.setItem("subject", subject);
    	        sessionStorage.setItem("que", i);
    	        sessionStorage.setItem("time", time);
    	        sessionStorage.setItem("listofans", json);
    	        sessionStorage.setItem("correctans", correct_ans);
    	        sessionStorage.setItem("worngans", wrong_ans);
    	        sessionStorage.setItem("markans", mark_ans);
    	        sessionStorage.setItem("totalmark", totalmarks);

    	        window.location.replace("NewFile.jsp");
    	    }
    	}
   	    
   	    
   		var number = $(this).attr("data-id");
		let getque= obj[0];
	
	
	document.getElementById("queid").value = 0 ;
	document.getElementById("id").value = getque.id ;
	document.getElementById("ans").value = getque.ans ;
	document.getElementById("op1").value = getque.op1;
	document.getElementById("op2").value = getque.op2;
	document.getElementById("op3").value = getque.op3;
	document.getElementById("op4").value = getque.op4;
	
	document.getElementById("op11").innerHTML = getque.op1;
 	document.getElementById("op22").innerHTML = getque.op2;
 	document.getElementById("op33").innerHTML = getque.op3;
 	document.getElementById("op44").innerHTML = getque.op4;
 	document.getElementById("que").innerHTML = getque.Que;
 
	});
   	$(document).on('click', '.button8', function(){
   		var radios = document.getElementsByName('fname');
   		for (c=0;c<radios.length;c++){
   			radios[c].checked=false;
   		}
   		var number = $(this).attr("data-id");
		let getque= obj[number];

		document.getElementById("queid").value = number;
		document.getElementById("op1").value = getque.op1;
		document.getElementById("op2").value = getque.op2;
		document.getElementById("op3").value = getque.op3;
		document.getElementById("op4").value = getque.op4;
		document.getElementById("id").value = getque.id ;
		document.getElementById("ans").value = getque.ans ;
		
		document.getElementById("op11").innerHTML = getque.op1;
	 	document.getElementById("op22").innerHTML = getque.op2;
	 	document.getElementById("op33").innerHTML = getque.op3;
	 	document.getElementById("op44").innerHTML = getque.op4;
	 	document.getElementById("que").innerHTML = getque.Que;
	 }); 
   	$(document).on('click', '.button5' , function(){
   		var radios = document.getElementsByName('fname');
		console.log(radios);
		var val= "";
		var id;
		for (var i = 0, length = radios.length; i < length; i++) {
		    if (radios[i].checked) {
		       val = radios[i].value;
		       console.log(val);
		       break;
		     }
		}
		if (val == "" ) {
		  alert('please select choice answer');
		  
		} else if ( val != "" ) {
			var quechange=$('#queid').val();
			var nos = ++quechange;
			var quid=$("#queclik"+nos);
			
			$(quid).css("background-color","green");
			
			
			for (c=0;c<radios.length;c++){
	   			radios[c].checked=false;
	   		}
	   		
	   		id=$('#queid').val();
		   	id++;
			let getque= obj[id];
			
			selectedid=$('#id').val();
			selectedans=$('#ans').val();
			
			var points = new Array();
	    	points.push(selectedid);
	      	points.push(val);
	      	ans.push(points);
	      	
	      	for (let index = 0; index < obj.length; index++) {
	      		var element = obj[index];
	        	if(element.id == selectedid && element.ans == val){
	        		correct_ans++;
	        	}
	        	else if(element.id == selectedid && element.ans != val){
	        		wrong_ans++;
	        	}
	        }
			document.getElementById("queid").value = id;
			document.getElementById("op1").value = getque.op1;
			document.getElementById("op2").value = getque.op2;
			document.getElementById("op3").value = getque.op3;
			document.getElementById("op4").value = getque.op4;
			document.getElementById("id").value = getque.id ;
			document.getElementById("ans").value = getque.ans ;
			
			document.getElementById("op11").innerHTML = getque.op1;
		 	document.getElementById("op22").innerHTML = getque.op2;
		 	document.getElementById("op33").innerHTML = getque.op3;
		 	document.getElementById("op44").innerHTML = getque.op4;
		 	document.getElementById("que").innerHTML = getque.Que;	
		}
	 });
	$(document).on('click', '.button1', function(){
		var radios = document.getElementsByName('fname');
		console.log(radios);
		var val= "";
		var id;
		for (var i = 0, length = radios.length; i < length; i++) {
		    if (radios[i].checked) {
		       val = radios[i].value;
		       console.log(val);
		       break;
		     }
		}
		if (val == "" ) {
		  alert('please select choice answer');
		  
		} else if ( val != "" ) {
			var quechange=$('#queid').val();
			var nos = ++quechange;
			var quid=$("#queclik"+nos);
			
			$(quid).css("background-color","purple");
			
			
			for (c=0;c<radios.length;c++){
	   			radios[c].checked=false;
	   		}
	   		
	   		id=$('#queid').val();
		   	id++;
			let getque= obj[id];
			
			selectedid=$('#id').val();
			selectedans=$('#ans').val();
			var points = new Array();
	    	points.push(selectedid);
	      	points.push(val);
	      	ans.push(points);
	      	
	      	for (let index = 0; index < obj.length; index++) {
	      		var element = obj[index];
	        	if(element.id == selectedid && element.ans == val){
	        		mark_ans++;
	        		console.log(mark_ans);
	        	}
	        	else if(element.id == selectedid && element.ans != val){
	        		wrong_ans++;
	        		console.log("wrong_ans = > "+wrong_ans);
	        	}
	        }
			document.getElementById("queid").value = id;
			document.getElementById("op1").value = getque.op1;
			document.getElementById("op2").value = getque.op2;
			document.getElementById("op3").value = getque.op3;
			document.getElementById("op4").value = getque.op4;
			document.getElementById("id").value = getque.id;
			document.getElementById("ans").value = getque.ans;

			document.getElementById("op11").innerHTML = getque.op1;
		 	document.getElementById("op22").innerHTML = getque.op2;
		 	document.getElementById("op33").innerHTML = getque.op3;
		 	document.getElementById("op44").innerHTML = getque.op4;
		 	document.getElementById("que").innerHTML = getque.Que;	
		}
	});
	$(document).on('click', '.button3', function(){
		var id;
		id=$('#queid').val();
   		id--;
		let getque= obj[id];
		
		document.getElementById("queid").value = id;
		document.getElementById("op1").value = getque.op1;
		document.getElementById("op2").value = getque.op2;
		document.getElementById("op3").value = getque.op3;
		document.getElementById("op4").value = getque.op4;
		document.getElementById("id").value = getque.id;
		document.getElementById("ans").value = getque.ans ;
		
		document.getElementById("op11").innerHTML = getque.op1;
	 	document.getElementById("op22").innerHTML = getque.op2;
	 	document.getElementById("op33").innerHTML = getque.op3;
	 	document.getElementById("op44").innerHTML = getque.op4;
	 	document.getElementById("que").innerHTML = getque.Que;
	 
	 });
	
	$(document).on('click', '.button4', function(){
		console.log("dsadsa");
		var json = JSON.stringify(ans);
		--i;
		console.log(json);
        sessionStorage.setItem("topic", topic);
        sessionStorage.setItem("subject", subject);
        sessionStorage.setItem("que", i);
        sessionStorage.setItem("time", time);
        sessionStorage.setItem("listofans", json);
        sessionStorage.setItem("correctans", correct_ans);
        sessionStorage.setItem("worngans", wrong_ans);
        sessionStorage.setItem("markans", mark_ans);
        sessionStorage.setItem("totalmark", totalmarks);

        window.location.replace("NewFile.jsp");
	 
	 });
});
	</script>
<style>
.button8 {
  width: 50px;
  height: 50px;
  background-color: black;
  color: white;
  text-align:center;
  line-height:50px;
  border-radius: 25px;
  text-decoration: none;
  border: none; 
}
h1{
	font-size: 20px;
	border:1px solid black;		
	color:blue;
	background-color:lightgray;
	width: 77%;
}
.top{
	border:1px solid black;
	margin-top:-13px;
	height: 452px;
	width: 77%;
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
	background-color: purple;
}
.button2{
	background-color:blue;
}

.button5{
    margin-left: 300px;
	background-color:green;
}
.button3{
margin-left: 350px;
	background-color: red;
}
.box{
	border-top: 2px solid black;
	margin-top: 1px;
}
.hero{
	border:1px solid black;
	width: 20%;
	margin-left:1163px;
    margin-top:-533px;
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
	    margin-left:1147px;;
	    border:1px solid black;
	    width:301px;
	    margin-top:-394px;
		height: 420px;
}
header {
  background-color: #666;
  text-align: center;
  font-size:20px;
  color: white;
}
.box1212{
list-style-type: none;
    padding-left: 0px;
    display: flex;
    width: 280px;
    flex-wrap: wrap;
    margin: auto;
}
.box1212 li{

margin:5px 3px;}
</style>
</head>
<body>
	<header>
	 <h3>Apollo Institute Of Engineering</h3>
	</header>
  			<fieldset style="width:74%;height:522px">
	 	<legend>QUESTIONS FOR EXAM-1ST EXAM</legend>
		<h1>Ouestion No.20 Of 20</h1>
		
   		<div class="top">
   			<form>
	   		<h2 id="que"></h2>
			<input type="hidden" name="id" id="queid" value="">
			<input type="hidden" name="id" id="id" value="">
			<input type="hidden" name="id" id="ans" value="">
			<input type="radio" name="fname" id="op1" >
	  	  	<label id="op11">Old Technology</label><br>
		  	
		  	<input type="radio" name="fname" id="op2">
		  	<label id="op22">New Technology</label><br>
		  	
		  	<input type="radio" name="fname" id="op3">
		  	<label id="op33">Middle Technology</label><br>
		  	
		  	<input type="radio" name="fname" id="op4">
		  	<label id="op44" >All of the abive</label><br>
		  	
		  	<input type="reset" style="margin-top: 259px; background-color: blue;" name="reset">
		  	</form>
		  	<div class="box" id="box">
		  		<button class="button button4 ">Submit </button>
			  	<button class="button button1 ">Mark & Review </button>
			  	<button class="button button3">Previous Question</button>
			  	<button class="button button5">Save & Next</button>
			</div>
			<div class="bottom">
			<!-- <table style="margin-left: 19px;"> -->
			<p style="margin-left: 19px">View Question palettle</p>
			<ul class="box1212">
			
			</ul>

			
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


<footer
        style="background-color:rgb(136, 127, 127); color: black; position: fixed;bottom: 0%;width: 100%; text-align: center;">
        <div class=" container">
            <p>© Copyright <strong>EXPERT WEB DESIGNING</strong> All Rights Reserved </p>
        </div>
    </footer>
</body>
</html>