package com.tecsharp.teachevaluationpro.services.crud.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.School;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserClassroom;
import com.tecsharp.teachevaluationpro.repositories.classroom.ClassroomRepository;
import com.tecsharp.teachevaluationpro.repositories.crud.CrudRepository;
import com.tecsharp.teachevaluationpro.repositories.filial.FilialRepository;
import com.tecsharp.teachevaluationpro.repositories.grade.GradeRepository;
import com.tecsharp.teachevaluationpro.repositories.level.LevelRepository;
import com.tecsharp.teachevaluationpro.repositories.user.UserRepository;
import com.tecsharp.teachevaluationpro.services.crud.CrudService;

@Service
public class CrudServicesImpl implements CrudService {


	@Autowired
	CrudRepository crudRepo;


	@Override
	public Integer getNumberOfClassroomsByLevelAndFilial(Level level, Filial filial) {
		List<Classroom> classroomList = crudRepo.getClassroomListByLevelAndFilial(level, filial);
		return classroomList.size();
	}

	@Override
	public Integer getNumerOfStudentsByLevelAndFilial(Level level, Filial filial) {

		List<User> userList = crudRepo.getStudentsByLevelAndFilial(level, filial);

		// TODO Auto-generated method stub
		return userList.size();
	}

	@Override
	public Integer getNumberOfStudentsByClassroomAndFilial(Classroom classroom, Filial filial) {
		
		List<UserClassroom> studentsList = crudRepo.getUserListByClassroom(classroom);
		
		List<User> filterStudentListByStudentType = new ArrayList<User>();
		for (UserClassroom stuLst : studentsList) {
			
			if(stuLst.getUser().getType() == 3) {
				
				filterStudentListByStudentType.add(stuLst.getUser());
				
			}
			
		}
		
		return filterStudentListByStudentType.size();
	}

}
