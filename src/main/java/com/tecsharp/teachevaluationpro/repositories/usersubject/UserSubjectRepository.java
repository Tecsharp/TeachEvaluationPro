package com.tecsharp.teachevaluationpro.repositories.usersubject;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.UserSubject;

public interface UserSubjectRepository {

	List<UserSubject> getUserSubjectListById(Long id);
	
	void saveUserSubject(UserSubject userSubject);
	
}
