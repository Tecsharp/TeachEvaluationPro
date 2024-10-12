package com.tecsharp.teachevaluationpro.repositories.level;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.Subject;

public interface LevelRepository {

	Level findLevelById(Long id);
	
	List<Level> getLevelListByFilial(Filial filial);
	
	void saveLevelByFilial(Level level);
	
	List<Level> getAllLevels();

	
}
