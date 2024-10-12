package com.tecsharp.teachevaluationpro.repositories.exam;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.User;


public interface ExamRepository {
	
	List<Exam> findAll();
	
	void saveExam(Exam exam);
	
	Exam findExamByName (String name);
	
	Exam getExamById(Long id);
	
	
}
