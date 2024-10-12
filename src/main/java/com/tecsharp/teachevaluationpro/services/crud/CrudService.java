package com.tecsharp.teachevaluationpro.services.crud;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.School;

public interface CrudService {

	Integer getNumberOfClassroomsByLevelAndFilial(Level level, Filial filial);

	Integer getNumerOfStudentsByLevelAndFilial(Level level, Filial filial);

	Integer getNumberOfStudentsByClassroomAndFilial(Classroom classroom, Filial filial);

}
