package com.tecsharp.teachevaluationpro.repositories.examdate;

import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.ExamDate;

public interface ExamDateRepository {
	
	void saveDataDateExam(ExamDate examDate);
	
	ExamDate findByExam(Exam exam);

}
