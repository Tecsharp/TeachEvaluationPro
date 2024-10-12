package com.tecsharp.teachevaluationpro.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tecsharp.teachevaluationpro.services.classroom.ClassroomService;
import com.tecsharp.teachevaluationpro.services.filial.FilialService;
import com.tecsharp.teachevaluationpro.utils.Cons;
import com.tecsharp.teachevaluationpro.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.repositories.exam.ExamRepository;
import com.tecsharp.teachevaluationpro.services.exam.ExamService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Controller
@RequestMapping("/app")
public class DashboardController {

    @Autowired
    UserService userService;

    @Autowired
    ExamRepository examRepo;

    @Autowired
    ExamService examService;

    @Autowired
    FilialService filialService;

    @Autowired
    ClassroomService classroomService;


    @GetMapping({"/dashboard"})
    public String englishLesson(HttpServletRequest req, Model model) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged)) {

            model.addAttribute("title", Cons.DASHBOARD_TITLE);
            model.addAttribute("user", user);
            if (user.getName() == null) {
                model.addAttribute("welcome", Utils.buildGreeting(Cons.GREETING_TEXT, user.getUsername()));
            } else {
                model.addAttribute("welcome", Utils.buildGreeting(Cons.GREETING_TEXT, user.getName()));
            }
            // MENSAJE DE BIENVENIDA Y ALERTA EN EL DASHBOARD
            // AGREGAR UN COMPROBADOR DE TIPO USUARIO
            model.addAttribute("alertPendingTeacher", Utils.randomStringSelectedFromArray(Cons.ALERTS_PENDING_TEACHERS));
            model.addAttribute("alertPendingStudent", Utils.randomStringSelectedFromArray(Cons.ALERTS_PENDING_STUDENTS));
            model.addAttribute("alertPendingAdmin", Utils.randomStringSelectedFromArray(Cons.ALERTS_PENDING_ADMINS));


            // ENVIO DE INFORMACIÓN DE HEADER BAR "SUCURSALES, AULAS, MAESTROS..."

            model.addAttribute("filialCount", filialService.getAllFilial().size());
            model.addAttribute("teacherCount", userService.getUserByType(Cons.TEACHER_USER_TYPE).size());
            model.addAttribute("classroomCount", classroomService.getAllClassroom().size());
            model.addAttribute("studentsCount", userService.getUserByType(Cons.STUDENT_USER_TYPE).size());

            List<Exam> examList = examRepo.findAll();
            model.addAttribute("examList", examList);

            return "dashboard";
        } else {

            return "redirect:/login";
        }

    }

}
