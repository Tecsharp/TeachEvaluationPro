package com.tecsharp.teachevaluationpro.services.grade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.repositories.grade.GradeRepository;
import com.tecsharp.teachevaluationpro.services.grade.GradeService;

@Service
public class GradeServiceImpl implements GradeService {
	
	@Autowired
	GradeRepository gradeRepo;
	
	
	@Override
	public Grade getGradeById(Long id) {
		// TODO Auto-generated method stub
		return gradeRepo.getGradeById(id);
	}
	
	@Override
	public List<Grade> getGradeListByLevelAndFilial(Level level, Filial filial) {

		return gradeRepo.getGradeListByLevelAndFilial(level, filial);
	}



	@Override
	public List<Grade> getAllGradesByLevel(Level level) {
		// TODO Auto-generated method stub
		return gradeRepo.getAllGradesByLevel(level);
	}



	@Override
	public List<Grade> getAllGrades() {
		// TODO Auto-generated method stub
		return gradeRepo.getAllGrades();
	}

}
