package com.tecsharp.teachevaluationpro.services.classroom.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserClassroom;
import com.tecsharp.teachevaluationpro.repositories.classroom.ClassroomRepository;
import com.tecsharp.teachevaluationpro.repositories.filial.FilialRepository;
import com.tecsharp.teachevaluationpro.repositories.grade.GradeRepository;
import com.tecsharp.teachevaluationpro.repositories.level.LevelRepository;
import com.tecsharp.teachevaluationpro.repositories.userclassroom.UserClassroomRepository;
import com.tecsharp.teachevaluationpro.services.classroom.ClassroomService;
import com.tecsharp.teachevaluationpro.services.crud.CrudService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Service
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	ClassroomRepository classroomRepo;

	@Autowired
	CrudService crudService;
	
	@Autowired
	UserClassroomRepository userClassroomRepo;
	
	@Autowired
	LevelRepository levelRepo;
	
	@Autowired
	GradeRepository gradeRepo;
	
	@Autowired
	FilialRepository filialRepo;
	
	@Autowired
	UserService userService;
	

	@Override
	public List<Classroom> getAllClassroomByGrade(Grade grade) {

		return classroomRepo.getAllByGrade(grade);
	}

	@Override
	public List<Classroom> getClassroomByLevelAndFilial(Level level, Filial filial) {

		// SE OBTIENE LA LISTA DE LAS CLASSROOM
		List<Classroom> classroomList = classroomRepo.getClassroomByLevelAndFilial(level, filial);

		List<Classroom> newClassroomList = new ArrayList<Classroom>();
		
		
		// OBTIENE LA LISTA DE CLASSROOM
		for (Classroom classList : classroomList) {

			classList.setStudentsNumber(crudService.getNumberOfStudentsByClassroomAndFilial(classList, filial));
			newClassroomList.add(classList);

		}
		
		

		return newClassroomList;
	}

	@Override
	public Classroom getClassroomById(Long id) {
		// TODO Auto-generated method stub
		return classroomRepo.getClassroomById(id);
	}

	@Override
	public void saveNewClassroom(Classroom classroom) {

		classroom.setId(null);
		classroomRepo.saveNewClassroom(classroom);

	}

	@Override
	public List<Classroom> getClassroomListByUser(User user) {
		
		return userClassroomRepo.getClassroomsByStudent(user);
	}

	@Override
	public void removeClassroom(Classroom classroom) {
		
		classroomRepo.removeClassroom(classroom);
		
	}

	@Override
	public void modifyClassroom(Classroom classroom, Long levelid, Long fid, Long clid) {
		
		// SE TERMINA DE LLENAR EL CLASSROOM
		classroom.setLevel(levelRepo.findLevelById(levelid)); 
		classroom.setGrade(gradeRepo.getGradeById(classroom.getGrade().getId()));
		classroom.setFilial(filialRepo.getFilialById(fid));
		classroom.setId(clid);
		
		classroomRepo.modifyClassroom(classroom);
		
		
		
	}

	@Override
	public void cleanClassroom(Classroom classroom) {
		
		List<UserClassroom> userClassroomList = userClassroomRepo.getUsersByClassroom(classroom);
		
		for(UserClassroom userClassroom : userClassroomList) {
			
			userClassroomRepo.removeUserClassroom(userClassroom);
			
		}
		
	}

	@Override
	public List<Classroom> getAllClassroom() {
		return classroomRepo.findAll();
	}

}
