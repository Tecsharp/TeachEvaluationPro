package com.tecsharp.teachevaluationpro.repositories.grade.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.grade.GradeRepository;

@Repository
public class GradeRepositoryImpl implements GradeRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	@Override
	public Grade getGradeById(Long id) {
		return (Grade) em.createQuery("from Grade where id = :id").setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Grade> getGradeListByLevelAndFilial(Level level, Filial filial) {
		// TODO Auto-generated method stub
		return (List<Grade>) em.createQuery("from Grade where level = :level and filial = :filial")
				.setParameter("level", level).setParameter("filial", filial).getResultList();
	}

	@Transactional
	@Override
	public void saveGrade(Grade grade) {
		em.persist(grade);

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Grade> getAllGradesByLevel(Level level) {
		return (List<Grade>) em.createQuery("from Grade where level = :level")
				.setParameter("level", level)
				.getResultList() ;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Grade> getAllGrades() {
		// TODO Auto-generated method stub
		return (List<Grade>) em.createQuery("from Grade").getResultList();
	}

}
