<%@ page import="teamsoftware.student" %>
<%@ page import="teamsoftware.schedule" %>
<!doctype html>
<html lang="en">
	<head>
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<!--firebase-->
		<script src="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth.js"></script>
		<link type="text/css" rel="stylesheet" href="https://www.gstatic.com/firebasejs/ui/4.5.0/firebase-ui-auth.css" />

		<title>profile</title>
		<style>
			.thumb{background-color: #ddd;}
			html, body{
				height: 100%;
			}
			.bbbbb{
				background: url(https://cdn.pixabay.com/photo/2016/01/05/11/20/pattern-1122314_960_720.jpg) no-repeat center center; 
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
	<body class="bbbbb" style="height: 100%;">
	<%
		student student = new student();
		//sign up
		//String userID = request.getParameter("uid");
		String userID = (String) session.getAttribute("userid");
		System.out.println(userID);
		System.out.println(request.getParameter("uid"));
		//this one is from sign in
		String ID = request.getParameter("id");
		System.out.println(ID);
		//sign up
		String name = request.getParameter("studentName");
		System.out.println(name);
		//sign up
		String email = request.getParameter("emailInput-signUp");
		System.out.println(email);
		String[] info = {"0","1","2"};
		if(ID != null){
			info = student.SQLinitStudent(ID);
		}
		if(userID != null && name != null && email != null){
			try{
				student.SQLaddStudent(userID,name,email);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if(userID != null){
			info = student.SQLinitStudent(userID);
		}
		String sName = info[1];
		String sEmail = info[2];

	%>
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-8 thumb">
					<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
					  <button class="navbar-toggler navbar-toggler-right collapsed" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					  </button>
					  <a class="navbar-brand" href="#!">Navbar</a>

					  <div class="navbar-collapse collapse" id="navbarTogglerDemo02" style="">
						<ul class="navbar-nav mr-auto mt-2 mt-md-0">
						  <li class="nav-item active">
							<a class="nav-link" onclick="location.href='/settings'">Settings<span class="sr-only">(current)</span></a>
						  </li>
							<li class="nav-item active">
								<a class="nav-link" onclick="location.href='/schedule'">Schedule<span class="sr-only">(current)</span></a>
							</li>
						  <li class="nav-item">
							  <a class="nav-link" onclick="location.href='/index'" onclick="signOut()">logout<span class="sr-only">(current)</span></a>
						  </li>
						</ul>
					  </div>
					</nav>
				</div>
				<div class="col-lg-2"></div>
			</div>
			
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-8 thumb">
					<div class="jumbotron rounded">
						<div class="row">
							<div class="col-lg-2">

							</div>
							<div class="col-lg-3">
								<h1 style="margin-left: 40px;" id="name"><%= sName%></h1>
								<h3 style="margin-left: 40px;" id="email"><%= sEmail%></h3>
							</div>
							<div class="col-lg-2"></div>
							<div class="col-lg-1">
								<button type="button" class="btn btn-outline-secondary" style="margin-left: 40px;">Friend Request</button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-2"></div>
			</div>
			
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-2 thumb">
					<h3 style="text-align:center;">Study Groups</h3>
					<div class="list-group">
						<a href="#!" class="list-group-item list-group-item-action">study group</a>
						<a href="#!" class="list-group-item list-group-item-action">study group</a>
						<a href="#!" class="list-group-item list-group-item-action">study group</a>
					</div>
				</div>
				<div class="col-lg-6 thumb">
				<h3 style="text-align:center;">Friends</h3>
					<div class="list-group">
						<a href="#!" class="list-group-item list-group-item-action">friends</a>
						<a href="#!" class="list-group-item list-group-item-action">friends</a>
						<a href="#!" class="list-group-item list-group-item-action">friends</a>
					</div>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-2 thumb">
				<h3 style="text-align:center;">Classrooms</h3>
					<div class="list-group">
						<a href="#!" class="list-group-item list-group-item-action">class</a>
						<a href="#!" class="list-group-item list-group-item-action">class</a>
						<a href="#!" class="list-group-item list-group-item-action">class</a>
					</div>
				</div>
				<div class="col-lg-6 thumb">
				<h3 style="text-align:center;">Schedule</h3>
					<div class="list-group">
						<%
							schedule sched = new schedule();
							sched.SQLintitschedule(userID);
							for(int i = 0; i<sched.listOfListOfPlaces.size();i++){
								out.println("<a class='list-group-item list-group-item-action'>" + sched.listOfListOfPlaces.get(i).getName() +" "+ sched.listOfListOfPlaces.get(i).getLocation() +" Start Time: "+ sched.listOfListOfPlaces.get(i).getTime()+"</a>");
							}
						%>
					</div>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-1 thumb"></div>
				<div class="col-lg-6 thumb">
					<h2 style="text-align:center;">Path</h2>
					<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						</ol>
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active">
								<img class="d-block w-100" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22900%22%20height%3D%22400%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20900%20400%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_170c5573fe3%20text%20%7B%20fill%3A%23FFFFFF%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A45pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_170c5573fe3%22%3E%3Crect%20width%3D%22900%22%20height%3D%22400%22%20fill%3D%22%233a5a97%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22333.203125%22%20y%3D%22220.1%22%3E900x400%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-src="holder.js/900x400?theme=social" alt="900x400" data-holder-rendered="true" style="width: 900px; height: 400px;">
							</div>
							<div class="carousel-item">
								<img class="d-block w-100" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22900%22%20height%3D%22400%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20900%20400%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_170c5574006%20text%20%7B%20fill%3A%23C2F200%3Bfont-weight%3Abold%3Bfont-family%3AArial%2C%20Helvetica%2C%20Open%20Sans%2C%20sans-serif%2C%20monospace%3Bfont-size%3A45pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_170c5574006%22%3E%3Crect%20width%3D%22900%22%20height%3D%22400%22%20fill%3D%22%23434A52%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22333.203125%22%20y%3D%22220.1%22%3E900x400%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" data-src="holder.js/900x400?theme=industrial" alt="900x400" data-holder-rendered="true" style="width: 900px; height: 400px;">
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
				<div class="col-lg-1 thumb"></div>
				<div class="col-lg-2"></div>
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
		<script src="javascript/auth.js"></script>
	</body>
</html>