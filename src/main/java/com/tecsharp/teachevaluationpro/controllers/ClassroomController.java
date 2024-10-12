package com.tecsharp.teachevaluationpro.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tecsharp.teachevaluationpro.utils.Cons;
import com.tecsharp.teachevaluationpro.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.classroom.ClassroomRepository;
import com.tecsharp.teachevaluationpro.repositories.userclassroom.UserClassroomRepository;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Controller
public class ClassroomController {

	@Autowired
	UserService userService;
	
	@Autowired
	ClassroomRepository classroomRepo;
	
	@Autowired
	UserClassroomRepository userClassroomRepo;
	
	@GetMapping({ "/app/classrooms" })
	public String classroomMainView(HttpServletRequest req, Model model) {
		String userLogged = (String) req.getSession().getAttribute("USERNAME");
		User user = userService.getUserByUsername(userLogged);
		if (user != null && user.getUsername().equals(userLogged)) {
			try {
			
			// SE OBTIENE LA LISTA DE TODOS LOS CLASSROOM Y SE CREA UNA NUEVA LISTA
			// PARA Y CADA VEZ QUE EL USUARIO CONINCIDA, EL CLASSROOM SE METE A UNA NUEVA
			// LISTA 
			List<Classroom> classroomList = new ArrayList<>();
			model.addAttribute("classroomList", classroomList);
			
			
			
			// ENVIO DE TEXTOS A LA VISTA
			model.addAttribute("title", Cons.DASHBOARD_TITLE);
			model.addAttribute("user", user);
			model.addAttribute("welcome", Cons.GREETING_TEXT + " " + user.getName());
			 
			model.addAttribute("alertPendingTeacher", Utils.randomStringSelectedFromArray(Cons.ALERTS_PENDING_TEACHERS));
			model.addAttribute("alertPendingStudent", Utils.randomStringSelectedFromArray(Cons.ALERTS_PENDING_STUDENTS));
			model.addAttribute("alertPendingAdmin", Utils.randomStringSelectedFromArray(Cons.ALERTS_PENDING_ADMINS));
			

			return "classroom";
			}catch (Exception error) {
				model.addAttribute("error", Cons.GENERIC_ERROR_TEXT + error);
				return "404";
			}
		} else {

			return "redirect:/login";
		}
	}
	
	@GetMapping({ "/app/classrooms-detail" })
	public String classroomDetailView(HttpServletRequest req, Model model, Long idClassroom) {
		String userLogged = (String) req.getSession().getAttribute("USERNAME");
		User user = userService.getUserByUsername(userLogged);
		if (user != null && user.getUsername().equals(userLogged)) {
			try {
			// SE BUSCA LA INFORMACION DEL CLASSROOM
			
			List<User> studentList = userClassroomRepo.getStudentsByClassroomId(idClassroom);
			model.addAttribute("studentList", studentList);
			model.addAttribute("classroomDetailText", "Detalles del aula: " + classroomRepo.getClassroomById(idClassroom).getName());
			

			return "classroom-detail";
			}catch (Exception error) {
				model.addAttribute("error", Cons.GENERIC_ERROR_TEXT + error);
				return "404";
			}
		} else {

			return "redirect:/login";
		}
	}
}
