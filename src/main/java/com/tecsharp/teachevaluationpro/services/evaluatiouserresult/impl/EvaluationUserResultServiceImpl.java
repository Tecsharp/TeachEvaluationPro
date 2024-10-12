package com.tecsharp.teachevaluationpro.services.evaluatiouserresult.impl;

import com.tecsharp.teachevaluationpro.controllers.EvaluationController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.EvaluationUserResult;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.evaluationuserresult.EvaluationUserResultRepository;
import com.tecsharp.teachevaluationpro.services.evaluatiouserresult.EvaluationUserResultService;


@Service
public class EvaluationUserResultServiceImpl implements EvaluationUserResultService{
	private static final Logger logger = LogManager.getLogger(EvaluationUserResultServiceImpl.class);
	@Autowired
	private EvaluationUserResultRepository evaluationUserResultRepo;

	@Override
	public EvaluationUserResult verifyExistEvaluationResult(Exam exam, User user, String userAnswer) {
		
		return evaluationUserResultRepo.verifyExistEvaluationResult(exam, user, userAnswer);
	}

	@Override
	public void registerEvaluationUserResult(Double score, String question, String userAnswer, Exam exam, User user) {
		
		EvaluationUserResult evaluationUserResult = new EvaluationUserResult();
		evaluationUserResult.setScore(score);
		evaluationUserResult.setQuestion(question);
		evaluationUserResult.setUserAnswer(userAnswer);
		evaluationUserResult.setExam(exam);
		evaluationUserResult.setUser(user);
		
		evaluationUserResultRepo.registerEvaluationUserResult(evaluationUserResult);
		logger.info("Se registró el Resultado de la respuesta");
	}

}
