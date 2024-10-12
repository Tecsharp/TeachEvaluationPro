package com.tecsharp.teachevaluationpro.repositories.examdate.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.ExamDate;
import com.tecsharp.teachevaluationpro.repositories.examdate.ExamDateRepository;

@Repository
public class ExamDateRepositoryImpl implements ExamDateRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void saveDataDateExam(ExamDate examDate) {

		em.persist(examDate);

	}

	@Transactional(readOnly = true)
	@Override
	public ExamDate findByExam(Exam exam) {

		return (ExamDate) em.createQuery("from ExamDate where exam = :exam").setParameter("exam", exam).getSingleResult();
	}

}
