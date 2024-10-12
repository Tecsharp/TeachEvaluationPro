package com.tecsharp.teachevaluationpro.repositories.userclassroom.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserClassroom;
import com.tecsharp.teachevaluationpro.repositories.userclassroom.UserClassroomRepository;

@Repository
public class UserClassroomRepositoryImpl implements UserClassroomRepository{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<User> getStudentsByClassroomId(Long id) {

		return em.createQuery("from User where classroom = :id and type=3").setParameter("id", id).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Classroom> getClassroomsByStudent(User user) {
		// TODO Auto-generated method stub
		return  em.createQuery("from UserClassroom where user = :user").setParameter("user", user).getResultList();
	}

	@Transactional
	@Override
	public void saveUserClassroom(UserClassroom userClassroom) {

		em.persist(userClassroom);
		
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<UserClassroom> getUsersByClassroom(Classroom classroom) {
		// TODO Auto-generated method stub
		return  em.createQuery("from UserClassroom where classroom = :classroom").setParameter("classroom", classroom).getResultList();
	}

	@Transactional
	@Override
	public void removeUserClassroom(UserClassroom userClassroom) {
		em.remove(userClassroom);
	}

	@Transactional
	@Override
	public UserClassroom getUserClassroomByUserAndClassroom(User user, Classroom classroom) {
		// TODO Auto-generated method stub
		return (UserClassroom) em.createQuery("from UserClassroom where classroom = :classroom and user = :user")
				.setParameter("classroom", classroom)
				.setParameter("user", user)
				.getSingleResult();
	}

}
