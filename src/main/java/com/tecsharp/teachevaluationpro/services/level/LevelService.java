package com.tecsharp.teachevaluationpro.services.level;

import java.util.List;

import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;

public interface LevelService {

	List<Level> getAllLevels();

	List<Level> getLevelListByFilial(Filial filial);

	void saveLevel(Level level);

	Level getLevelById(Long id);

	List<Level> getLevelListByFilial(Long id);

	Boolean classroomLevelExist(Long level, Long id);
}
