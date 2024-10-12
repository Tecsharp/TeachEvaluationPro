package com.tecsharp.teachevaluationpro.repositories.usersubject.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.UserSubject;
import com.tecsharp.teachevaluationpro.repositories.usersubject.UserSubjectRepository;

@Repository
public class UserSubjectRepositoryImpl implements UserSubjectRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<UserSubject> getUserSubjectListById(Long id) {
		
		return em.createQuery("from UserSubject WHERE user = '" + id + "'").getResultList();
		
	}

	@Transactional
	@Override
	public void saveUserSubject(UserSubject userSubject) {
		em.persist(userSubject);
		
	}
	
	
	
}
