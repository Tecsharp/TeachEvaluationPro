package com.tecsharp.teachevaluationpro.services.subject.impl;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Subject;
import com.tecsharp.teachevaluationpro.models.UserSubject;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.crud.CrudRepository;
import com.tecsharp.teachevaluationpro.repositories.subject.SubjectRepository;
import com.tecsharp.teachevaluationpro.repositories.usersubject.UserSubjectRepository;
import com.tecsharp.teachevaluationpro.services.subject.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectRepository subjectRepo;

	@Autowired
	UserSubjectRepository userSubjectRepo;

	@Override
	public Subject getSubjectById(Long id) {

		return subjectRepo.findSubjectById(id);
	}

	@Override
	public List<Subject> getSubjectAll() {

		return subjectRepo.findAll();
	}

	@Override
	public void registerUserSubject(User user, List<Long> subjectSelected) {

		// List<Subject> newSubjectList = new ArrayList<Subject>();

		for (Long subject : subjectSelected) {

			UserSubject userSubject = new UserSubject();

			userSubject.setSubject(subjectRepo.findSubjectById(subject));
			userSubject.setUser(user);
			
			userSubjectRepo.saveUserSubject(userSubject);

		}

	}

}
