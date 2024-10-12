package com.tecsharp.teachevaluationpro.repositories.stage;

import org.springframework.data.repository.CrudRepository;

import com.tecsharp.teachevaluationpro.models.Evaluation;

public interface StageRepository  extends CrudRepository<Evaluation, Long> {

}
