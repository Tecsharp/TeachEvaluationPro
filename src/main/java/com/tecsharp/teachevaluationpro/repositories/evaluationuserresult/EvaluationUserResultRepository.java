package com.tecsharp.teachevaluationpro.repositories.evaluationuserresult;

import com.tecsharp.teachevaluationpro.models.EvaluationUserResult;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.services.evaluatiouserresult.EvaluationUserResultService;

public interface EvaluationUserResultRepository {

	EvaluationUserResult verifyExistEvaluationResult(Exam exam, User user, String userAnswer);
	
	void registerEvaluationUserResult(EvaluationUserResult evaluationUserResult);

}
