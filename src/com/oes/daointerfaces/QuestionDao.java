package com.oes.daointerfaces;

import java.util.List;

import com.oes.models.Question;


public interface QuestionDao {
	
	int insertQuestion(Question question);
	List<Question> getQuestionList();
	boolean deleteQuestion(int question_id);
	Question getQuestion(int question_id);
	int editQuestion(Question qn);
	List<Question> getQuestionList(int subject_id);
}
