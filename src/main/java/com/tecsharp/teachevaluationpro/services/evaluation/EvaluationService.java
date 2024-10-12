package com.tecsharp.teachevaluationpro.services.evaluation;

import java.util.List;
import java.util.Optional;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;

public interface EvaluationService {

	Evaluation getById(Long Id);

	void buildEvaluation(String question, String firstAns, String secondAns, String thirdAns, String fourthAns,
			Double score, String correctAnswer, Long examId);

	void sumEvaluationScoreToSaveExam(Long examId);

	List<Evaluation> evaluationsListByExam(Exam exam);

	Boolean existUserExamQuestionRelation(Exam exam, User user, String question);
	
	
}
