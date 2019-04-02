<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
.active-tab {
	background-color: #eee;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark">

	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#Home">Home</a></li>


		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> Subject </a>
			<ul class="dropdown-menu">
				<li><a href="#AddSubject" role="tab" data-toggle="tab">Add
						Subject</a></li>
				<li><a href="#VieworDeleteSubjects" role="tab"
					data-toggle="tab">View/Delete Subjects</a></li>
			</ul></li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">Question</a>
			<ul class="dropdown-menu" id="subjects-dropdown">
				<c:forEach var="sb" items="${applicationScope.subjectList}">
					<li><a href="#AddViewEditDeleteQuestion" role="tab"
						data-toggle="tab" id="${sb.subject_id}">${sb.name}</a></li>
				</c:forEach>
			</ul></li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">Test</a>
			<ul class="dropdown-menu">
				<li><a href="#AddTest" role="tab" data-toggle="tab">Create
						Test Paper</a></li>
				<li><a href="#VieworDeleteTests" role="tab" data-toggle="tab">View/Delete
						Tests</a></li>
			</ul></li>
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


	<form action="OESController" method="post">

		<h3>Create/Insert a Question</h3>

		<div class="form-group">
			<label for="question">Question:</label> <input type="text"
				class="form-control" id="question" placeholder="Enter the Question"
				name="question">
		</div>
		<div class="form-group">
			<label for="choice1">Choice 1:</label> <input type="text"
				class="form-control" id="choice1" placeholder="Choice 1"
				name="choice1">
		</div>
		<div class="form-group">
			<label for="choice2">Choice 2:</label> <input type="text"
				class="form-control" id="choice2" placeholder="Choice 2"
				name="choice2">
		</div>
		<div class="form-group">
			<label for="choice3">Choice 3:</label> <input type="text"
				class="form-control" id="choice3" placeholder="Choice 3"
				name="choice3">
		</div>
		<div class="form-group">
			<label for="choice4">Choice 4:</label> <input type="text"
				class="form-control" id="choice4" placeholder="Choice 4"
				name="choice4">
		</div>
		<div class="form-group">
			<label for="answer">Answer:</label> <input type="text"
				class="form-control" id="answer" placeholder="Answer" name="answer">
		</div>
		<div class="form-group">
			<label for="marks">Marks:</label> <input type="text"
				class="form-control" id="marks" placeholder="Marks for the Question"
				name="marks">
		</div>
		<div class="form-group">
			<label for="Subject">Select Subject:</label> <select
				class="form-control" id="subject_id" name="subject_id">
				<c:forEach var="sub" items="${applicationScope.subjectList}">
					<option value="${sub.subject_id}" id="subject_id">${sub.subject_id}
						- ${sub.name}</option>
				</c:forEach>
			</select>
		</div>
		<button type="submit" name="action" value="Add Question"
			class="btn btn-default">Add Question</button>
	</form>
</body>
</html>