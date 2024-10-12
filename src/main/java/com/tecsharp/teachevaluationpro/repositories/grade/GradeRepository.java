package com.tecsharp.teachevaluationpro.repositories.grade;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;

public interface GradeRepository {
	
	Grade getGradeById(Long id);
	
	List<Grade> getGradeListByLevelAndFilial(Level level, Filial filial);
	
	void saveGrade(Grade grade);
	
	List<Grade> getAllGradesByLevel(Level level);

	List<Grade> getAllGrades();
}
