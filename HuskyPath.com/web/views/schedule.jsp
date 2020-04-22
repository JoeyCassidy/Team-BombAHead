<%--
  Created by IntelliJ IDEA.
  User: joeyb
  Date: 4/6/2020
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

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
                            <a class="nav-link" onclick="location.href='/profile'">Profile<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#!"></a>
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
            <form method="post">
            <div class="form-group row">
                <label for="class"> Class Name</label>
                <input type="text" class="form-control" name="class" id="class">
            </div>
                <div class="form-group row">
                    <label for="starttime"> Start Time</label>
                    <input type="datetime-local" class="form-control" name="starttime" id="starttime">
                    <label for="endtime"> End Time</label>
                    <input type="datetime-local" class="form-control" name="endtime" id="endtime">
                </div>
            <div class="form-check form-check-inline">
            <label class="form-check-label">
                <input class="form-check-input" name="days[]" type="checkbox" value="monday"> Monday
            </label>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" name="days[]" type="checkbox" value="tuesday" > Tuesday
                </label>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" name="days[]" type="checkbox" value="wednesday" > Wednesday
                </label>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" name="days[]" type="checkbox" value="thursday" > Thursday
                </label>
            </div>
            <div class="form-check form-check-inline">
                <label class="form-check-label">
                    <input class="form-check-input" name="days[]" type="checkbox" value="friday" > Friday
                </label>
            </div>

            <div class="form-group row">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
            </form>
            </div>
        </div>
        <div class="col-lg-2"></div>
    </div>


</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>