package com.tecsharp.teachevaluationpro.services.examtype.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.ExamType;
import com.tecsharp.teachevaluationpro.repositories.examtype.ExamTypeRepository;
import com.tecsharp.teachevaluationpro.services.examtype.ExamTypeService;

@Service
public class ExamTypeServiceImpl implements ExamTypeService{

	@Autowired
	ExamTypeRepository examTypeRepo;
	
	@Override
	public ExamType getExamTypeById(Long id) {
		return examTypeRepo.getExamTypeById(id);
	}

}
