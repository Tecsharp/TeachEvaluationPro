package com.tecsharp.teachevaluationpro.repositories.init;

import com.tecsharp.teachevaluationpro.models.Init;
import com.tecsharp.teachevaluationpro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitRepository extends JpaRepository<Init, Long> {

    Init findInitById(Long intID);



}
