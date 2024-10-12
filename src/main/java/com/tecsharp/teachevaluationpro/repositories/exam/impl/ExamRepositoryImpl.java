package com.tecsharp.teachevaluationpro.repositories.exam.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.repositories.exam.ExamRepository;

@Repository
public class ExamRepositoryImpl implements ExamRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Exam> findAll() {

		return em.createQuery("from Exam").getResultList();
	}

	@Transactional
	@Override
	public void saveExam(Exam exam) {
		em.persist(exam);
	}

	@Transactional(readOnly = true)
	@Override
	public Exam findExamByName(String name) {

		return (Exam) em.createQuery("from Exam where name = :name").setParameter("name", name).getSingleResult();
	}

	@Transactional(readOnly = true)
	@Override
	public Exam getExamById(Long id) {

		return (Exam) em.createQuery("from Exam where id = :id").setParameter("id", id).getSingleResult();
	}

}
