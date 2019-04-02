package com.oes.services;

import java.util.List;

import com.oes.daoimpls.CandidateDaoImpl;
import com.oes.daoimpls.QuestionDaoImpl;
import com.oes.daoimpls.SubjectDaoImpl;
import com.oes.daoimpls.TestDaoImpl;
import com.oes.daoimpls.TestQuestionMappingDaoImpl;
import com.oes.daointerfaces.CandidateDao;
import com.oes.daointerfaces.QuestionDao;
import com.oes.daointerfaces.SubjectDao;
import com.oes.daointerfaces.TestDao;
import com.oes.daointerfaces.TestQuestionMappingDao;
import com.oes.models.Candidate;
import com.oes.models.Question;
import com.oes.models.Subject;
import com.oes.models.Test;
import com.oes.models.TestQuestionMapping;


public class OESService {

	public int registerCandidate(Candidate candidate) {
		CandidateDao dao = new CandidateDaoImpl();
		int candidate_id = dao.insertCandidate(candidate);
		
		System.out.println("Service " + candidate_id);
		
		return candidate_id;
	}

	public Candidate authenticateCandidate(String email, String password) {
		CandidateDao dao = new CandidateDaoImpl();
		Candidate candidate = dao.getCandidate(email);
		
		System.out.println("2 " + candidate.getFirst_name());
		
		if(candidate != null && candidate.getEmail().equals(email) && candidate.getPassword().equals(password))
		{
			return candidate;
		}
		else
		{
			return null;
		}
	}
	
	public Candidate authenticateAdmin(String email, String password) {
		CandidateDao dao = new CandidateDaoImpl();
		Candidate candidate = dao.getCandidate(email);
		
		System.out.println("2 " + candidate.getFirst_name());
		
		if(candidate != null && candidate.getEmail().equals(email) && candidate.getPassword().equals(password))
		{
			return candidate;
		}
		else
		{
			return null;
		}
	}
	
	public int addSubject(Subject subject)
	{
		SubjectDao dao = new SubjectDaoImpl();
		int subject_id = dao.addSubject(subject);
		
		System.out.println("Serv Subject ID: " + subject.getSubject_id());
		
		if(subject_id > 0)
			return subject_id;
		else
			return 0;
	}
	
	public int addQuestion(Question question)
	{
		QuestionDao dao = new QuestionDaoImpl();
		int question_id = dao.insertQuestion(question);
		
		System.out.println("Serv Question ID: " + question.getQuestion_id());
		
		if(question_id > 0)
			return question_id;
		else
			return 0;
	}

	public int createTest(Test test) {
		TestDao dao = new TestDaoImpl();
		int test_id = dao.createTest(test);
		
		System.out.println("Serv Test ID: " + test.getTest_id());
		
		if(test_id > 0)
			return test_id;
		else
			return 0;
	}

	public List<Subject> getSubjects() {
		System.out.println("Entered Service");
		SubjectDao dao = new SubjectDaoImpl();
		List<Subject> subjectList = dao.getSubjectList();
		System.out.println("Serv SubjectList Size:" + subjectList.size());

		return subjectList;
	}

	public List<Question> getQuestions() {
		System.out.println("Entered Service");
		QuestionDao dao = new QuestionDaoImpl();
		List<Question> questionList = dao.getQuestionList();
		System.out.println("Serv QuestionList Size:" + questionList.size());

		return questionList;
	}

	public List<Test> getTests() {
		TestDao dao = new TestDaoImpl();
		List<Test> testList = dao.getTestList();
		System.out.println("Serv TestList Size:" + testList.size());

		return testList;
	}
	
	public List<TestQuestionMapping> getQuestionsfromTests() {
		TestQuestionMappingDao dao = new TestQuestionMappingDaoImpl();
		List<TestQuestionMapping> testquestionsList = dao.getQuestionsfromTests();
		System.out.println("Serv Test Questions List Size:" + testquestionsList.size());

		return testquestionsList;
	}

	public int editSubject(Subject sub) {
		SubjectDao dao = new SubjectDaoImpl();
		int sub_id = dao.editSubject(sub);
		
		System.out.println("Serv Subject ID:" + sub_id);
		
		if(sub_id > 0)
			return sub_id;
		else
			return 0;
	}
	
	public boolean deleteSubject(int subject_id) {
		SubjectDao dao = new SubjectDaoImpl();
		boolean result = dao.deleteSubject(subject_id);
		
		Subject subject = new Subject();
		System.out.println("Serv Subject ID: " + subject.getSubject_id());

		return result;
	}
	
	public int editQuestion(Question qn) {
		QuestionDao dao = new QuestionDaoImpl();
		
		int qn_id = dao.editQuestion(qn);
		
		System.out.println("Serv Question ID: " + qn_id);
		
		if(qn_id > 0)
			return qn_id;
		else
			return 0;
	}

	public boolean deleteQuestion(int question_id) {
		QuestionDao dao = new QuestionDaoImpl();
		boolean result = dao.deleteQuestion(question_id);
		
		Question question = new Question();
		System.out.println("Serv Question ID: " + question.getQuestion_id());

		return result;
	}
	
	public int editTest(Test test) {
		TestDao dao = new TestDaoImpl();
		int tst_id = dao.editTest(test);
		
		System.out.println("Serv Test ID:" + test.getTest_id());
		
		if(tst_id > 0)
			return tst_id;
		else
			return 0;
	}
	
	public boolean deleteTest(int test_id) {
		TestDao dao = new TestDaoImpl();
		boolean result = dao.deleteTest(test_id);
		
		Test test = new Test();
		System.out.println("Serv Test ID: " + test.getTest_id());

		return result;
	}

	public Question getQuestion(int question_id) {
		
		System.out.println("Entered Service");
		QuestionDao dao = new QuestionDaoImpl();
		Question question = dao.getQuestion(question_id);
		System.out.println("Serv Question ID:" + question.getQuestion_id());
		
		return question;
	}

	public Subject getSubject(int subject_id) {
		
		System.out.println("Entered getSubject() Service");
		SubjectDao dao = new SubjectDaoImpl();
		Subject subject = dao.getSubject(subject_id);
		System.out.println("Serv Subject ID:" + subject.getSubject_id());
		
		return subject;
	}

	public Test getTest(int test_id) {
		
		System.out.println("Entered getTest() Service");
		TestDao dao = new TestDaoImpl();
		Test test = dao.getTest(test_id);
		System.out.println("Serv Test ID:" + test.getTest_id());
		
		return test;
	}

	public List<Question> getQuestions(int subject_id) {
		
		System.out.println("Entered Service");
		QuestionDao dao = new QuestionDaoImpl();
		List<Question> questionList =  dao.getQuestionList(subject_id);
		System.out.println("Serv Question List:" + questionList.size());
		
		return questionList;
	}

	public int addQuestionstoList(List<TestQuestionMapping> questionList) {
		TestQuestionMappingDao dao = new TestQuestionMappingDaoImpl();
		int result = dao.addQuestionstoList(questionList);
		return result;
	}

	public int addQuestionstoListbyTestID(List<TestQuestionMapping> questionList, int test_id) {
		TestQuestionMappingDao dao = new TestQuestionMappingDaoImpl();
		int result = dao.addQuestionstoListbyTestID(questionList, test_id);
		return result;
	}

	public List<TestQuestionMapping> getQuestionsfromTest(int test_id) {
		TestQuestionMappingDao dao = new TestQuestionMappingDaoImpl();
		List<TestQuestionMapping> testquestions = dao.getQuestionsfromTest(test_id);
		System.out.println("Serv Test Questions List:" + testquestions.size());
		
		return testquestions;
	}

	public boolean deleteQuestionsfromTest(int test_id, int question_id) {
		TestQuestionMappingDao dao = new TestQuestionMappingDaoImpl();
		boolean result = dao.deleteQuestionsfromTest(test_id, question_id);
		
		TestQuestionMapping tqm = new TestQuestionMapping();
		System.out.println("Serv Test ID: " + tqm.getTest_id());
		System.out.println("Serv Question ID: " + tqm.getQuestion_id());

		return result;
	}

	public int getMaxScoreOfTest(int test_id) {
		TestDao dao = new TestDaoImpl();
		int maxscoreofTest = dao.getMaxScoreOfTest(test_id);
		
		Test test = new Test();
		System.out.println("Serv Test ID: " + test.getMax_score());
		
		System.out.println("Serv Max Score: " + maxscoreofTest);
		
		return maxscoreofTest;
	}
}