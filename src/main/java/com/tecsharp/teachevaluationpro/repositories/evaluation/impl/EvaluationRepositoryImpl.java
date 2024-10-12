package com.tecsharp.teachevaluationpro.repositories.evaluation.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.websocket.Session;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.repositories.evaluation.EvaluationRepository;

@Repository
public class EvaluationRepositoryImpl implements EvaluationRepository{

	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public Evaluation getLessonLevelById(Long id) {
		
		return (Evaluation) em.createQuery("from Evaluation where id = :id").setParameter("id", id).getSingleResult();
	}

	@Override
	@Transactional
	public void saveEvaluation(Evaluation evaluation) {
		em.persist(evaluation);
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Evaluation> findAll() {

		return em.createQuery("from Evaluation").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Evaluation> findByExam(Exam exam) {

		return em.createQuery("from Evaluation where exam = :exam").setParameter("exam", exam).getResultList();
	}
	
}