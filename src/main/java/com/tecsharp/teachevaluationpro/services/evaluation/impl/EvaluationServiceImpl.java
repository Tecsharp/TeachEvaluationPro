package com.tecsharp.teachevaluationpro.services.evaluation.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.evaluation.EvaluationRepository;
import com.tecsharp.teachevaluationpro.repositories.exam.ExamRepository;
import com.tecsharp.teachevaluationpro.services.evaluation.EvaluationService;

@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	EvaluationRepository evaluationRepo;
	
	@Autowired
	ExamRepository examRepo;

	@Override
	public Evaluation getById(Long Id) {

		return evaluationRepo.getLessonLevelById(Id);
	}

	@Override
	public void buildEvaluation(String question, String firstAns, String secondAns, String thirdAns, String fourthAns,
			Double score, String correctAnswer, Long examId) {

		Evaluation evaluation = new Evaluation();
		evaluation.setQuestion(question);
		evaluation.setScore(score);
		evaluation.setHeaderText("Responde la siguiente pregunta");
		String[] arrayCorrectAns = { firstAns, secondAns, thirdAns, fourthAns };
		evaluation.setArrayTextAnswers(arrayCorrectAns);
		evaluation.setCorrectAnswer(correctAnswer);
		evaluation.setMessageAnswerCorrect("");
		evaluation.setExam(examRepo.getExamById(examId));
		
		evaluationRepo.saveEvaluation(evaluation);
		

	}

	@Override
	public void sumEvaluationScoreToSaveExam(Long examId) {
		
		List<Evaluation> evaluationList = evaluationRepo.findByExam(examRepo.getExamById(examId));
		Double finalScore = 0.0;  // Inicializar la variable acumuladora antes del bucle

		for (Evaluation evaluation : evaluationList) {
		    Double score = evaluation.getScore();
		    finalScore += score;  // Acumular el puntaje en cada iteración
		}
		
		
		Exam exam = examRepo.getExamById(examId);
		exam.setScore(finalScore);
		
		examRepo.saveExam(exam);
		
		
	}

	@Override
	public List<Evaluation> evaluationsListByExam(Exam exam) {
		
		return evaluationRepo.findByExam(exam);
	}

	@Override
	public Boolean existUserExamQuestionRelation(Exam exam, User user, String question) {
		
		// VERIFICA SI EXISTE LA RELACION
		
		return null;
		
	}

}
