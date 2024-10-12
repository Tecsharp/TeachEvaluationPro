package com.tecsharp.teachevaluationpro.repositories.examtype;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsharp.teachevaluationpro.models.ExamType;
import com.tecsharp.teachevaluationpro.models.Subject;
@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType,Long>{
	
	List<ExamType> findAll();
	
	ExamType getExamTypeById(Long id); 


}
