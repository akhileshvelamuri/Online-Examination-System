package com.oes.daointerfaces;

import java.util.ArrayList;
import java.util.List;

import com.oes.models.Test;
import com.oes.models.TestQuestionMapping;

public interface TestQuestionMappingDao {
	
	List<TestQuestionMapping> questionList = new ArrayList<TestQuestionMapping>();
	
	int addQuestionstoList(List<TestQuestionMapping> questionList);

	int addQuestionstoListbyTestID(List<TestQuestionMapping> questionList2, int test_id);

	List<TestQuestionMapping> getQuestionsfromTest(int test_id);

	List<TestQuestionMapping> getQuestionsfromTests();

	boolean deleteQuestionsfromTest(int test_id, int question_id);

}
