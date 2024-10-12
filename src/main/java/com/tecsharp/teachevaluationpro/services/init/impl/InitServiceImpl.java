package com.tecsharp.teachevaluationpro.services.init.impl;

import com.tecsharp.teachevaluationpro.models.Init;
import com.tecsharp.teachevaluationpro.repositories.init.InitRepository;
import com.tecsharp.teachevaluationpro.services.init.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    InitRepository initRepo;

    @Override
    public Init getVerifyInitRegister(Long initID) {

        return initRepo.findInitById(initID);
    }
}
