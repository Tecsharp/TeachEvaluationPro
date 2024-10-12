package com.tecsharp.teachevaluationpro.repositories.classroom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.repositories.classroom.ClassroomRepository;

@Repository
public class ClassroomRepositoryImpl implements ClassroomRepository{

	
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Classroom> findAll() {
		
		return em.createQuery("from Classroom").getResultList();
	}


	@Transactional(readOnly = true)
	@Override
	public Classroom getClassroomById(Long id) {
		return (Classroom) em.createQuery("from Classroom where id = :id").setParameter("id", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Classroom> getAllByGrade(Grade grade) {
		
		return em.createQuery("from Classroom where grade = :grade").setParameter("grade", grade).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Classroom> getClassroomByLevelAndFilial(Level level, Filial filial) {
		
		return em.createQuery("from Classroom where level = :level and filial = :filial")
				.setParameter("level", level)
				.setParameter("filial", filial)
				.getResultList();
	}

	@Transactional
	@Override
	public void saveNewClassroom(Classroom classroom) {
			em.persist(classroom); // Si es una nueva entidad
		
	}

	@Transactional
	@Override
	public void removeClassroom(Classroom classroom) {
		em.remove(classroom);
		
	}

	@Transactional
	@Override
	public void modifyClassroom(Classroom classroom) {
		em.merge(classroom);
		
	}
	


}
