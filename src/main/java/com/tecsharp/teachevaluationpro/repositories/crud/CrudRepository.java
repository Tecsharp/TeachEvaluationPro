package com.tecsharp.teachevaluationpro.repositories.crud;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.School;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserClassroom;

public interface CrudRepository {

	List<Filial> getFilialListBySchool(Long id);

	School getSchoolById();

	List<User> getUserListByClassroom2(Classroom classroom);

	List<UserClassroom> getUserListByClassroom(Classroom classroom);

	User getTeacherByClassroom(Classroom classroom);

	List<Classroom> getClassroomListByFilial(Filial filial);

	List<Classroom> getClassroomListByLevelAndFilial(Level level, Filial filial);

	List<Classroom> getClassroomListByClassroom(Classroom classroom);

	List<User> getStudentsByLevelAndFilial(Level level, Filial filial);
	
	void mergeUser(User user);
	

}
