package com.tecsharp.teachevaluationpro.repositories.subject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsharp.teachevaluationpro.models.Subject;
import com.tecsharp.teachevaluationpro.models.User;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
	
	Subject findSubjectById(Long id);
	
	List<Subject> findAll();

}
