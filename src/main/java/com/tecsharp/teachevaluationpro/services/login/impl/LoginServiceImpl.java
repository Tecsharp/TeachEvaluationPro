package com.tecsharp.teachevaluationpro.services.login.impl;

import com.tecsharp.teachevaluationpro.utils.crypt.Sha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.user.UserRepository;
import com.tecsharp.teachevaluationpro.services.login.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public User getUserByUsername(String username) {
		return userRepo.getUserByUsername(username);
	}

	@Override
	public User getUserLogin(String password, String username) {


		User userLogin = userRepo.getUserByUsername(username);
		if (obtenerAutorizacionComparandoPass(password, username, userLogin)) {

			return userRepo.getUserByUsername(username);
		}

		return null;
	}

	@Override
	public Boolean obtenerAutorizacionComparandoPass(String password, String username, User userLogin) {

		Sha256 sha = new Sha256();
		return sha.comparePassword(password, userLogin.getPassword(), username);
	}

}
