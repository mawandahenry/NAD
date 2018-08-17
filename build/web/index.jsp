<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Mobile Money</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	<script src="jquery-1.7.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	
</head>
<body>

<script type="text/javascript">
$(document).ready(function(){
$("#tag3").click(function(){
 $(".content").hide("slow", function(){
  $("#about").fadeToggle(1000).animate({color:"blue"});
 });
});
$("#tag4").click(function(){
$(".content").hide("slow",function(){
    
    $("#loginx").fadeToggle("slow",function(){
        $("#tag3").hide();
    });
});	
});
	  
$("button").click(function(){
$("#about").hide("slow", function(){
	$(".content").show("slow",function(){
		
	});
});	
});
$("#home").click(function(){
    $(".content").show("slow",function(){
        $("#loginx").hide("slow",function(){
            $("#about").hide();
        });
    });
});
 });

</script>
<div class="container" >
<div id="tag1">
	<h1> MOBILE MONEY UGANDA</h1>
</div>

<div id="about">
<h3 style="text-align:center; margin-top: 50px;">ABOUT THE SYSTEM</h3>
	<p>Mobile Money system lets you transfer and receive money In order to use the service, you should have a </br>well registered 
	simcard with one of the service providers. the system has two panels one being an </br>interface just for the administrator .this one is mainly for adding new users and checking the progress of their work
	the registered users then use the commandline interface to carry out transactions .</p>
<button class="btn btn-primary">backHome</button>
</div>

<div id="loginx">
    <form action="doc.jsp" method="post"  style="margin-left: 40px; margin-top: 70px;">
    <h2 style="text-align: center">Admin Login Panel</h2>
<table>
<tr><td>Username</td><td><input type="text" name="username"></td></tr>
<tr><td>Password</td><td><input type="password" name="password"></td></tr>
<tr><td></td><td><input type="submit" value="Login" class="btn btn-primary"></td></tr>

</table>
</form>

    <table style="margin-left: 100px;">
        <tr><td></td><td><input type="submit" value="backHome" class="btn btn-primary" id="home"></td></tr>
    </table>
</div>

<div class="content"><!-- this is my main nav sort of-->
	   
		<a href="#/profile" id="tag3"><i class="fa fa-list"></i>About</a>
		<a href="#/friends" id="tag4" data-toggle="modal" data-target="#myModal"><i class="fa fa-user"></i>Admin</a>

<div>

</div>
<footer>
	<div>&copy Naco,Dam,Phib & immy Production 2017</a></div>
</footer>
<script type="text/javascript" src="bootstrap.min.js"></script>
</div>
</div>

</body>
</html>