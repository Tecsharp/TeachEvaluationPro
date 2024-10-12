package com.tecsharp.teachevaluationpro.services.grade;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;

public interface GradeService {

	List<Grade> getGradeListByLevelAndFilial(Level level, Filial filial);

	List<Grade> getAllGradesByLevel(Level level);

	List<Grade> getAllGrades();

	Grade getGradeById(Long id);

}
