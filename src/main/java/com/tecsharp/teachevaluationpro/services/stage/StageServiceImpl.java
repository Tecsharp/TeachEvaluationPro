package com.tecsharp.teachevaluationpro.services.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.repositories.stage.StageRepository;

@Service
public class StageServiceImpl implements StageService{


	@Autowired
	private StageRepository stageRepo;

	@Override
    public void guardarArrayEnDB(String[] array, String textToTranslate, String correctAnswer) {
    	
    	Evaluation lessonlevel = new Evaluation();
    	lessonlevel.setCorrectAnswer(textToTranslate);
        lessonlevel.setArrayTextAnswers(array);
        lessonlevel.setCorrectAnswer(correctAnswer);
        lessonlevel.setMessageAnswerCorrect(textToTranslate);
        stageRepo.save(lessonlevel);
    }


}
