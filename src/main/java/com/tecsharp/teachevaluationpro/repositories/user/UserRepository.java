package com.tecsharp.teachevaluationpro.repositories.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUsername(String username);

	User getUserByUsername(String username);

	List<User> findAll();

	User getUserById(Long id);

	List<User> findByType(Integer type);

	User findByEmail(String email);




	
	

}
