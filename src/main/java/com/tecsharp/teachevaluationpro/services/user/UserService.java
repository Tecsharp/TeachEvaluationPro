package com.tecsharp.teachevaluationpro.services.user;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserClassroom;

public interface UserService {
	
	User getUserByUsername(String username);
	
	User findUserById(Long id);
	
	List<User> getUsersByClassroom(Classroom classroom );
	
	List<User> getTeacherByClassroom(Classroom classroom);
	
	List<User>findAll();
	
	User createUser(Long fid, Long level, User user, Long classid);
	
	List<User> getAllTeachersUnassigned(Long classroom);
	
	void registerUserSelectedOnClassroom(Long classId, List<Long> teacherList);
	
	void removeUserFromUserClassroom(Long userId, Long classroomId);
	
	List <User> getAllStudentsUnassigned(Long classId);
	
	Boolean isUserExistVerify(User user);
	
	void registerStudentsByExcel(List<User> userList, Long fid, Long classId, Long lvl);
	
	void mergeUserModify(User user);

	List<User> getUserByType(Integer userType);

	void createAdminUser(String username, String password);


}
