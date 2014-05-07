<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% String json = (String) request.getAttribute("json");%>

<html>
  <head>
    <title>Bootstrap 101 Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        
  </head>
  <body>
   <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
		</div>
		<div class="span4">
			<h2>
				Error...
			</h2>
			<p>
				Invalid username and/or password... <%= json%>
			</p>
			<p>
				<a class="btn btn-large btn-primary" href="index.html">Back...</a>
			</p>
		</div>
		
		<div class="span4">
		</div>
	</div>
</div>
    <!-- /container -->
    <script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>