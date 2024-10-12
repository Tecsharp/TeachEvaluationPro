package com.tecsharp.teachevaluationpro.services.security.impl;

import com.tecsharp.teachevaluationpro.services.security.SecurityCryptService;
import com.tecsharp.teachevaluationpro.utils.crypt.Sha256;
import org.springframework.stereotype.Service;
@Service
public class SecurityCryptServiceImpl implements SecurityCryptService {
    @Override
    public String createPasswordHased(String password, String username) {

        Sha256 sha = new Sha256();

        return sha.computeHash(password, "lpmw3221", username);
    }
}
