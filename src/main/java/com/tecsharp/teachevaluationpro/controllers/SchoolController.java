package com.tecsharp.teachevaluationpro.controllers;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.services.classroom.ClassroomService;
import com.tecsharp.teachevaluationpro.services.crud.CrudService;
import com.tecsharp.teachevaluationpro.services.filial.FilialService;
import com.tecsharp.teachevaluationpro.services.grade.GradeService;
import com.tecsharp.teachevaluationpro.services.level.LevelService;
import com.tecsharp.teachevaluationpro.services.subject.SubjectService;
import com.tecsharp.teachevaluationpro.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/app")
@Controller
public class SchoolController {

    @Autowired
    UserService userService;

    @Autowired
    FilialService filialService;

    @Autowired
    CrudService crudService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    LevelService levelService;

    @Autowired
    GradeService gradeService;

    @Autowired
    SubjectService subjectService;

    @GetMapping({ "/admin/school" })
    public String adminSchoolView(HttpServletRequest req, Model model, Integer id) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {

            try {
                model.addAttribute("title", "Administrar escuela");

                // SE ENVIA EL USUARIO A LA LISTA
                model.addAttribute("user", user);

                // ENVIA LA LISTA DE FILIAL CON NUMERO DE AULAS Y ESTUDIANTES A LA VISTA
                model.addAttribute("filialList", filialService.getFilialListBySchool());
                model.addAttribute("filialNames",
                        "Tus sucursales en " + filialService.getFilialListBySchool().get(0).getSchool().getName());
                model.addAttribute("schoolName", filialService.getFilialListBySchool().get(0).getSchool().getName());

                // ENVIA EL NUMERO DE AULAS Y NUMERO DE ESTUDIANTES

                return "admin/adminschool";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping({ "/admin/school/filial" })
    public String adminSchoolFilialView(HttpServletRequest req, Model model, Long id) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {
                model.addAttribute("title", "Administrar escuela");
                model.addAttribute("filialId", id);

                // COMPRUEBA SI HAY AULAS EN LOS DISTINTOS NIVELES PARA MOSTRAR LOS NIVELES EN
                // VISTA
                Boolean levelOneExist = levelService.classroomLevelExist(1L, id); // PRIMARIA
                Boolean levelTwoExist = levelService.classroomLevelExist(2L, id); // SECUNDARIA
                Boolean levelThreeExist = levelService.classroomLevelExist(3L, id); // PREPARATORIA
                Boolean levelFourExist = levelService.classroomLevelExist(4L, id); // UNIVERSIDAD

                model.addAttribute("levelOneExist", levelOneExist);
                model.addAttribute("levelTwoExist", levelTwoExist);
                model.addAttribute("levelThreeExist", levelThreeExist);
                model.addAttribute("levelFourExist", levelFourExist);

                // ENVIO DE USUARIO A LA VISTA
                model.addAttribute("user", user);
                List<Filial> list = filialService.getFilialListBySchool();

                Integer n = list.size();

                // ENVIA LA LISTA DE FILIAL A LA VISTA
                int newId = n - 1;
                if (id <= n) {
                    model.addAttribute("nameFilial", list.get(newId).getName());

                    model.addAttribute("levelList", levelService.getAllLevels());

                    // OBTIENE LOS NIVELES ACTIVOS

                    // MANDA EL NUMERO DE ESTUDIANTES

                    model.addAttribute("filialName",
                            "Niveles de " + list.get(newId).getName() + " en " + list.get(newId).getSchool().getName());
                    model.addAttribute("schoolName", list.get(newId).getSchool().getName());

                }

                return "admin/adminfilial";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping({ "/admin/school/filial/level" })
    public String adminSchoolLevelView(HttpServletRequest req, Model model, Integer id, Long fid, Boolean aDDS) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {
                model.addAttribute("title", "Administrar escuela");

                // SE ENVIA EL USUARIO A LA VISTA
                model.addAttribute("user", user);

                // SE ENVIA EL FILIAL ID AL MODELO
                model.addAttribute("filialId", id);

                // INFORMACION DEL PATH
                String schoolName = filialService.getFilialListBySchool().get(0).getSchool().getName();
                model.addAttribute("schoolName", schoolName);

                String filialName = filialService.getFilialById(fid).getName();

                // ENVIA LA LISTA DE FILIAL A LA VISTA
                if (id == 1) {

                    Level level = levelService.getLevelById(1L);
                    model.addAttribute("path", schoolName + " / " + filialName + " / " + level.getName());
                    model.addAttribute("classroomList",
                            classroomService.getClassroomByLevelAndFilial(level, filialService.getFilialById(fid)));
                    model.addAttribute("aDDS", aDDS);

                }
                if (id == 2) {

                    // SE OBTIENE EL GRADE CON EL ID Y SE OBTIENE LA LISTA DE AULAS
                    Level level = levelService.getLevelById(2L);
                    model.addAttribute("path", schoolName + " / " + filialName + " / " + level.getName());
                    model.addAttribute("classroomList",
                            classroomService.getClassroomByLevelAndFilial(level, filialService.getFilialById(fid)));
                    model.addAttribute("aDDS", aDDS);
                } else if (id == 3) {

                    Level level = levelService.getLevelById(3L);
                    model.addAttribute("path", schoolName + " / " + filialName + " / " + level.getName());
                    model.addAttribute("classroomList",
                            classroomService.getClassroomByLevelAndFilial(level, filialService.getFilialById(fid)));
                    model.addAttribute("aDDS", aDDS);

                } else if (id == 4) {
                    Level level = levelService.getLevelById(4L);
                    model.addAttribute("path", schoolName + " / " + filialName + " / " + level.getName());
                    model.addAttribute("classroomList",
                            classroomService.getClassroomByLevelAndFilial(level, filialService.getFilialById(fid)));
                    model.addAttribute("aDDS", aDDS);

                }

                return "admin/adminlevel";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping({ "/admin/school/filial/level/classroom" })
    public String adminSchoolClassroomLevelView(HttpServletRequest req, Model model, Long clssid, Long fid,
                                                Boolean uAE) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {
                // SE ENVIA EL USUARIO A LA VISTA
                model.addAttribute("user", user);

                // SE BUSCA EL CLASSROOM CON LA ID
                Classroom classroom = classroomService.getClassroomById(clssid);
                model.addAttribute("classroom", classroom);

                // MANDA A LA VISTA EL NUMERO DE ESTUDIANTES

                model.addAttribute("studentList", userService.getUsersByClassroom(classroom));

                try {
                    List<User> teachers = userService.getTeacherByClassroom(classroom);
                    model.addAttribute("teachers", teachers);

                } catch (Exception e) {

                    User teacherVoid = null;
                    model.addAttribute("teacher", teacherVoid);

                }

                // SE ENVIA EL NOMBRE DE LA VISTA
                model.addAttribute("title", "Aula " + classroom.getName());

                model.addAttribute("classroomHead", "Información de aula");

                model.addAttribute("uAE", uAE);
                return "admin/adminclassroom";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }

        } else {
            return "redirect:/login";
        }
    }
}
