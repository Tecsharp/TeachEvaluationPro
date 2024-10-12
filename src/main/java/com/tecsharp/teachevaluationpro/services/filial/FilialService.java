package com.tecsharp.teachevaluationpro.services.filial;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.School;

public interface FilialService {
	
	void createFilial(Filial filial);
	
	List<Filial> createListFilialOfSchool(School school);
	
	Filial getFilialById(Long id);
	
	Filial getFilialByName(String name);
	
	List<Filial> getFilialListBySchool();

	void removeFilial(Long fid);

	List<Filial> getAllFilial();
}
