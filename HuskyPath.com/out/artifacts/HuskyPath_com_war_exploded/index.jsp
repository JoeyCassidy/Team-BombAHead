
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>

		<!-- Required meta tags -->
		<meta charset="utf-8">
		<title>Login</title>

		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!-- firebase-->
		<script src="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth.js"></script>
		<link type="text/css" rel="stylesheet" href="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth.css" />

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

		<style>
			.intro{
				text-align: center;
			}
			.setup{
				color: #10E90D;
			}
			.bbbbb{
				background: url(https://cdn.pixabay.com/photo/2017/03/25/17/55/color-2174045_960_720.png) no-repeat center center; 
				-webkit-background-size: cover;
				-moz-background-size: cover;
				-o-background-size: cover;
				background-size: cover;
			}
			html, body{
				height:100%;
			}
		</style>
	</head>
	<!--temp color-->
	<body class="bbbbb">
		<div class="container-fluid" style="height:100%">
			<div class="row" style="margin-top: 100px;">
				<div class="col-lg-12">
					<div class="row intro">
						<div class="col-lg-3"></div>
						<div class="col-lg-6">
							<div class="jumbotron jumbotron-fluid rounded border border-dark shadow p-3 mb-5 bg-white" style="color: #FFFFFF">
								<h1 style="color: black">Welcome to Husk Path</h1>
								<h3 style="color: black">Login to get started</h3>
								<img src="https://cdn.pixabay.com/photo/2016/03/31/15/34/arrow-1293397_960_720.png" width="100" height="100"> 
								<h1> </h1>

								<div id="firebaseui-auth-container">
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#signIn">sign in</button>
									<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#signUp">sign up</button>

									<button type="button" onclick="location.href='/result'">this</button>
								</div>
							</div>
						</div>
						<div class="col-lg-3"></div>
					</div>
				</div>
			</div>
			<!-- signup modal -->
			<div id="signUp" class="modal" tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Sign up</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p>Don't forget to make your password secure!</p>
							<form action="views/profile.jsp" method="post" id="signUpForm">
								<div class="form-group">
									<label for="studentName">Your name</label>
									<input type="text" class="form-control" id="studentName" name="studentName" placeholder="name" required>
								</div>
								<div class="form-group">
									<label for="emailInput-signUp">Email address</label>
									<input type="email" class="form-control" id="emailInput-signUp" name="emailInput-signUp" placeholder="example@site.com" required/>
								</div>
								<div class="form-group">
									<label for="passwordInput-signUp">Choose a password</label>
									<input type="password" class="form-control" id="passwordInput-signUp" name="passwordInput-signUp" placeholder="password" required/>
								</div>
								<input type="hidden" name="uid" id="uid" value=""/>
								<button type="submit" class="btn btn-primary" value="get" onclick="signUp()">Sign up</button>
							</form>
						</div>
						<div class="modal-footer">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="defaultCheck1" onclick="signUp()">
								<label class="form-check-label" for="defaultCheck1">
									Confirmation
								</label>
							</div>
							<button type="submit" class="btn btn-secondary" data-dismiss="modal" onclick="clearForm('signUpForm')">Close</button>
						</div>
					</div>
				</div>
			</div>
			<!--signin modal-->
			<div id="signIn" class="modal" tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Sign in</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p>Welcome back!</p>
							<form action="views/profile.jsp" method="post" id="signInForm">
								<div class="form-group">
									<label for="emailInput-signIn">Your email address</label>
									<input type="email" class="form-control" id="emailInput-signIn" placeholder="example@site.com" required>
								</div>
								<div class="form-group">
									<label for="passwordInput-signIn">Your password</label>
									<input type="password" class="form-control" id="passwordInput-signIn" placeholder="password" required>
								</div>
								<input type="hidden" name="id" id="id" value=""/>
								<button type="submit" class="btn btn-primary" onclick="signIn()">Sign in</button>
							</form>
						</div>
						<div class="modal-footer">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value="" id="defaultCheck2" onclick="signIn()">
								<label class="form-check-label" for="defaultCheck2">
									Confirmation
								</label>
							</div>
							<button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="clearForm('signInForm')">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- The core Firebase JS SDK is always required and must be listed first -->
		<script src="https://www.gstatic.com/firebasejs/7.14.0/firebase-app.js"></script>

		<!-- Insert these scripts at the bottom of the HTML, but before you use any Firebase services -->


		<!-- If you enabled Analytics in your project, add the Firebase SDK for Analytics -->
		<script src="https://www.gstatic.com/firebasejs/7.14.0/firebase-analytics.js"></script>

		<!-- Add Firebase products that you want to use -->
		<script src="https://www.gstatic.com/firebasejs/7.14.0/firebase-auth.js"></script>
		<script src="https://www.gstatic.com/firebasejs/7.14.0/firebase-firestore.js"></script>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
		<script src="views/javascript/auth.js"></script>
	</body>
</html>