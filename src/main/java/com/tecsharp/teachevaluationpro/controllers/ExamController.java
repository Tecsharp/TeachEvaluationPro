package com.tecsharp.teachevaluationpro.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.ExamType;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.UserSubject;
import com.tecsharp.teachevaluationpro.repositories.evaluation.EvaluationRepository;
import com.tecsharp.teachevaluationpro.repositories.exam.ExamRepository;
import com.tecsharp.teachevaluationpro.repositories.examtype.ExamTypeRepository;
import com.tecsharp.teachevaluationpro.repositories.usersubject.UserSubjectRepository;
import com.tecsharp.teachevaluationpro.services.evaluation.EvaluationService;
import com.tecsharp.teachevaluationpro.services.exam.ExamService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Controller
@RequestMapping("/")
public class ExamController {

	@Autowired
	UserService userService;

	@Autowired
	ExamService examService;

	@Autowired
	UserSubjectRepository userSubRepo;

	@Autowired
	ExamTypeRepository examTypeRepo;

	@Autowired
	EvaluationRepository evaluationRepo;

	@Autowired
	EvaluationService evaluationService;

	@Autowired
	ExamRepository examRepo;

	@GetMapping({ "/app/exam-creator" })
	public String examCreator(HttpServletRequest req, Model model, Boolean nameExist) {

		// SE EXTRAE DE LA SESION EL NOMBRE DE USUARIO, SI ES QUE EXISTE Y COINCIDE
		// PUEDE USAR EL CREADOR DE EXAMENES
		String userLogged = (String) req.getSession().getAttribute("USERNAME");
		User user = userService.getUserByUsername(userLogged);
		if (user != null && user.getUsername().equals(userLogged)) {

			// ENVIA OBJETO DEL USUARIO A LA VISTA
			model.addAttribute("user", user);

			// ENVIA DE LA LISTA DE MATERIAS QUE HAN SIDO ASIGNADOS AL USUARIO MAESTRO
			// PARA USAR EN FORMULARIO
			List<UserSubject> userSubjectList = userSubRepo.getUserSubjectListById(user.getId());
			model.addAttribute("userSubjectList", userSubjectList);

			// ENVIA LOS TIPOS DE EXAMENES A LA VISTA PARA FORMULARIO
			List<ExamType> examTypeList = examTypeRepo.findAll();
			model.addAttribute("examTypeList", examTypeList);

			return "exam-creator";
		} else

		{
			return "redirect:/login";
		}
	}

	@PostMapping({ "/app/exam-creator" })
	public String examCreatorPost(HttpServletRequest req, Model model, String examName, Integer numberQuestions,
			Long examSubject, Long examType, String initDate, String initTime, String endDate, String endTime) {

		String userLogged = (String) req.getSession().getAttribute("USERNAME");
		User user = userService.getUserByUsername(userLogged);
		if (user != null && user.getUsername().equals(userLogged)) {

			// SE COMPRUEBA QUE EL NOMBRE DEL EXAMEN NO EXISTA

			req.getSession().setAttribute("examName", examName);
			req.getSession().setAttribute("numberQuestions", numberQuestions);
			req.getSession().setAttribute("examSubject", examSubject);
			req.getSession().setAttribute("examType", examType);

			// SE ENVIAN LOS DATOS DEL FORMULARIO AL SERVICE PARA CREAR EL EXAMEN SIN
			// PREGUNTAS
			Exam exam = examService.createExam(examName, numberQuestions, examSubject, examType, user.getId());

			// SE DA FORMATO A LAS FECHAS
			examService.convertStringToDateAndSave(initDate, initTime, endDate, endTime, exam);

			// SE OBTIENE MANDA EL ID DEL EXAMEN AL REQUEST PARA TENERLO LISTO
			// Y GUARDAR LAS PREGUNTAS DESPUES
			req.getSession().setAttribute("examId", exam.getId());

			// INICIO DEL NUMERO ITERATOR QUE COMIENZA EN 1 PARA LLEVAR CONTEO
			// DEL NUMERO DE PREGUNTA EN LA CREACION DE PREGUNTAS
			req.getSession().setAttribute("numberQuestIterator", 1);

			return "redirect:/app/exam-creator-evaluation";

		} else

		{
			return "redirect:/login";
		}

	}

	@GetMapping({ "/app/exam-creator-evaluation" })
	public String examCreatorEvaluation(HttpServletRequest req, Model model) {

		String userLogged = (String) req.getSession().getAttribute("USERNAME");
		User user = userService.getUserByUsername(userLogged);
		if (user != null && user.getUsername().equals(userLogged)) {
			model.addAttribute("error", false);
			
			model.addAttribute("user", user);
			
			String examName = (String) req.getSession().getAttribute("examName");
			Integer numberQuestions = (Integer) req.getSession().getAttribute("numberQuestions");
			Long examSubject = (Long) req.getSession().getAttribute("examSubject");
			Long examType = (Long) req.getSession().getAttribute("examType");

			// SE ASIGNA EL NUMERO ITERATOR A UN INTEGER PARA PODERLO MOSTRAR EN LA VISTA
			// EN LA PARTE QUE MUESTRA EL NUMERO DE PREGUNTA
			Integer numberQuestIterator = (Integer) req.getSession().getAttribute("numberQuestIterator");

			model.addAttribute("numberQuest", "Pregunta número: " + numberQuestIterator + "/" + numberQuestions);

			return "evaluation-creator";
		} else {

			return "redirect:/login";
		}
	}

	@PostMapping({ "/app/exam-creator-evaluation" })
	public String examCreatorEvaluationPost(HttpServletRequest req, Model model, String question, String firstAns,
			String secondAns, String thirdAns, String fourthAns, Double score, String correctAnswer) {

		String userLogged = (String) req.getSession().getAttribute("USERNAME");
		User user = userService.getUserByUsername(userLogged);
		if (user != null && user.getUsername().equals(userLogged)) {
			Integer numberQuestIterator = (Integer) req.getSession().getAttribute("numberQuestIterator");
			Integer numberQuestions = (Integer) req.getSession().getAttribute("numberQuestions");
			Long examId = (Long) req.getSession().getAttribute("examId");

			// VERIFICA QUE EL NUMERO DE ITERACION SEA MENOR AL NUMERO DE PREGUNTAS
			// REQUERIDAS
			if (numberQuestIterator < numberQuestions) {
				// SE ENVIA AL SERVICE PARA REGISTRAR LA PREGUNTA AL EXAMEN
				evaluationService.buildEvaluation(question, firstAns, secondAns, thirdAns, fourthAns, score,
						correctAnswer, examId);

				// EL NUMERO ITERATOR SE VA SUMANDO CADA VEZ QUE PASA, ESTE MISMO SE OBTIENE Y
				// SE ENVIA MODIFICADO PARA SEGUIR ITERANDOLO HASTA QUE LLEGUE AL NUMERO TOTAL
				// DE PREGUNTAS REQUERIDAS
				numberQuestIterator++;
				req.getSession().setAttribute("numberQuestIterator", numberQuestIterator);

				// CUANDO EL NUMERO ITERATOR LLEGA AL MISMO VALOR DEL NUMERO DE PREGUNTAS
				// REQUERIDO
				// DEJA DE ITERAR, SE SALE Y SE REDIRECCIONA A LA VISTA DE EXAMENES DESPUES DE
				// GUARDAR LAS PREGUNTAS
			} else if (numberQuestIterator.equals(numberQuestions)) {
				// SE ENVIA AL SERVICE PARA REGISTRAR LA PREGUNTA AL EXAMEN
				evaluationService.buildEvaluation(question, firstAns, secondAns, thirdAns, fourthAns, score,
						correctAnswer, examId);

				// SE REALIZA LA SUMATORIA DE SCORE
				evaluationService.sumEvaluationScoreToSaveExam(examId);

				//AGREGAR ESTA VISTA
				return "redirect:/app/dashboard";
			}

			return "redirect:/app/exam-creator-evaluation";
		} else {
			return "redirect:/login";
		}
	}
	
	
	
	

}
