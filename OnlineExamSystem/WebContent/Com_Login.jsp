<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.main-bg {
	background: url("img/onlile.jpg") no-repeat center;
	width: auto;

}
div {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

.bg-content-w3pvt {
	max-width: 400px;
	margin: 0 auto;
	background: #fff;
	text-align: center;
}

.top-content-style {
	padding: 2vw 4vw 4vw;
	background: #1cc7d0;
}

.sub-main-w3 form {
	background: #ffff;
	padding: 2em;
	box-shadow: 2px 5px 16px 2px rgba(16, 16, 16, 0.18);
	margin: -2.5em 2.5em 2em border-radius: 4px;
}

.from {
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

p.legend {
	color: #4e4d4d;
	font-size: 24px;
	text-align: center;
	margin-bottom: 1.2em;
}

p.legend span {
	color: #000;
	margin-left: 10px;
}

.fa {
	display: inline-block;
	font: normal normal normal 14px/1 FontAwesome;
	font-size: inherit;
	text-rendering: auto;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.fa-hand-o-down:before {
	content: "\f0a7";
}

.input {
	position: relative;
	margin: 20px auto;
	width: 100%;
}

.input input {
	width: 100%;
	padding: 13px 10px 13px 34px;
	display: block;
	border: none;
	border: 1px solid #1cc7d0;
	color: #000;
	box-sizing: border-box;
	font-size: 13px;
	outline: none;
	letter-spacing: 1px;
	background: #fff;
	box-shadow: 2px 5px 16px 2px rgba(16, 16, 16, 0.18);
}

user agent stylesheet
input[type="email" i] {
	padding: 1px 2px;
}

.input span {
	position: absolute;
	display: block;
	color: #1cc7d0;
	left: 10px;
	top: 12px;
	font-size: 16px;
}

input[type="password" i] {
	-webkit-text-security: disc !important;
}

.submit {
	width: 45px;
	height: 45px;
	display: block;
	margin: 2.5em auto 0;
	background: #1cc7d0;
	-webkit-border-radius: 10px;
	-o-border-radius: 10px;
	-ms-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	border: none;
	cursor: pointer;
	-webkit-transition: 0.5s all;
	-o-transition: 0.5s all;
	-moz-transition: 0.5s all;
	-ms-transition: 0.5s all;
	transition: 0.5s all;
}

button {
	appearance: button;
	-webkit-writing-mode: horizontal-tb !important;
	text-rendering: auto;
	color: -internal-light-dark(buttontext, rgb(170, 170, 170));
	letter-spacing: normal;
	word-spacing: normal;
	text-transform: none;
	text-indent: 0px;
	text-shadow: none;
	display: inline-block;
	text-align: center;
	align-items: flex-start;
	cursor: default;
	background-color: -internal-light-dark(rgb(239, 239, 239),
		rgb(74, 74, 74));
	box-sizing: border-box;
	margin: 0em;
	font: 400 13.3333px Arial;
	padding: 1px 6px;
	border-width: 2px;
	border-style: outset;
	border-color: -internal-light-dark(rgb(118, 118, 118),
		rgb(195, 195, 195));
	border-image: initial;
}

.submit span {
	color: #fff;
	font-size: 20px;
}

a.bottom-text-w3ls {
	color: #757474;
	font-size: 16px;
	display: inline-block;
	margin: 0em 1em 2em;
	letter-spacing: 1px;
}

a.bottom-text-w3ls:hover {
	background: #03e9f4;
	color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 5px #03e9f4, 0 0 25px #03e9f4, 0 0 50px #03e9f4, 0 0
		100px #03e9f4;
}

a {
	text-decoration: none;
}

a:-webkit-any-link {
	cursor: pointer;
}

.copyright {
	margin-top: 3.08vw;
	padding-bottom: 1.5vw;
}
</style>
</head>
<body>
	<div class="main-bg">
			Latest Login Form
			<div class="sub-main-w3">
				<div class="bg-content-w3pvt">
					<div class="top-content-style">
						<img src="img/expertlogo.png" alt="" width="50" height="50"style="border-radius: 50%">
					</div>
					<form action="Login" method="post">
						<p class="legend">
							Login Here <span class="fas fa-hand-o-down"></span>
						</p>
						<div class="input">
							<input type="email" placeholder="Email" name="email" required="">
							<span class="fas fa-envelope"></span>
						</div>
						<div class="input">
							<input type="password" placeholder="Password" name="pwd"
								required=""> <span class="fas fa-unlock"></span>
						</div>
						<input type="hidden" name="flag" value="verify">
						<button type="submit" class="btn submit">
							<span class="glyphicon glyphicon-log-in"></span>
						</button>
					</form>
					<a href="forgetpassword.jsp" class="bottom-text-w3ls">Forgot Password?</a>
				</div>
			</div>
	</div>
</body>
</html>