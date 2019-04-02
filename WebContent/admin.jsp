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

		<%-- <li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">Question</a>
			<ul class="dropdown-menu">
			<li><a href="#AddQuestion" role="tab" data-toggle="tab">Add
						Question</a></li>
			<li class="dropdown-submenu"><a class="dropdown-item dropdown-toggle" href="#">View/Delete Questions</a>
				<ul class="dropdown-menu">
              	
					<c:forEach var="sb" items="${subjectList}">
						<li><a class="dropdown-item" href="#AddViewEditDeleteQuestion" role="tab"
						data-toggle="tab" id="${sb.subject_id}">${sb.name}</a></li>
					</c:forEach>
				</ul>
			</li>
			</ul>
		</li> --%>
		
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> Question </a>
		<ul class="dropdown-menu">
		<li><a href="#AddQuestion" role="tab" data-toggle="tab">Add
						Question</a></li>
		<li class="dropdown dropdown-submenu"><a href="#" class="dropdown-toggle" data-toggle="dropdown" href="#">View/Delete Questions</a>
        						<ul class="dropdown-menu" id="subjects-dropdown">
                                    <c:forEach var="sb" items="${applicationScope.subjectList}">
					<li><a tabindex="-1" href="#AddViewEditDeleteQuestion" role="tab"
						data-toggle="tab" id="${sb.subject_id}">${sb.name}</a></li>
				</c:forEach>
                                                                      
								</ul>
								</li>
								</ul>
								</li>
		
		<%-- <li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">Question</a>
			<ul class="dropdown-menu" id="subjects-dropdown">
				<c:forEach var="sb" items="${applicationScope.subjectList}">
					<li><a href="#AddViewEditDeleteQuestion" role="tab"
						data-toggle="tab" id="${sb.subject_id}">${sb.name}</a></li>
				</c:forEach>
			</ul></li> --%>
			

		<li class="dropdown">
			<a class="dropdown-toggle"
			data-toggle="dropdown" href="#">Test</a>
			<ul class="dropdown-menu">
				<li><a href="#AddTest" role="tab" data-toggle="tab">Create
						Test Paper</a></li>
				<li><a href="#VieworDeleteTests" role="tab" data-toggle="tab">View/Delete
						Tests</a></li>
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
			<h3>Home</h3>
			<p>Here you add a Subject, a question to a Subject, create and
				Edit Test Papers and also delete Subjects, Questions and Papers.</p>
		</div>

		<div id="AddSubject" class="tab-pane fade">
			<h3>Add a Subject</h3>
			<form action="OESController" method="post">
				<div class="form-group">
					<label for="subject_id">Subject ID:</label> <input type="text"
						class="form-control" id="subject_id"
						placeholder="Enter Subject ID" name="subject_id" required>
				</div>
				<div class="form-group">
					<label for="name">Subject Name:</label> <input type="text"
						class="form-control" id="name" placeholder="Enter Subject Name"
						name="name" required>
				</div>
				<button type="submit" name="action" value="Add Subject"
					class="btn btn-default">Add Subject</button>
			</form>
		</div>

		<div id="AddQuestion" class="tab-pane fade">
			<h3>Insert a Question</h3>
			<form action="OESController" method="post">
				<div class="form-group">
					<label for="question">Question:</label> <input type="text"
						class="form-control" id="question"
						placeholder="Enter the Question" name="question" required>
				</div>
				<div class="form-group">
					<label for="choice1">Choice 1:</label> <input type="text"
						class="form-control" id="choice1" placeholder="Choice 1"
						name="choice1" required>
				</div>
				<div class="form-group">
					<label for="choice2">Choice 2:</label> <input type="text"
						class="form-control" id="choice2" placeholder="Choice 2"
						name="choice2" required>
				</div>
				<div class="form-group">
					<label for="choice3">Choice 3:</label> <input type="text"
						class="form-control" id="choice3" placeholder="Choice 3"
						name="choice3" required>
				</div>
				<div class="form-group">
					<label for="choice4">Choice 4:</label> <input type="text"
						class="form-control" id="choice4" placeholder="Choice 4"
						name="choice4" required>
				</div>
				<div class="form-group">
					<label for="answer">Answer:</label> <input type="text"
						class="form-control" id="answer" placeholder="Answer"
						name="answer" required>
				</div>
				<div class="form-group">
					<label for="marks">Marks:</label> <input type="text"
						class="form-control" id="marks"
						placeholder="Marks for the Question" name="marks" required>
				</div>
				<div class="form-group">
					<label for="subject_id">Select Subject:</label> <select
						class="form-control" id="subject_id" name="subject_id">
						<c:forEach var="sub" items="${applicationScope.subjectList}">
							<option value="${sub.subject_id}">${sub.subject_id}-
								${sub.name}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" name="action" value="Add Question"
					class="btn btn-default">Add Question</button>
			</form>
		</div>

		<div id="AddTest" class="tab-pane fade">
			<h3>Create a Test</h3>
			<form action="OESController" method="post">
				<div class="form-group">
					<label for="test_id">Test ID:</label> <input type="text"
						class="form-control" id="test_id" placeholder="Enter the Test ID"
						name="test_id" required>
				</div>
				<div class="form-group">
					<label for="name">Test Name:</label> <input type="text"
						class="form-control" id="name"
						placeholder="Enter the Name of the Test" name="name" required>
				</div>
				<div class="form-group">
					<label for="duration">Duration:</label> <input type="text"
						class="form-control" id="duration"
						placeholder="Duration for the Test" name="duration" required>
				</div>
				<div class="form-group">
					<label for="max_score">Maximum Score for the Test:</label> <input
						type="text" class="form-control" id="max_score"
						placeholder="Enter the Maximum Score" name="max_score" required>
				</div>
				<div class="form-group">
					<label for="subject_id">Select Subject:</label> <select
						class="form-control" id="subject_id" name="subject_id">
						<c:forEach var="sub" items="${applicationScope.subjectList}">
							<option value="${sub.subject_id}">${sub.subject_id}-
								${sub.name}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" name="action" value="Create Test"
					class="btn btn-default">Create Test</button>
			</form>
		</div>

		<div id="AddViewEditDeleteQuestion" class="tab-pane fade">
			<form action="OESController" method="post">
				<c:forEach var="qn" items="${applicationScope.questionList}">
				<div class="form-check">
					<label class="form-check-label qn-${qn.subject_id}"><input type="radio"
					class="form-check-input" name="question_id" value="${qn.question_id}" />${qn.question}</label>
				</div>
				<br>
				</c:forEach>
				<button type="submit" name="action" value="ViewQuestion"
					class="btn btn-default viewquestion">View Question</button>

				<button type="submit" name="action" value="Delete Question"
					class="btn btn-default">Delete Question</button>

				<button type="submit" name="action" value="GetQuestionID"
					class="btn btn-default">Edit Question</button>

				<button type="button" class="btn btn-primary"
					onclick="window.location='addquestion.jsp';">Add Question</button>

			</form>
		</div>

		<div id="VieworDeleteSubjects" class="tab-pane fade">
			<form action="OESController" method="post">
				<c:forEach var="s" items="${applicationScope.subjectList}">
					<div class="radio row-md-1">
						<label><input type="radio" name="subject_id"
							value="${s.subject_id}">${s.name}</label>
					</div>
				</c:forEach>

				<button type="submit" name="action" value="ViewSubject"
					class="btn btn-default">View Subject</button>

				<button type="submit" name="action" value="Delete Subject"
					class="btn btn-default">Delete Subject</button>

				<button type="submit" name="action" value="GetSubjectID"
					class="btn btn-default">Edit Subject</button>
			</form>
		</div>

		<div id="VieworDeleteTests" class="tab-pane fade">
			<form action="OESController" method="post">
				<c:forEach var="t" items="${applicationScope.testList}">
					<div class="radio row-md-1">
						<label><input type="radio" name="test_id"
							value="${t.test_id}">${t.name}</label>
					</div>
				</c:forEach>

				<button type="submit" name="action" value="ViewTest"
					class="btn btn-default">View Test</button>

				<button type="submit" name="action" value="Delete Test"
					class="btn btn-default">Delete Test</button>

				<button type="submit" name="action" value="GetTestID"
					class="btn btn-default">Edit Test</button>

				<button type="submit" name="action"
					value="AddQuestionstoTestbyTestID" class="btn btn-default">Add
					Questions</button>

				<button type="submit" name="action" value="ViewQuestionsfromTest"
					class="btn btn-default">View/Delete Questions</button>
			</form>
		</div>

		<div id="ViewQuestion" class="tab-pane fade">
			<form action="OESController" method="post">
				<c:forEach var="q" items="${question}">
					<div class="radio">
						<label><input type="radio" name="question_id"
							value="${q.question_id}">${q.question}</label>
					</div>
				</c:forEach>
			</form>
		</div>
	</div>
</body>
</html>