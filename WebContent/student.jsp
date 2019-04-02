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
	
	position: fixed;
  top: 0;
  width: 100%;
}
.pad
{
padding: 5px;
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

	<!--  Tab Panes -->

	<div class="tab-content">
		<div id="Home" class="tab-pane fade in active">
			<i>Welcome to the <b>Online Examination System</b></i>

		</div>



		<div id="AttemptTest" class="tab-pane fade">
			<p id="timerclock"></p>

			<div class="form-group">
				<form action="OESController" method="post">
					
						
				<!-- <label for="subject_id">Subject:</label> <input type="text"
					class="form-control" id="subject_id" placeholder="Subject ID"
						name="subject_id" value="${tst.subject_id}" disabled> -->
						
				<c:forEach var="tst" items="${testList}">	
					<c:forEach var="tqm" items="${testquestionsList}">
						<c:forEach var="qn" items="${questionList}">
							<c:if test="${tqm.test_id eq tst.test_id}">
								<c:if test="${tqm.question_id eq qn.question_id}">
									
									<input type="hidden" name="question_id" value="${qn.question_id}" />
									<div class="form-group">
										<label for="question">Question:</label> <input type="text"
											class="form-control" id="question" name="question" value="${qn.question}" disabled>
									</div>
									<div id="pad">
									<div class="form-group">
										<label for="choice1" class="radio-inline">${qn.choice1} <input type="radio"
											class="radio" id="choice1" name="choice-${qn.question_id}" value="${qn.choice1}"></label>
									</div>
									<div class="form-group">
										<label for="choice2" class="radio-inline">${qn.choice2} <input type="radio"
											class="radio" id="choice2" name="choice-${qn.question_id}" value="${qn.choice2}"></label>
									</div>
									<div class="form-group">
										<label for="choice3" class="radio-inline">${qn.choice3}<input type="radio"
											class="radio" id="choice3" name="choice-${qn.question_id}" value="${qn.choice3}"></label> 
									</div>
									<div class="form-group">
										<label for="choice4" class="radio-inline">${qn.choice4} <input type="radio"
											class="radio" id="choice4" name="choice-${qn.question_id}" value="${qn.choice4}"></label>
									</div>
									</div>
								</c:if>
							</c:if>
							<input type="hidden" name="test_id" value="${tqm.test_id}" />
						</c:forEach>
					</c:forEach>
				</c:forEach>

					<button type="submit" name="action" value="GetScoreOfTest"
						class="btn btn-default">Submit Answers</button>
				</form>
			</div>


		</div>
	</div>

	<script>
	</script>

</body>
<style>
.time-reaming {
    display: inline-block;
    padding: 5px;
    /* color: #fff; */
    float: right;
    font-size: 20px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	var x;
	
	$('li').click(function(){
	//clearInterval(x);
	var originalduration = $(this).attr("data");
	var duration = originalduration.split(":");
	var seconds = (+duration[0]) * 60 * 60 + (+duration[1]) * 60 + (+duration[2]);
	//Set the date we're counting down to
	var countDownDate = new Date().getTime() + (seconds * 1000);

	// Update the count down every 1 second
	 x = setInterval(function()
	{

	// Get todays date and time
	var now = new Date().getTime();
    
	// Find the distance between now and the count down date
	var distance =  countDownDate - now;
    
	// Time calculations for minutes and seconds
  	var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  	var seconds = Math.floor((distance % (1000 * 60)) / 1000);
  	if (minutes < 10) {minutes = "0" + minutes};  // add zero in front of numbers < 10
  	if (seconds < 10) {seconds = "0" + seconds};  // add zero in front of numbers < 10
  	
  	// Output the result in an element with id="demo"
  	document.getElementById("timerclock").innerHTML = minutes + ":" + seconds;
    
  	// If the count down is over, write some text 
  	if (distance < 0)
  	{
    	clearInterval(x);
    	document.getElementById("timerclock").innerHTML = "EXPIRED";
    	window.location.replace("testscore.jsp");
  	}
	
	}, 1000);

	 
    });
	
});
</script>

</html>