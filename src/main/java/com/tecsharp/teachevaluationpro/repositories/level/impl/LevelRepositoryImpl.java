package com.tecsharp.teachevaluationpro.repositories.level.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.repositories.level.LevelRepository;

@Repository
public class LevelRepositoryImpl implements LevelRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public Level findLevelById(Long id) {

		return (Level) em.createQuery("from Level where id = :id").setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Level> getLevelListByFilial(Filial filial) {
		
		return (List<Level>)em.createQuery("from Level where filial = :filial")
				.setParameter("filial", filial)
				.getResultList();
	}

	@Transactional
	@Override
	public void saveLevelByFilial(Level level) {
		em.persist(level);
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Level> getAllLevels() {
		// TODO Auto-generated method stub
		return (List<Level>)em.createQuery("from Level")
				.getResultList();
	}


	

}
