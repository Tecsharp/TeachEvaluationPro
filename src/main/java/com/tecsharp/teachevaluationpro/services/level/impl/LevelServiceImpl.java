package com.tecsharp.teachevaluationpro.services.level.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.repositories.classroom.ClassroomRepository;
import com.tecsharp.teachevaluationpro.repositories.level.LevelRepository;
import com.tecsharp.teachevaluationpro.services.crud.CrudService;
import com.tecsharp.teachevaluationpro.services.filial.FilialService;
import com.tecsharp.teachevaluationpro.services.level.LevelService;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	LevelRepository levelRepo;

	@Autowired
	CrudService crudService;

	@Autowired
	FilialService filialService;

	@Autowired
	ClassroomRepository classroomRepo;

	@Override
	public List<Level> getLevelListByFilial(Long id) {

		List<Level> levelList = new ArrayList<Level>();
		// List<Level> newLevelList = new ArrayList<Level>();
		List<Filial> filialList = filialService.getFilialListBySchool();

		for (Filial filial : filialList) {
			// levelList.add(filial.getLevel());

			for (Level level : levelList) {

				if (level.getNumStudents() == null) {
					level.setNumStudents(crudService.getNumerOfStudentsByLevelAndFilial(level, filial));
				}
				if (level.getNumClassrooms() == null) {
					level.setNumClassrooms(crudService.getNumberOfClassroomsByLevelAndFilial(level, filial));
				}
			}

		}

		return levelList;

	}

	@Override
	public Level getLevelById(Long id) {
		// TODO Auto-generated method stub
		return levelRepo.findLevelById(id);
	}

	@Override
	public List<Level> getLevelListByFilial(Filial filial) {
		// TODO Auto-generated method stub
		return levelRepo.getLevelListByFilial(filial);
	}

	@Override
	public void saveLevel(Level level) {

		levelRepo.saveLevelByFilial(level);
	}

	@Override
	public List<Level> getAllLevels() {
		// TODO Auto-generated method stub
		return levelRepo.getAllLevels();
	}

	@Override
	public Boolean classroomLevelExist(Long level, Long fid) {

		List<Classroom> classroomList = classroomRepo.findAll();
		; // Nivel que deseas buscar

		boolean levelExist = false;

		for (Classroom classroom : classroomList) {
			if (classroom.getLevel().getId() == level && classroom.getFilial().getId() == fid) {
				levelExist = true;
				break;
			}
		}

		return levelExist;
	}

}
