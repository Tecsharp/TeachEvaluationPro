package com.tecsharp.teachevaluationpro.repositories.userclassroom;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserClassroom;

public interface UserClassroomRepository {

	List<User> getStudentsByClassroomId(Long id);
	
	List<Classroom> getClassroomsByStudent(User user);
	
	void saveUserClassroom(UserClassroom userClassroom);
	
	void removeUserClassroom(UserClassroom userClassroom);
	
	List<UserClassroom> getUsersByClassroom(Classroom classroom);
	
	UserClassroom getUserClassroomByUserAndClassroom(User user, Classroom classroom);
}
