package com.tecsharp.teachevaluationpro.services.evaluatiouserresult;

import com.tecsharp.teachevaluationpro.models.EvaluationUserResult;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;

public interface EvaluationUserResultService {

	EvaluationUserResult verifyExistEvaluationResult(Exam exam, User user, String userAnswer );
	
	void registerEvaluationUserResult(Double score, String question, String userAnswer, Exam exam, User user);
	
}
