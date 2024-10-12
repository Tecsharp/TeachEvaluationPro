package com.tecsharp.teachevaluationpro.repositories.evaluation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;


public interface EvaluationRepository{
	
	Evaluation getLessonLevelById(Long id);
	
	void saveEvaluation(Evaluation evaluation);
	
	List<Evaluation> findAll();
	
	List<Evaluation> findByExam(Exam exam);
	
	
}


