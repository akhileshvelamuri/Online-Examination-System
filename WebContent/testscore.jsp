<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<style>
p {
	text-align: right;
	font-size: 40px;
	background-color: blue;
	margin-top: 0px;
}
</style>

</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark">
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#Home">Home</a></li>

		<li class="nav-item dropdown">
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown">Attempt an Exam</button>
			<ul class="dropdown-menu">
				<c:forEach items="${testList}" var="test">
					<li data="${test.duration }"><a href="#AttemptTest" data-toggle="tab"> ${test.name}</a></li>
					<br />
				</c:forEach>
			</ul>
		</li>
	</ul>

	<ul class="navbar-nav ml-auto">
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> Welcome
				${sessionScope.candidate.first_name}</a>
			<div class="dropdown-menu">

				<a class="dropdown-item" href="OESController">Sign Out</a>

			</div></li>
	</ul>
	</nav>
	<br>

<h1>My Score</h1>

<div id="piechart"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Maximum Score', '${requestScope.total}'],
  ['Correct Answers', '${requestScope.correct}'],
  ['Incorrect Answers', '${requestScope.incorrect}']
]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Score Visualization', 'width':550, 'height':400, pieStartAngle: '${requestScope.total}'};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>

</body>
</html>
