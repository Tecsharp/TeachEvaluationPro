package com.tecsharp.teachevaluationpro.repositories.classroom;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;

public interface ClassroomRepository {
	
	List<Classroom> findAll();
	
	Classroom getClassroomById(Long id);
	
	List<Classroom> getClassroomByLevelAndFilial(Level level, Filial filial);
	
	List<Classroom> getAllByGrade(Grade grade);
	
	void saveNewClassroom(Classroom classroom);
	
	void removeClassroom(Classroom classroom);
	
	void modifyClassroom(Classroom classroom);

}
