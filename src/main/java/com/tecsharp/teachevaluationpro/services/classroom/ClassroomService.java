package com.tecsharp.teachevaluationpro.services.classroom;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.User;

public interface ClassroomService {

	Classroom getClassroomById(Long id);

	List<Classroom> getClassroomByLevelAndFilial(Level level, Filial filial);

	void saveNewClassroom(Classroom classroom);

	List<Classroom> getAllClassroomByGrade(Grade grade);

	List<Classroom> getClassroomListByUser(User user);	
	
	void removeClassroom(Classroom classroom);
	
	void modifyClassroom(Classroom classroom, Long levelid, Long fid, Long clid);
	
	void cleanClassroom(Classroom classroom);

	List<Classroom> getAllClassroom();
}
