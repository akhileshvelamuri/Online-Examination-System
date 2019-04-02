package com.oes.daointerfaces;

import java.util.List;

import com.oes.models.Subject;

public interface SubjectDao {
	
	int addSubject(Subject subject);
	boolean deleteSubject(int Subject_id);
	List<Subject> getSubjectList();
	int editSubject(Subject sub);
	Subject getSubject(int subject_id);
}
