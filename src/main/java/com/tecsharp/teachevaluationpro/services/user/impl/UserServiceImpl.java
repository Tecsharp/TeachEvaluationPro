package com.tecsharp.teachevaluationpro.services.user.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import com.tecsharp.teachevaluationpro.controllers.LoginController;
import com.tecsharp.teachevaluationpro.models.*;
import com.tecsharp.teachevaluationpro.models.enums.Status;
import com.tecsharp.teachevaluationpro.repositories.init.InitRepository;
import com.tecsharp.teachevaluationpro.services.security.SecurityCryptService;
import com.tecsharp.teachevaluationpro.services.security.impl.SecurityCryptServiceImpl;
import com.tecsharp.teachevaluationpro.utils.crypt.Sha256;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.repositories.classroom.ClassroomRepository;
import com.tecsharp.teachevaluationpro.repositories.crud.CrudRepository;
import com.tecsharp.teachevaluationpro.repositories.filial.FilialRepository;
import com.tecsharp.teachevaluationpro.repositories.level.LevelRepository;
import com.tecsharp.teachevaluationpro.repositories.user.UserRepository;
import com.tecsharp.teachevaluationpro.repositories.userclassroom.UserClassroomRepository;
import com.tecsharp.teachevaluationpro.services.filial.FilialService;
import com.tecsharp.teachevaluationpro.services.level.LevelService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	CrudRepository crudRepo;

	@Autowired
	FilialRepository filialRepo;

	@Autowired
	LevelRepository levelRepo;

	@Autowired
	ClassroomRepository classroomRepo;

	@Autowired
	UserClassroomRepository userClassroomRepo;

	@Autowired
	SecurityCryptServiceImpl securityService;

	@Autowired
	InitRepository initRepository;

	@Override
	public User getUserByUsername(String username) {
		return userRepo.getUserByUsername(username);
	}

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	@Override
	public User findUserById(Long id) {

		return userRepo.getUserById(id);
	}

	@Override
	public List<User> getUsersByClassroom(Classroom classroom) {
		// SE FILTRAN LOS USARIOS POR ESTUDIANTES

		List<UserClassroom> userClassroomList = crudRepo.getUserListByClassroom(classroom);

		List<User> studentList = new ArrayList<User>();

		for (UserClassroom userClassList : userClassroomList) {

			if (userClassList.getUser().getType() == 3) {
				studentList.add(userClassList.getUser());
			}
		}

		return studentList;
	}

	@Override
	public List<User> getTeacherByClassroom(Classroom classroom) {
		List<UserClassroom> userClassroomList = crudRepo.getUserListByClassroom(classroom);

		List<User> newTeacherList = new ArrayList<User>();

		for (UserClassroom userList : userClassroomList) {

			if (userList.getUser().getType() == 2) {

				newTeacherList.add(userList.getUser());

			}

		}
		return newTeacherList;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public User createUser(Long fid, Long level, User user, Long classId) {

		user.setPassword(securityService.createPasswordHased(user.getPassword(), user.getUsername()));

		user.setFilial(filialRepo.getFilialById(fid));
		if (user.getType() == 3) {
			user.setLevel(levelRepo.findLevelById(level));
		} else if (user.getType() == 2) {
			user.setLevel(null);
		} else if (user.getType() == 1){
			user.setLevel(null);
		}

		userRepo.save(user);

		// RECUPERA EL ID DEL USUARIO AGREGADO
		User newUserSaved = getUserByUsername(user.getUsername());

		if(newUserSaved.getType() == 3) {
		// SE CREA UN NUEVO USERCLASSROOM
		UserClassroom userClassroom = new UserClassroom();
		// SE RELLENA EL USERCLASSROOM CON LOS DATOS DEL USUARIO
		userClassroom.setClassroom(classroomRepo.getClassroomById(classId));
		userClassroom.setUser(newUserSaved);

		// REGISTRA EL USUARIO - CLASSROOM
		userClassroomRepo.saveUserClassroom(userClassroom);
		}
		
		return newUserSaved;

	}

	@Override
	public List<User> getAllTeachersUnassigned(Long classId) {

		// Lista de todos los maestros
		List<User> teacherList = userRepo.findByType(2);

		// Recupera el objeto Classroom
		Classroom classroom = classroomRepo.getClassroomById(classId);

		// Lista de todos los maestros asignados
		List<UserClassroom> usersInClassroomId = userClassroomRepo.getUsersByClassroom(classroom);

		List<User> newTeacherListUnassigned = new ArrayList<>();

		for (User userList : teacherList) {
			boolean found = false;

			// Verifica si el maestro está en la lista de maestros asignados
			for (UserClassroom userAs : usersInClassroomId) {
				if (userList.getId() == userAs.getUser().getId()) {
					found = true;
					break;
				}
			}

			// Si no se encuentra en la lista de maestros asignados, agrégalo a la lista de
			// no asignados
			if (!found) {
				newTeacherListUnassigned.add(userList);
			}
		}

		return newTeacherListUnassigned;
	}

	@Override
	public void registerUserSelectedOnClassroom(Long classId,List<Long> teacherList) {

		Classroom classroom = classroomRepo.getClassroomById(classId);
		
		for(Long teacher : teacherList) {
			
			User user = userRepo.getUserById(teacher);
			
			UserClassroom userClassroom = new UserClassroom();
			userClassroom.setClassroom(classroom);
			userClassroom.setUser(user);

			userClassroomRepo.saveUserClassroom(userClassroom);
			
		}
		
		

	}

	@Override
	public void removeUserFromUserClassroom(Long userId, Long classroomId) {

		UserClassroom userClassroom = userClassroomRepo.getUserClassroomByUserAndClassroom(userRepo.getUserById(userId),
				classroomRepo.getClassroomById(classroomId));

		userClassroomRepo.removeUserClassroom(userClassroom);

	}

	@Override
	public List<User> getAllStudentsUnassigned(Long classId) {

		// Lista de todos los maestros
		List<User> studentList = userRepo.findByType(3);

		// Recupera el objeto Classroom
		Classroom classroom = classroomRepo.getClassroomById(classId);

		// Lista de todos los maestros asignados
		List<UserClassroom> usersInClassroomId = userClassroomRepo.getUsersByClassroom(classroom);

		List<User> newStudentListUnassigned = new ArrayList<>();

		for (User userList : studentList) {
			boolean found = false;

			// Verifica si el maestro está en la lista de maestros asignados
			for (UserClassroom userAs : usersInClassroomId) {
				if (userList.getId() == userAs.getUser().getId()) {
					found = true;
					break;
				}
			}

			// Si no se encuentra en la lista de maestros asignados, agrégalo a la lista de
			// no asignados
			if (!found) {
				newStudentListUnassigned.add(userList);
			}
		}

		return newStudentListUnassigned;
	}

	@Override
	public Boolean isUserExistVerify(User user) {

		User userUsername = userRepo.getUserByUsername(user.getUsername());
		User userEmail = userRepo.findByEmail(user.getEmail());

		if (userEmail != null) {
			return true;
		} else if (userUsername != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void registerStudentsByExcel(List<User> userList, Long fid, Long classId, Long lvl) {
		
		List<User> userListModified = new ArrayList<User>();
		
		Filial filial = filialRepo.getFilialById(fid);
		Classroom classroom = classroomRepo.getClassroomById(classId);
		Level level = levelRepo.findLevelById(lvl);
		
		// RECORRE LA LISTA PARA AGREGAR EL FILIAL Y LEVEL
		for(User user : userList) {

			// AGREGA EL HASHED AL PASSWORD
			user.setPassword(securityService.createPasswordHased(user.getPassword(), user.getUsername()));
			user.setFilial(filial);
			user.setLevel(level);
			user.setType(3);
			
			userListModified.add(user);
			
		}
		
		// SE RECORRE LA LISTA MIENTRAS VA AGREGANDO LOS USUARIOS
		for(User userMod : userListModified) {
			
			// SE GUARDA EL USUARIO
			userRepo.save(userMod);
			
			// SE RECUPERA ESE MISMO USUARIO PARA SACAR EL ID
			User userSaved = userRepo.getUserByUsername(userMod.getUsername());
			
			// SE CREA UN USER_CLASSROOM PARA CREAR LA RELACION
			UserClassroom userClassroom = new UserClassroom();
			userClassroom.setClassroom(classroom);
			userClassroom.setUser(userSaved);
			
			// SE REGISTRA LA RELACION USER Y CLASSROOM
			userClassroomRepo.saveUserClassroom(userClassroom);
			
		}
		
		
	}

	@Override
	public void mergeUserModify(User utom) {
		
		User userFind = findUserById(utom.getId());
		
		if (userFind != null) {
			userFind.setUsername(utom.getUsername());
			
			if (utom.getPassword() != "") {
				userFind.setPassword(utom.getPassword());
			} 
			
			userFind.setName(utom.getName());
			userFind.setLastnameone(utom.getLastnameone());
			userFind.setLastnametwo(utom.getLastnametwo());
			userFind.setEmail(utom.getEmail());

		}
		
		
		crudRepo.mergeUser(userFind);
		
	}

	@Override
	public List<User> getUserByType(Integer userType) {
		return userRepo.findByType(userType);
	}

	@Override
	public void createAdminUser(String username, String password) {

		// ENVIA LA CONTRASEÑA PARA REGISTRARLA EN EL HASH
		User userAdmin = new User();
		userAdmin.setId(0L);
		userAdmin.setUsername(username);
		userAdmin.setPassword(securityService.createPasswordHased(password, username));
		userAdmin.setName(null);
		userAdmin.setLastnameone(null);
		userAdmin.setLastnametwo(null);
		userAdmin.setFilial(filialRepo.getFilialById(1L));
		userAdmin.setEmail(null);
		userAdmin.setPoints(null);
		userAdmin.setType(1);
		userAdmin.setLevel(null);

		// GUARDA EL USUARIO EN BBDD
		userRepo.save(userAdmin);

		// SE CREA EL INIT CON VALOR REGISTERED = 1
		Init init = new Init();
		init.setId(1L);
		init.setRegistered(Status.REGISTERED); // Asigna el valor del enum

		// SE GUARDA EL INIT
		initRepository.save(init);

	}


}
