package com.tecsharp.teachevaluationpro.services.subject;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Subject;
import com.tecsharp.teachevaluationpro.models.User;

public interface SubjectService {
	
	Subject getSubjectById(Long id);
	
	List<Subject> getSubjectAll();
	
	void registerUserSubject(User user, List<Long> subjectSelected);

}
