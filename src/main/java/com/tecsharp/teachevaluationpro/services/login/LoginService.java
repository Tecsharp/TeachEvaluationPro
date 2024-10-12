package com.tecsharp.teachevaluationpro.services.login;

import com.tecsharp.teachevaluationpro.models.User;

public interface LoginService {
	
	User getUserByUsername(String username);

	User getUserLogin(String password, String username);

	Boolean obtenerAutorizacionComparandoPass(String password, String username, User userLogin);


}
