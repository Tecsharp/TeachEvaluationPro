package com.tecsharp.teachevaluationpro.services.exam;

import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.ExamDate;

public interface ExamService {
	
	Exam createExam(String examName, Integer numberQuestions, Long examSubject, Long examType,
			Long teacherId);
	
	Exam getExamById(Long id);
	
	void convertStringToDateAndSave(String initDate, String initTime, String endDate, String endTime, Exam exam);
	
	String getStringDaysRemaining(ExamDate examDate, Exam exam);
	
	Boolean nameAlreadyExistCheck(String examName);
	
}
