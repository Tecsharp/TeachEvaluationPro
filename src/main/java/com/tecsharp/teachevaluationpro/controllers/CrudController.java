package com.tecsharp.teachevaluationpro.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tecsharp.teachevaluationpro.utils.Cons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tecsharp.teachevaluationpro.models.Classroom;
import com.tecsharp.teachevaluationpro.models.Filial;
import com.tecsharp.teachevaluationpro.models.Level;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.services.classroom.ClassroomService;
import com.tecsharp.teachevaluationpro.services.filial.FilialService;
import com.tecsharp.teachevaluationpro.services.grade.GradeService;
import com.tecsharp.teachevaluationpro.services.level.LevelService;
import com.tecsharp.teachevaluationpro.services.subject.SubjectService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Controller
public class CrudController {

    @Autowired
    UserService userService;

    @Autowired
    FilialService filialService;

    @Autowired
    ClassroomService classroomService;

    @Autowired
    LevelService levelService;

    @Autowired
    GradeService gradeService;

    @Autowired
    SubjectService subjectService;

    @GetMapping({"/app/filial-crud"})
    public String crudMainFilialView(HttpServletRequest req, Model model) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {
                // ENVIA EL NOMBRE DE LA ESCUELA
                String schoolName = filialService.getFilialListBySchool().get(0).getSchool().getName();
                model.addAttribute("schoolName", schoolName);

                // SE ENVIA EL USUARIO A LA VISTA
                model.addAttribute("user", user);

                model.addAttribute("title", Cons.REGISTER_MODULE_TITLE);

                model.addAttribute("filialList", filialService.getFilialListBySchool());
                model.addAttribute("filialNames", "Selecciona una sucursal de "
                        + filialService.getFilialListBySchool().get(0).getSchool().getName());
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
            return "admin/crud-select-filial";
        } else {

            return "redirect:/login";
        }

    }

    @GetMapping({"/app/crud"})
    public String crudMainView(HttpServletRequest req, Model model, Long fid, Boolean tAE, Boolean cAS) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {
                if (fid == null) {
                    model.addAttribute("error", Cons.INVALID_URL);
                    return "404";
                }

                model.addAttribute("tAE", tAE);
                model.addAttribute("cAS", cAS);

                // ENVIA AL MODELO EL FILIAL ID
                model.addAttribute("fid", fid);

                // SE ENVIA EL USUARIO A LA VISTA
                model.addAttribute("user", user);

                model.addAttribute("title", Cons.REGISTER_MODULE_TITLE);

                return "admin/crud";
            } catch (Exception e) {
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @GetMapping({"/app/crud/rfil"})
    public String removeFilial(HttpServletRequest req, Model model, Long fid) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {

                filialService.removeFilial(fid);

                return "redirect:/app/filial-crud";
            } catch (Exception e) {
                model.addAttribute("error", Cons.DELETE_FILIAL_ERROR_TEXT);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @GetMapping({"/app/crud-register"})
    public String crudRegisterMainView(HttpServletRequest req, Model model, Integer id, Long fid, Long lvl,
                                       Long classId, Boolean uE, Long utomId, Boolean cAS) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {

            try {
                // SE ENVIA EL FILIAL ID AL MODELO
                model.addAttribute("fid", fid);

                // SE ENVIA EL USUARIO A LA VISTA
                model.addAttribute("user", user);
                model.addAttribute("title", Cons.REGISTER_MODULE_TITLE);

                switch (id) {


                    case 1:
                        Filial filial = new Filial();
                        model.addAttribute("filial", filial);
                        model.addAttribute("title", Cons.REGISTER_FILIAL_TITLE);
                        model.addAttribute("idType", id);
                        break;

                    case 2:

                        User userVoid = new User();
                        userVoid.setFilial(new Filial());
                        userVoid.setLevel(new Level());
                        model.addAttribute("userVoid", userVoid);
                        model.addAttribute("title", Cons.REGISTER_STUDENT_TITLE);
                        model.addAttribute("headerFormText", Cons.FORM_REGISTER_STUDENT_HEADER_TEXT);
                        model.addAttribute("idType", id);

                        // ENVIA FID AL REQ
                        model.addAttribute("fid", fid);

                        // ENVIA LEVELID AL REQ
                        model.addAttribute("lvl", lvl);

                        // ENVIA CLASSID AL REQ
                        model.addAttribute("classId", classId);

                        // ENVIO DE LISTA FILIAL
                        model.addAttribute("filialList", filialService.getFilialListBySchool());
                        model.addAttribute("gradeList", gradeService.getAllGrades());

                        // ENVIA LA LISTA DE ALUMNOS EN GENERAL QUE NO ESTEN ASIGNADOS A UN AULA
                        model.addAttribute("studentsList", userService.getAllStudentsUnassigned(classId));

                        // ENVIA EL ERROR DE QUE EL USUARIO YA EXISTE
                        model.addAttribute("uE", uE);
                        break;

                    case 3:
                        User userVoid2 = new User();
                        userVoid2.setFilial(new Filial());
                        userVoid2.setLevel(new Level());
                        model.addAttribute("userVoid", userVoid2);
                        model.addAttribute("title", Cons.REGISTER_TEACHER_TO_CLASSROOM_TITLE);
                        model.addAttribute("headerFormText", Cons.FORM_REGISTER_TEACHER_HEADER_TEXT);
                        model.addAttribute("idType", id);

                        // ENVIA FID AL REQ
                        model.addAttribute("fid", fid);

                        // ENVIA LEVELID AL REQ
                        model.addAttribute("lvl", lvl);

                        // ENVIA CLASSID AL REQ
                        model.addAttribute("classId", classId);

                        // ENVÍO DE LISTA FILIAL
                        model.addAttribute("filialList", filialService.getFilialListBySchool());
                        model.addAttribute("gradeList", gradeService.getAllGrades());

                        // ENVIA LA LISTA DE MAESTROS EN GENERAL QUE NO ESTEN ASIGNADOS AL AULA
                        model.addAttribute("teacherList", userService.getAllTeachersUnassigned(classId));

                        // ENVIA EL ERROR DE QUE EL USUARIO YA EXISTE
                        model.addAttribute("uE", uE);
                        break;

                    case 4:
                        model.addAttribute("title", Cons.REGISTER_STUDENT_WITH_EXCEL_TITLE);
                        model.addAttribute("headerFormText", Cons.FORM_UPLOAD_EXCEL_TEXT);
                        model.addAttribute("idType", id);

                        // ENVIA FID AL REQ
                        model.addAttribute("fid", fid);

                        // ENVIA LEVELID AL REQ
                        model.addAttribute("lvl", lvl);

                        // ENVIA CLASSID AL REQ
                        model.addAttribute("classId", classId);
                        // REDIRIGIE A LA VISTA PARA AGREGAR LOS ESTUDIANTES MEDIANTE EXCEL
                        return "admin/excel-register";

                    case 5:
                        // SE ENVIA A UNA PLANTILLA PARA SELECCIONAR EL NIVEL
                        model.addAttribute("cAS", cAS);
                        return "admin/crud-register-classroom";


                    case 6:
                        // MODIFICA EL ESTUDIANTE DESDE EL CLASSROOM
                        // ENVIA EL USUARIO A MODIFICAR A LA VISTA

                        model.addAttribute("utom", userService.findUserById(utomId));
                        model.addAttribute("title", Cons.MODIFY_STUDENT_TITLE);
                        model.addAttribute("headerFormText", Cons.MODIFY_STUDENT_DATA_TEXT);
                        model.addAttribute("idType", id);

                        // ENVIA FID AL REQ
                        model.addAttribute("fid", fid);

                        // ENVIA LEVELID AL REQ
                        model.addAttribute("lvl", lvl);

                        // ENVIA CLASSID AL REQ
                        model.addAttribute("classId", classId);
                        break;

                    case 7:

                        model.addAttribute("title", Cons.MODIFY_CLASSROM_TITLE);

                        model.addAttribute("idType", id);

                        // SE ENVIA EL OBJETO CLASSROOM A LA VISTA PARA EDITAR
                        Classroom classroomObj = classroomService.getClassroomById(classId);
                        model.addAttribute("classroom", classroomObj);

                        model.addAttribute("gradeList", gradeService
                                .getAllGradesByLevel(levelService.getLevelById(classroomObj.getLevel().getId())));
                        break;

                    case 9:

                        // ENVIA EL USUARIO A MODIFICAR A LA VISTA

                        model.addAttribute("utom", userService.findUserById(utomId));
                        model.addAttribute("title", Cons.MODIFY_TEACHER_TITLE);
                        model.addAttribute("headerFormText", Cons.MODIFY_TEACHER_DATA_TEXT);
                        model.addAttribute("idType", id);

                        // ENVIA FID AL REQ
                        model.addAttribute("fid", fid);

                        // ENVIA LEVELID AL REQ
                        model.addAttribute("lvl", lvl);

                        // ENVIA CLASSID AL REQ
                        model.addAttribute("classId", classId);
                        break;

                    // RECIBE A LAS AULAS

                    case 11:

                        model.addAttribute("classroom", new Classroom());
                        Filial filial2 = filialService.getFilialById(fid);

                        model.addAttribute("title", filial2.getName() + Cons.REGISTER_CLASSROOM_PRIMARIA_TITLE);
                        model.addAttribute("headerFormText", Cons.FORM_REGISTER_CLASSROOM_HEADER_TEXT);
                        model.addAttribute("idType", id);

                        // MANDA LOS GRADOS DE PRIMARIA
                        model.addAttribute("gradeList", gradeService.getAllGradesByLevel(levelService.getLevelById(1L)));
                        break;

                    case 12:
                        model.addAttribute("classroom", new Classroom());
                        Filial filial3 = filialService.getFilialById(fid);
                        model.addAttribute("title", filial3.getName() + Cons.REGISTER_CLASSROOM_SECUNDARIA_TITLE);
                        model.addAttribute("headerFormText", Cons.FORM_REGISTER_CLASSROOM_HEADER_TEXT);
                        model.addAttribute("idType", id);

                        // MANDA LOS GRADOS DE PRIMARIA
                        model.addAttribute("gradeList", gradeService.getAllGradesByLevel(levelService.getLevelById(2L)));
                        break;

                    case 13:
                        model.addAttribute("classroom", new Classroom());
                        Filial filial4 = filialService.getFilialById(fid);
                        model.addAttribute("title", filial4.getName() + Cons.REGISTER_CLASSROOM_PREPARATORIA_TITLE);
                        model.addAttribute("headerFormText", Cons.FORM_REGISTER_CLASSROOM_HEADER_TEXT);
                        model.addAttribute("idType", id);

                        // MANDA LOS GRADOS DE PRIMARIA
                        model.addAttribute("gradeList", gradeService.getAllGradesByLevel(levelService.getLevelById(3L)));
                        break;

                    case 14:
                        model.addAttribute("classroom", new Classroom());
                        Filial filial5 = filialService.getFilialById(fid);
                        model.addAttribute("title", filial5.getName() + " / Registrar aula en universidad");
                        model.addAttribute("headerFormText", Cons.FORM_REGISTER_CLASSROOM_HEADER_TEXT);
                        model.addAttribute("idType", id);

                        // MANDA LOS GRADOS DE PRIMARIA
                        model.addAttribute("gradeList", gradeService.getAllGradesByLevel(levelService.getLevelById(4L)));
                        break;

                    case 15:

                        User userVoid3 = new User();
                        userVoid3.setFilial(new Filial());
                        userVoid3.setLevel(new Level());
                        model.addAttribute("userVoid", userVoid3);
                        model.addAttribute("title", Cons.REGISTER_TEACHER_TITLE);
                        model.addAttribute("headerFormText", Cons.FORM_REGISTER_TEACHER_HEADER_TEXT);
                        model.addAttribute("idType", id);

                        // ENVIA FID AL REQ
                        model.addAttribute("fid", fid);

                        // ENVIA LEVELID AL REQ
                        model.addAttribute("lvl", lvl);

                        // ENVIA EL ERROR DE QUE EL USUARIO YA EXISTE
                        model.addAttribute("uE", uE);

                        // SE ENVIA EL LISTADO DE MATERIAS
                        model.addAttribute("subjectList", subjectService.getSubjectAll());
                        break;

                    default:
                        System.out.println(Cons.INVALID_OPTION);
                }

                return "admin/crud-register";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping({"/app/crud-register/filial"})
    public String crudRegister(HttpServletRequest req, Model model, Integer id, @ModelAttribute Filial filial) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {
                model.addAttribute("title", Cons.REGISTER_MODULE_TITLE);

                if (id == 1) {

                    filialService.createFilial(filial);

                }

                return "redirect:/app/filial-crud";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping({"/app/crud-register/student"})
    public String crudRegisterStudent(HttpServletRequest req, Model model, @ModelAttribute User user,
                                      Long lvl, Long fid, Long classId) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user2 = userService.getUserByUsername(userLogged);
        if (user != null && user2.getUsername().equals(userLogged) && user2.getType() == 1) {
            try {

                if (!user.getEmail().contains("@")) {
                    return "redirect:/app/crud-register?id=2&classId=" + classId + "&fid=" + fid + "&lvl=" + lvl
                            + "&uE=true";
                }

                // VERIFICA QUE EL USUARIO NO ESTE CREADO YA
                Boolean userExist = userService.isUserExistVerify(user);

                if (!userExist) {
                    // CREA EL USUARIO PARA REGISTRARLO EN EL CLASSROOM
                    userService.createUser(fid, lvl, user, classId);
                } else {
                    // MARCA ERROR
                    return "redirect:/app/crud-register?id=2&classId=" + classId + "&fid=" + fid + "&lvl=" + lvl
                            + "&uE=true";
                }

                return "redirect:/app/admin/school/filial/level/classroom?clssid=" + classId + "&fid=" + fid;
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping({"/app/crud-register/teacher"})
    public String crudRegisterTeacher(HttpServletRequest req, Model model, @ModelAttribute User user,
                                      Long fid, @RequestParam(name = "selectedSubjects", required = false) List<Long> selectedSubjects) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user2 = userService.getUserByUsername(userLogged);
        if (user != null && user2.getUsername().equals(userLogged) && user2.getType() == 1) {
            try {

                if (!user.getEmail().contains("@")) {

                    return "redirect:/app/crud-register?id=15&fid=" + fid + "&uE=true";
                }

                // VERIFICA QUE EL USUARIO NO ESTE CREADO YA
                Boolean userExist = userService.isUserExistVerify(user);

                if (!userExist) {
                    // CREA EL USUARIO PARA REGISTRARLO EN EL FILIAL
                    User userSaved = userService.createUser(fid, null, user, null);

                    subjectService.registerUserSubject(userSaved, selectedSubjects);

                } else {
                    // MARCA ERROR

                    return "redirect:/app/crud-register?id=15&fid=" + fid + "&uE=true";
                }
                model.addAttribute("tAE", true);
                return "redirect:/app/crud?fid=" + fid + "&tAE=true";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping({"/app/crud-register/modify-user"})
    public String crudModifyUser(HttpServletRequest req, Model model, @ModelAttribute User user,
                                 Long fid, Long classId, Integer pathId) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user2 = userService.getUserByUsername(userLogged);
        if (user != null && user2.getUsername().equals(userLogged) && user2.getType() == 1) {
            try {

                userService.mergeUserModify(user);

                if (pathId == 1) {
                    return "redirect:/app/admin/school/filial/level/classroom?clssid=" + classId + "&fid=" + fid
                            + "&uAE=true"; // URL DESDE CLASSROOM APLICA ESTUDIANTES Y MAESTROS
                } else if (pathId == 2) {

                    // URL DESDE EL PANEL DE REGISTROS
                    return "redirect:/app/admin/school/filial/level/classroom?clssid=" + classId + "&fid=" + fid;
                }
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
        return "redirect:/login";
    }

    // AGREGA EL USUARIO/MAESTRO SELECCIONADO A LA TABLA USER_CLASSROOM
    @PostMapping({"/app/crud-register/userselected"})
    public String crudRegisterTeacherSelected(HttpServletRequest req, Model model,
                                              @RequestParam(name = "selectedUsers", required = false) List<Long> selectedUsers,
                                              @RequestParam("fid") Long fid, @RequestParam("classId") Long classId) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user2 = userService.getUserByUsername(userLogged);
        if (user2.getUsername().equals(userLogged) && user2.getType() == 1) {
            try {

                // SE ENVIAN LOS DATOS PARA REGISTRAR EL USUARIO EN EL USER_CLASSROOM
                userService.registerUserSelectedOnClassroom(classId, selectedUsers);

                return "redirect:/app/admin/school/filial/level/classroom?clssid=" + classId + "&fid=" + fid;
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    // ELIMINA EL USUARIO/PROFESOR DE LA TABLA USERCLASSROOM
    @GetMapping({"/app/crud-register/rusr-usrclassrm"})
    public String removeUserFromUserClassroom(HttpServletRequest req, Model model, Long tid, Long classId, Long fid) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user2 = userService.getUserByUsername(userLogged);
        if (user2.getUsername().equals(userLogged) && user2.getType() == 1) {
            try {

                userService.removeUserFromUserClassroom(tid, classId);

                return "redirect:/app/admin/school/filial/level/classroom?clssid=" + classId + "&fid=" + fid;
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    // ELIMINA EL AULA
    @GetMapping({"/app/crud-register/remove-classrm"})
    public String removeClassroom(HttpServletRequest req, Model model, Long classId) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user2 = userService.getUserByUsername(userLogged);
        if (user2.getUsername().equals(userLogged) && user2.getType() == 1) {
            try {
                // SE OBTIENE EL CLASSROOM
                Classroom classroom = classroomService.getClassroomById(classId);

                classroomService.removeClassroom(classroom);

                return "redirect:/app/admin/school/filial/level?id=" + classroom.getLevel().getId() + "&fid="
                        + classroom.getFilial().getId();
            } catch (Exception e) {
                model.addAttribute("error", Cons.DELETE_CLASSROOM_ERROR_TEXT);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    // VACIA EL AULA
    @GetMapping({"/app/crud-register/clean-classrm"})
    public String cleanClassroom(HttpServletRequest req, Model model, Long classId) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user2 = userService.getUserByUsername(userLogged);
        if (user2.getUsername().equals(userLogged) && user2.getType() == 1) {
            try {
                // SE OBTIENE EL CLASSROOM
                Classroom classroom = classroomService.getClassroomById(classId);

                classroomService.cleanClassroom(classroom);

                return "redirect:/app/admin/school/filial/level?id=" + classroom.getLevel().getId() + "&fid="
                        + classroom.getFilial().getId() + "&aDDS=true";
            } catch (Exception e) {
                model.addAttribute("error", Cons.DELETE_CLASSROOM_ERROR_TEXT);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping({"/app/crud-register/classroom-modify"})
    public String modifyClassroom(HttpServletRequest req, Model model, @ModelAttribute Classroom classroom,
                                  Long fid, Long levelid, Long clid) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {

                // SE ENVIA EL CLASSROOM QUE SE RECIBE DEL FORMULARIO PARA TERMINAR DE LLENAR
                classroomService.modifyClassroom(classroom, levelid, fid, clid);

                return "redirect:/app/admin/school/filial/level?id=" + levelid + "&fid=" + fid;
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping({"/app/crud-register/classroom"})
    public String crudRegisterClassroom(HttpServletRequest req, Model model, Integer id,
                                        @ModelAttribute Classroom classroom, Long fid) {
        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged) && user.getType() == 1) {
            try {
                // SE BUSCA EL LEVEL POR EL ID ESTATICO 1L (PRIMARIA)
                if (id == 11) {

                    classroom.setLevel(levelService.getLevelById(1L));
                    classroom.setFilial(filialService.getFilialById(fid));

                    // GUARDA EL CLASSROOM
                    classroomService.saveNewClassroom(classroom);

                } else if (id == 12) {

                    classroom.setLevel(levelService.getLevelById(2L));
                    classroom.setFilial(filialService.getFilialById(fid));

                    // GUARDA EL CLASSROOM
                    classroomService.saveNewClassroom(classroom);

                } else if (id == 13) {

                    classroom.setLevel(levelService.getLevelById(3L));
                    classroom.setFilial(filialService.getFilialById(fid));

                    // GUARDA EL CLASSROOM
                    classroomService.saveNewClassroom(classroom);

                } else if (id == 14) {

                    classroom.setLevel(levelService.getLevelById(4L));
                    classroom.setFilial(filialService.getFilialById(fid));

                    // GUARDA EL CLASSROOM
                    classroomService.saveNewClassroom(classroom);

                }

                return "redirect:/app/crud-register" + "?id=" + 5 + "&fid=" + fid + "&cAS=true";
            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        } else {

            return "redirect:/login";
        }
    }

}
