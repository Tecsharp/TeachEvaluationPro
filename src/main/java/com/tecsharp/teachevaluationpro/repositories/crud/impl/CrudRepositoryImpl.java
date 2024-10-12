package com.tecsharp.teachevaluationpro.repositories.crud.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.School;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserClassroom;
import com.tecsharp.teachevaluationpro.repositories.crud.CrudRepository;

@Repository
public class CrudRepositoryImpl implements CrudRepository {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Filial> getFilialListBySchool(Long id) {

		Long idSchool = 1L;
		return (List<Filial>) em.createQuery("from School").getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public School getSchoolById() {

		Long idSchool = 1L;
		return (School) em.createQuery("from School where id = :id").setParameter("id", idSchool).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<User> getUserListByClassroom2(Classroom classroom) {

		return (List<User>) em.createQuery("from User where classroom = :classroom and type = 3")
				.setParameter("classroom", classroom).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public User getTeacherByClassroom(Classroom classroom) {
		// TODO Auto-generated method stub
		return (User) em.createQuery("from User where classroom = :classroom and type = 2")
				.setParameter("classroom", classroom).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Classroom> getClassroomListByFilial(Filial filial) {
		// TODO Auto-generated method stub
		return (List<Classroom>) em.createQuery("SELECT c FROM Classroom c INNER JOIN c.filial f WHERE f = :filial")
				.setParameter("filial", filial).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Classroom> getClassroomListByLevelAndFilial(Level level, Filial filial) {

		return (List<Classroom>) em.createQuery("FROM Classroom WHERE level = :level and filial = :filial")
				.setParameter("filial", filial).setParameter("level", level).getResultList();
	}

	@Override
	public List<Classroom> getClassroomListByClassroom(Classroom classroom) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getStudentsByLevelAndFilial(Level level, Filial filial) {
		// TODO Auto-generated method stub
		return (List<User>) em.createQuery("FROM User WHERE filial = :filial AND level = :level")
				.setParameter("filial", filial).setParameter("level", level).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<UserClassroom> getUserListByClassroom(Classroom classroom) {
		// TODO Auto-generated method stub

		return (List<UserClassroom>) em.createQuery("from UserClassroom where classroom = :classroom")
				.setParameter("classroom", classroom).getResultList();
	}

	@Transactional
	@Override
	public void mergeUser(User utom) {
		

		em.merge(utom);

	}

}
