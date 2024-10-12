package com.tecsharp.teachevaluationpro.services.filial.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Grade;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.School;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.crud.CrudRepository;
import com.tecsharp.teachevaluationpro.repositories.filial.FilialRepository;
import com.tecsharp.teachevaluationpro.repositories.grade.GradeRepository;
import com.tecsharp.teachevaluationpro.repositories.level.LevelRepository;
import com.tecsharp.teachevaluationpro.services.crud.CrudService;
import com.tecsharp.teachevaluationpro.services.filial.FilialService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Service
public class FilialServiceImpl implements FilialService {

	@Autowired
	FilialRepository filialRepo;

	@Autowired
	LevelRepository levelRepo;

	@Autowired
	CrudRepository crudRepo;

	@Autowired
	GradeRepository gradeRepo;

	@Autowired
	UserService userService;

	@Override
	public void createFilial(Filial filial) {

		filial.setId(0L);
		filial.setSchool(crudRepo.getSchoolById());
		// filial.setLevel(levelRepo.findLevelById(filial.getLevel().getId()));

		filialRepo.saveFilial(filial);

	}

	@Override
	public List<Filial> createListFilialOfSchool(School school) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filial getFilialById(Long id) {
		// TODO Auto-generated method stub
		return filialRepo.getFilialById(id);
	}

	@Override
	public Filial getFilialByName(String name) {
		// TODO Auto-generated method stub
		return filialRepo.getFilialByName(name);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public List<Filial> getFilialListBySchool() {

		School school = crudRepo.getSchoolById();

		List<Filial> newFilialList = new ArrayList<Filial>();

		for (Filial filialList : school.getFilials()) {

			newFilialList.add(filialList);

		}

		// AGREGA EL NUMERO DE AULAS A LA LISTA
		for (Filial listFil : newFilialList) {

			listFil.setNumClassrooms(crudRepo.getClassroomListByFilial(listFil).size());
			List<User> newUserList = new ArrayList<User>();
			List<User> userList = userService.findAll();

			// AGREGA LA CANTIDAD DE USUARIOS A LA LISTA
			for (User usrlst : userList) {

				if (usrlst.getFilial().equals(listFil)) {
					if (usrlst.getType() == 3) {
						newUserList.add(usrlst);
					}

					listFil.setNumStudents(newUserList.size());

				}

			}

		}

		return newFilialList;
	}

	@Override
	public void removeFilial(Long fid) {
		
		filialRepo.removeFilial(filialRepo.getFilialById(fid));
	}

	@Override
	public List<Filial> getAllFilial() {
		return filialRepo.getAllFilial();
	}

}
