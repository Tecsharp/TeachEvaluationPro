package com.tecsharp.teachevaluationpro.repositories.evaluationuserresult.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tecsharp.teachevaluationpro.services.evaluatiouserresult.impl.EvaluationUserResultServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.EvaluationUserResult;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.evaluationuserresult.EvaluationUserResultRepository;
import com.tecsharp.teachevaluationpro.services.evaluatiouserresult.EvaluationUserResultService;

@Repository
public class EvaluationUserResultRepositoryImpl implements EvaluationUserResultRepository{
	private static final Logger logger = LogManager.getLogger(EvaluationUserResultRepositoryImpl.class);
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	@Override
	public EvaluationUserResult verifyExistEvaluationResult(Exam exam, User user, String question) {
	    List<EvaluationUserResult> resultList = em.createQuery("from EvaluationUserResult WHERE exam = :exam AND user = :user AND question = :question", EvaluationUserResult.class)
	            .setParameter("exam", exam)
	            .setParameter("user", user)
	            .setParameter("question", question)
	            .getResultList();

	    if (!resultList.isEmpty()) {

			logger.info("No existe resultado entre usuario y respuesta");
	        // Si hay resultados, devuelve el primero (o maneja múltiples resultados según tus necesidades)
	        return resultList.get(0);
	    } else {
	        // Si no hay resultados, puedes devolver null o lanzar una excepción, según tus necesidades
	        return null;
	    }
	}

	@Transactional
	@Override
	public void registerEvaluationUserResult(EvaluationUserResult evaluationUserResult) {
		em.persist(evaluationUserResult);
		
	}


}
