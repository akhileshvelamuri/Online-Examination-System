package com.oes.daointerfaces;

import java.util.List;

import com.oes.models.Test;

public interface TestDao {

	int createTest(Test test);
	List<Test> getTestList();
	int editTest(Test test);
	boolean deleteTest(int test_id);
	Test getTest(int test_id);
	int getMaxScoreOfTest(int test_id);
}
