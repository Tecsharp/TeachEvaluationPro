package com.tecsharp.teachevaluationpro.repositories.filial;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Filial;

public interface FilialRepository {
	
	void saveFilial(Filial filial);
	
	Filial getFilialById(Long id);
	
	Filial getFilialByName(String name);
	
	void removeFilial(Filial filial);

	List<Filial> getAllFilial();

}
