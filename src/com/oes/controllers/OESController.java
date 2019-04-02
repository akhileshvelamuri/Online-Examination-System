package com.oes.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oes.models.Candidate;
import com.oes.models.Question;
import com.oes.models.Subject;
import com.oes.models.Test;
import com.oes.models.TestQuestionMapping;
import com.oes.services.OESService;

/**
 * Servlet implementation class OESController
 */
@WebServlet("/OESController")
public class OESController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OESController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null)
		{
			processHomePage(request, response);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}	
	}

	public static void processHomePage(HttpServletRequest request, HttpServletResponse response) {
		List<Subject> subjectList = new OESService().getSubjects();
		System.out.println("Contr " + subjectList.size());
		ServletContext sc = request.getServletContext();
		sc.setAttribute("subjectList", subjectList);
		
		List<Question> questionList = new OESService().getQuestions();
		System.out.println("Contr " + questionList.size());
		ServletContext sc2 = request.getServletContext();
		sc2.setAttribute("questionList", questionList);
		
		List<Test> testList = new OESService().getTests();
		System.out.println("Contr " + testList.size());
		ServletContext sc3 = request.getServletContext();
		sc3.setAttribute("testList", testList);
		
		List<TestQuestionMapping> testquestionsList = new OESService().getQuestionsfromTests();
		System.out.println("Contr " + testquestionsList.size());
		ServletContext sc4 = request.getServletContext();
		sc4.setAttribute("testquestionsList", testquestionsList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(action.equalsIgnoreCase("Sign Up"))
		{
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String qualification = request.getParameter("qualification");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			int admin  = Integer.parseInt(request.getParameter("admin"));
			
			Candidate candidate = new Candidate();
			candidate.setFirst_name(first_name);
			candidate.setLast_name(last_name);
			candidate.setQualification(qualification);
			candidate.setEmail(email);
			candidate.setPassword(password);
			candidate.setAdmin(admin);
			
			int candidate_id = new OESService().registerCandidate(candidate);
			
			candidate.setCandidate_id(candidate_id);
			
			if(candidate_id > 0 && candidate.getAdmin() > 0)
			{
				HttpSession session = request.getSession();
				session.setAttribute("candidate" , candidate);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			}
			else if(candidate_id > 0)
			{
				HttpSession session = request.getSession();
				session.setAttribute("candidate" , candidate);
				processHomePage(request, response);
				request.getRequestDispatcher("/student.jsp").forward(request, response);
			}
			else
			{
				out.println("Candidate Registration unsuccessful. Please try again");
				processHomePage(request, response);
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("Candidate Login"))
		{
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Candidate candidate = new OESService().authenticateCandidate(email, password);
			
			if(candidate != null && candidate.getAdmin() == 0)
			{
				HttpSession session = request.getSession();
				session.setAttribute("candidate", candidate);
				processHomePage(request, response);
				request.getRequestDispatcher("/student.jsp").forward(request, response);
			}
			if(candidate != null && candidate.getAdmin() == 1)
			{
				out.println("<h2><b>You're an Admin. Please Login using Admin Login </b></h2>");
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
			if(candidate != null && candidate.getAdmin() !=0 && candidate.getAdmin() != 1)
			{
				out.println("<h2><b>No such User exists. Please Register</b></h2>");
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
			if(candidate == null)
			{
				out.println("<h2><b>Invalid Login Details. Please Try Again</b></h2>");
				processHomePage(request, response);
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("Admin Login"))
		{
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			Candidate candidate = new OESService().authenticateAdmin(email, password);
			
			if(candidate != null && candidate.getAdmin() == 1)
			{
				HttpSession session = request.getSession();
				session.setAttribute("candidate", candidate);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			}
			if(candidate != null && candidate.getAdmin() == 0)
			{
				out.println("<h2><b>You're a Student. Please Login using Candidate Login </b></h2>");
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
			if(candidate != null && candidate.getAdmin() == 1 && !(candidate.getPassword().equals(password)))
			{
				out.println("<h2><b>Incorrect Password. Please Try Again </b></h2>");
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
			if(candidate != null && candidate.getAdmin() !=0 && candidate.getAdmin() != 1)
			{
				request.setAttribute("err", "Invalid credentials");
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
			if(candidate == null)
			{
				out.println("<h2><b>Invalid Login Details. Please Try Again</b></h2>");
				request.getRequestDispatcher("/index.jsp").include(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("Add Subject"))
		{
			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			String name = request.getParameter("name");
			
			Subject subject = new Subject();
			subject.setSubject_id(subject_id);
			subject.setName(name);
			
			System.out.println(" Contrlr Pre Add Subject " + subject_id);

			
			int sub_id = new OESService().addSubject(subject);
			
			System.out.println(" Contrlr Post Add Subject " + sub_id);
			
			if(sub_id > 0)
			{
				out.println(sub_id + " Successfully inserted");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			
			}
			
			else
			{
				out.println(sub_id + "Insertion UNSUCCESSFUL");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("Add Question"))
		{
			String question = request.getParameter("question");
			String choice1 = request.getParameter("choice1");
			String choice2 = request.getParameter("choice2");
			String choice3 = request.getParameter("choice3");
			String choice4 = request.getParameter("choice4");
			String answer = request.getParameter("answer");
			int marks = Integer.parseInt(request.getParameter("marks"));
			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			
			Question qn = new Question();
			qn.setQuestion(question);
			qn.setChoice1(choice1);
			qn.setChoice2(choice2);
			qn.setChoice3(choice3);
			qn.setChoice4(choice4);
			qn.setAnswer(answer);
			qn.setMarks(marks);
			qn.setSubject_id(subject_id);
			
			
			int qn_id = new OESService().addQuestion(qn);
			
			if(qn_id > 0)
			{
				out.println(qn_id + " Successfully inserted");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			
			}
			
			else
			{
				out.println(qn_id + "Insertion UNSUCCESSFUL");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("Create Test"))
		{
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			String name = request.getParameter("name");
			Time duration = Time.valueOf(request.getParameter("duration"));
			int max_score = Integer.parseInt(request.getParameter("max_score"));
			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			
			Test test = new Test();
			test.setTest_id(test_id);
			test.setName(name);
			test.setDuration(duration);
			test.setMax_score(max_score);
			test.setSubject_id(subject_id);
			
			int tst_id = new OESService().createTest(test);
			
			if(tst_id > 0)
			{
				out.println(tst_id + " Successfully created");
				processHomePage(request, response);
				request.setAttribute("test", test);
				request.getRequestDispatcher("/testaddquestions.jsp").include(request, response);
			}
			
			else
			{
				out.println(tst_id + "Creation UNSUCCESSFUL");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
		}

		if(action.equalsIgnoreCase("AddQuestionstoTest"))
		{
			System.out.println("Enteredin AddQuestionstoTest controller");
			
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			String[] question_ids =request.getParameterValues("question_id");
			
			List<TestQuestionMapping> questionList = new ArrayList<TestQuestionMapping>();
			
			
			for(String id : question_ids){
				TestQuestionMapping qn = new TestQuestionMapping();
				qn.setQuestion_id(Integer.parseInt(id));
				qn.setTest_id(test_id);
				questionList.add(qn);
			}
			int result = new OESService().addQuestionstoList(questionList);
			
			if(result > 0)
			{
				out.println("Questions successfully INSERTED");
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
			else
			{
				out.println("No Questions INSERTED");
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("AddQuestionstoTestbyTestID"))
		{
			System.out.println("Enteredin AddQuestionstoTestbyTestID controller");
			
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			
			Test test = new OESService().getTest(test_id);
			
			processHomePage(request, response);
			request.setAttribute("test", test);
			request.getRequestDispatcher("/testaddquestions.jsp").include(request, response);
		}
		
		if(action.equalsIgnoreCase("ViewQuestionsfromTest"))
		{
			System.out.println("Enteredin ViewQuestionsfromTest controller");
			
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			
			//List<TestQuestionMapping> testquestions = new OESService().getQuestionsfromTest(test_id);
			Test test = new OESService().getTest(test_id);
			
			processHomePage(request,response);
			request.setAttribute("test", test);
			request.getRequestDispatcher("/viewdeletequestionsfromtest.jsp").include(request, response);
		}
		
		if(action.equalsIgnoreCase("DeleteQuestionfromTest"))
		{
			System.out.println("Enteredin DeleteQuestionfromTest controller");
			
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			int question_id = Integer.parseInt(request.getParameter("question_id"));
			
			TestQuestionMapping tqm = new TestQuestionMapping();
			tqm.setTest_id(test_id);
			tqm.setQuestion_id(question_id);
			
			boolean result = new OESService().deleteQuestionsfromTest(test_id, question_id);
			
			if(result == true)
			{
				out.println(test_id + question_id + " Successfully deleted");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);	
			}
			
			else
			{
				out.println("Failed to delete " + test_id + question_id);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
			
			
		}
		
		if(action.equalsIgnoreCase("VieworDeleteSubjects"))
		{
			System.out.println("Enteredin controller");
			List<Subject> subject = new OESService().getSubjects();
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		
		if(action.equalsIgnoreCase("GetSubjectID"))
		{
			System.out.println("Enteredin controller 2");
			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			Subject subject = new OESService().getSubject(subject_id);
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/editsubject.jsp").forward(request, response);
		}
		
		if(action.equalsIgnoreCase("GetQuestions"))
		{
			System.out.println("Enteredin controller 2");
			List<Question> question = new OESService().getQuestions();
			request.setAttribute("question", question);
			request.getRequestDispatcher("/editquestion.jsp").forward(request, response);
		}
		
		if(action.equalsIgnoreCase("GetQuestionID"))
		{
			System.out.println("Enteredin controller 2");
			int question_id = Integer.parseInt(request.getParameter("question_id"));
			Question question = new OESService().getQuestion(question_id);
			request.setAttribute("question", question);
			request.getRequestDispatcher("/editquestion.jsp").forward(request, response);
		}
		
		if(action.equalsIgnoreCase("VieworDeleteTests"))
		{
			System.out.println("Enteredin controller 3");
			List<Test> test = new OESService().getTests();
			request.setAttribute("test", test);
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		
		if(action.equalsIgnoreCase("GetTestID"))
		{
			System.out.println("Enteredin controller 2");
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			Test test = new OESService().getTest(test_id);
			request.setAttribute("test", test);
			request.getRequestDispatcher("/edittest.jsp").forward(request, response);
		}
		
		
		if(action.equalsIgnoreCase("EditSubject"))
		{
			System.out.println("Entered in edit subject");

			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			String name = request.getParameter("name");
			
			Subject sub = new Subject();
			sub.setSubject_id(subject_id);
			sub.setName(name);
			
			int sub_id = new OESService().editSubject(sub);
			
			if(sub_id > 0)
			{
				out.println(sub_id + " has been Edited Successfully");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
			else
			{
				out.println(sub_id + " EDIT Unsuccessful");
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("EditQuestion"))
		{
			System.out.println("Entered in edit question");
			
			int question_id = Integer.parseInt(request.getParameter("question_id"));
			String question = request.getParameter("question");
			String choice1 = request.getParameter("choice1");
			String choice2 = request.getParameter("choice2");
			String choice3 = request.getParameter("choice3");
			String choice4 = request.getParameter("choice4");
			String answer = request.getParameter("answer");
			int marks = Integer.parseInt(request.getParameter("marks"));
			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			
			
			Question qn = new Question();
			qn.setQuestion_id(question_id);
			qn.setQuestion(question);
			qn.setChoice1(choice1);
			qn.setChoice2(choice2);
			qn.setChoice3(choice3);
			qn.setChoice4(choice4);
			qn.setAnswer(answer);
			qn.setMarks(marks);
			qn.setSubject_id(subject_id);
			
			int qn_id = new OESService().editQuestion(qn);
			
			if(qn_id > 0)
			{
				out.println(qn_id + " has been Edited Successfully");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			}
			else
			{
				out.println(qn_id + " EDIT Unsuccessful");
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			}
			
		}
		

		
		if(action.equalsIgnoreCase("EditTest"))
		{
			System.out.println("Enteredin controller EditTest");
			
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			String name = request.getParameter("name");
			Time duration = Time.valueOf(request.getParameter("duration"));
			int max_score = Integer.parseInt(request.getParameter("max_score"));
			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			
			Test test = new Test();
			test.setTest_id(test_id);
			test.setName(name);
			test.setDuration(duration);
			test.setMax_score(max_score);
			test.setSubject_id(subject_id);
			
			int tst_id = new OESService().editTest(test);
			
			if(tst_id > 0)
			{
				out.println(test_id + " has been Edited Successfully");
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			}
			else
			{
				out.println(test_id + " EDIT Unsuccessful");
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("Delete Subject"))
		{
			int subject_id = Integer.parseInt((String)request.getParameter("subject_id"));
			
			Subject sub = new Subject();
			sub.setSubject_id(subject_id);
			
			boolean result = new OESService().deleteSubject(subject_id);
			
			if(result == true)
			{
				out.println(subject_id + " Successfully deleted");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			
			}
			
			else
			{
				out.println("Failed to delete " + subject_id);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
		}

		if(action.equalsIgnoreCase("ViewSubject"))
		{
			int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			
			System.out.println("Controller Subject ID:" + subject_id);
			
			Subject sub = new OESService().getSubject(subject_id);
			
			System.out.println("Cntrl Post Get Subj " + sub.getSubject_id() + " " + sub.getName());
			
			if(sub != null)
			{
				processHomePage(request, response);
				request.setAttribute("sub", sub);
				request.getRequestDispatcher("/viewsubject.jsp").forward(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("ViewQuestion"))
		{

			int question_id = Integer.parseInt(request.getParameter("question_id"));
			
			System.out.println("Controller Question ID:" + question_id);

			Question quesn = new OESService().getQuestion(question_id);
			
			if(quesn != null)
			{
				processHomePage(request, response);
				request.setAttribute("quesn", quesn);
				request.getRequestDispatcher("/viewquestion.jsp").forward(request, response);
			}
			
			else
			{
				out.println("Unable to view " + question_id);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("ViewTest"))
		{
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			
			System.out.println("Controller Test ID:" + test_id);
			
			Test tst = new OESService().getTest(test_id);
			
			if(tst != null)
			{
				processHomePage(request, response);
				request.setAttribute("tst", tst);
				request.getRequestDispatcher("/viewtest.jsp").forward(request, response);
			}
			
			else
			{
				out.println("Unable to view " + test_id);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("Delete Question"))
		{
			int question_id = Integer.parseInt((String)request.getParameter("question_id"));
			
			Question qn = new Question();
			qn.setQuestion_id(question_id);
			
			boolean result = new OESService().deleteQuestion(question_id);
			
			if(result == true)
			{
				out.println(question_id + " Successfully deleted");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);	
			}
			
			else
			{
				out.println("Failed to delete " + question_id);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
		}
		
		
		if(action.equalsIgnoreCase("Delete Test"))
		{
			int test_id = Integer.parseInt((String)request.getParameter("test_id"));
			
			Test test = new Test();
			test.setTest_id(test_id);
			
			boolean result = new OESService().deleteTest(test_id);
			
			if(result == true)
			{
				out.println(test_id + " Successfully deleted");
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
			
			else
			{
				out.println("Failed to delete " + test_id);
				processHomePage(request, response);
				request.getRequestDispatcher("/admin.jsp").include(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("GetScoreOfTest"))
		{
			int test_id = Integer.parseInt((String)request.getParameter("test_id"));
			int question_id = Integer.parseInt(request.getParameter("question_id"));
			String question = request.getParameter("question");
			String choice1 = request.getParameter("choice1");
			String choice2 = request.getParameter("choice2");
			String choice3 = request.getParameter("choice3");
			String choice4 = request.getParameter("choice4");
		//	int subject_id = Integer.parseInt(request.getParameter("subject_id"));
			
			List<TestQuestionMapping> questions =  new OESService().getQuestionsfromTest(test_id);
			int userScore = 0;
			int noofcorrectqns = 0;
			
			for(TestQuestionMapping ques : questions){
				Question questionObj = new OESService().getQuestion(ques.getQuestion_id());
				String userAnswer = request.getParameter("choice-"+ques.getQuestion_id());
				if(userAnswer.contentEquals(questionObj.getAnswer())) {
					userScore += questionObj.getMarks();
					noofcorrectqns += 1;
				}
			}
			System.out.println(userScore);
	//		int score = new OESService().getScoreOfTest(question_id, choice1, choice2, choice3, choice4, test_id, subject_id);
			
			Test test = new Test();
			int max_score = new OESService().getMaxScoreOfTest(test_id);
			int incorrect_qns =  questions.size() - noofcorrectqns;
			request.setAttribute("total", questions.size());
			request.setAttribute("correct", noofcorrectqns);
			request.setAttribute("incorrect", incorrect_qns);
			request.getRequestDispatcher("/testscore.jsp").include(request, response);
			
		}
	}
}