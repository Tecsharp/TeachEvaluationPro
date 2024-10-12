package com.tecsharp.teachevaluationpro.repositories.filial.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tecsharp.teachevaluationpro.models.Classroom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.repositories.filial.FilialRepository;

@Repository
public class FilialRepositoryImpl implements FilialRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void saveFilial(Filial filial) {
		if (filial.getId() == null) {
			em.persist(filial); // Si es una nueva entidad
		} else {
			em.merge(filial); // Si es una entidad existente
		}
	}

	@Transactional
	@Override
	public Filial getFilialById(Long id) {
		
		return (Filial) em.createQuery("from Filial where id = :id").setParameter("id", id).getSingleResult();
	}

	@Transactional
	@Override
	public Filial getFilialByName(String name) {
		
		return (Filial) em.createQuery("from Filial where name = :name").setParameter("name", name).getSingleResult();
	}
	
	@Transactional
	@Override
	public void removeFilial(Filial filial) {
		em.remove(filial);
		
	}

	@Transactional(readOnly = true)
	@Override
	public List<Filial> getAllFilial() {

		return (List<Filial>) em.createQuery("from Filial").getResultList();

	}

}
