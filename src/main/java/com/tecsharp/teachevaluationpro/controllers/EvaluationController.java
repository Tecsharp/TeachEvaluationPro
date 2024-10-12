package com.tecsharp.teachevaluationpro.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.tecsharp.teachevaluationpro.utils.Cons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tecsharp.teachevaluationpro.models.Evaluation;
import com.tecsharp.teachevaluationpro.models.EvaluationUserResult;
import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.repositories.evaluation.EvaluationRepository;
import com.tecsharp.teachevaluationpro.repositories.user.UserRepository;
import com.tecsharp.teachevaluationpro.services.evaluation.EvaluationService;
import com.tecsharp.teachevaluationpro.services.evaluatiouserresult.EvaluationUserResultService;
import com.tecsharp.teachevaluationpro.services.exam.ExamService;
import com.tecsharp.teachevaluationpro.services.stage.StageService;
import com.tecsharp.teachevaluationpro.services.user.UserService;
import com.tecsharp.teachevaluationpro.utils.Utils;

@Controller
@RequestMapping("/")
public class EvaluationController implements Serializable {

    private static final Logger logger = LogManager.getLogger(EvaluationController.class);
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamService examService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private EvaluationRepository evaluationRepo;

    @Autowired
    private EvaluationUserResultService evaluationUserResultService;

    private List<Long> incorrectLessonList = new ArrayList<Long>();

    @GetMapping({"/app/exam/evaluation"})
    public String englishLesson(HttpServletRequest req, Model model, Long less, Long oid, Integer pb, Boolean incansw,
                                String clians, Long examId) {

        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged)) {

            model.addAttribute("title", Cons.EXAM_TITLE);

            logger.info("El usuario está logueado");

            try {
                // AGREGAR UN COMPROBANTE PARA QUE NO PUEDAN RETROCEDER

                Boolean questionExist = null;

                // SE OBTIENE EL EXAMEN
                Exam exam = examService.getExamById(examId);

                // SE AGREGAN LOS ID DE LAS EVALUACIONES EN UN ARRAY
                Long[] evaluationIds = new Long[exam.getEvaluations().size()];
                int index = 0;

                for (Evaluation evaluation : exam.getEvaluations()) {
                    evaluationIds[index++] = evaluation.getId();

                    logger.info("Se está iterando lista preguntas/evaluations en obj: Exam");


                }

                // RECORRE LOS ID's DEL ARRAY Y COMPRUEBA QUE LA PREGUNTA NO ESTE EN EL
                // EvaluationUserResult
                // SI ESTA EN EL EVALUATION, SALTA AL SIGUIENTE ID EN EL ARRAY
                try {
                    for (Long evaluationId : evaluationIds) {

                        logger.info("Iterando lista de preguntas");
                        logger.info("Pregunta/evaluation id: " + evaluationId);
                        // SE OBTIENE EL OBJETO EVALUACION CONFORME EL ID
                        Evaluation evaluation = evaluationService.getById(evaluationId);

                        // BUSCA EN LA BASE DE DATOS SI EL USUARIO NO TIENE RELACION CON LA RESPUESTA Y
                        // EXAMEN
                        // SI ES NULO, CONTINUA

                        EvaluationUserResult evaUserResult = (evaluationUserResultService
                                .verifyExistEvaluationResult(exam, user, evaluation.getQuestion()));

                        /*
                        SI EL RESULTADO DEL EVALUATIONRESULT ES NULO, DEBERÍA ENTRAR PARA REGISTRAR LA NUEVA
                        PREGUNTA Y PROCEDER.
                        * */
                        if (evaUserResult == null) {

                            logger.info("La respuesta del alumno es nula");
                            logger.info("La respuesta no tiene relacion con el alumno");

                            questionExist = false;
                            // MANDA LOS DATOS DEL EXAMEN

                            model.addAttribute("originalText", evaluation.getQuestion());
                            model.addAttribute("translateText", evaluation.getCorrectAnswer());
                            model.addAttribute("wordsList", evaluation.getArrayTextAnswers());
                            model.addAttribute("correctAswMsg", evaluation.getCorrectAnswer());
                            model.addAttribute("headerText", evaluation.getHeaderText());
                            model.addAttribute("score", evaluation.getScore());
                            model.addAttribute("lastEvaluationId", evaluationId);
                            model.addAttribute("progressBar", 10); // ARREGLAR
                            model.addAttribute("examId", examId);
                            model.addAttribute("incorrectAnswer", false);

                            return "evaluation";

                        }

                    }

                    // SI YA NO HAY MÁS PREGUNTAS
                    // SE HACE EL REGISTRO DE QUE EL USUARIO YA REALIZÓ EL EXAMEN
                    // MÉTODO:
                    // examService.registerExamFinished(user, exam);
                    //

                    logger.info("Ha finalizado el exámen con éxito");
                    return "exam-finished";

                } catch (Exception e) {
                    model.addAttribute("error", "Hubo un error al realizar el exámen");
                    return "404";
                }

            } catch (Exception e) {
                model.addAttribute("error", e);
                return "404";
            }
        }
        return null;
    }

    @PostMapping({"/app/exam/evaluation"})
    public String recibirDatosDelCliente(HttpServletRequest req, @RequestParam("selectedWord") String selectedWord,
                                         Model model, Long lastEvaluationId, Long idLevel, Integer progressBar, Boolean incorrectAnswer, Long examId,
                                         Double score) {

        String userLogged = (String) req.getSession().getAttribute("USERNAME");
        User user = userService.getUserByUsername(userLogged);
        if (user != null && user.getUsername().equals(userLogged)) {

            logger.info("El usuario está logueadp");

            // SE OBTIENE LA EVALUACION ACTUAL
            Evaluation evaluation = evaluationService.getById(lastEvaluationId);

            // SE OBTIENE EL EXAMEN
            Exam exam = examService.getExamById(examId);

            Long leccion = lastEvaluationId;
            logger.info("LasEvaluationId: " + leccion);
            try {
                if (Utils.convertArrayToString(selectedWord).equals(evaluation.getCorrectAnswer())) {

                    logger.info("Respuesta seleccionada: " + selectedWord + " y respuesta correcta:" + evaluation.getCorrectAnswer());

                    leccion++;

                    System.out.println("Palabra recibida desde el cliente: " + selectedWord);

                    // REGISTRA EL EvaluationUserResult
                    evaluationUserResultService.registerEvaluationUserResult(score, evaluation.getQuestion(),
                            selectedWord, exam, user);

                    model.addAttribute("leccion", leccion);

                } else {

                    // SI LA RESPUESTA ES INCORRECTA MARCARLA CON SCORE 0
                    // REGISTRA EL EvaluationUserResult
                    evaluationUserResultService.registerEvaluationUserResult(0.0, evaluation.getQuestion(),
                            selectedWord, exam, user);

                    model.addAttribute("leccion", leccion);
                    return "redirect:/app/exam/evaluation?less=" + leccion + "&oid=" + evaluation.getId() + "&pb="
                            + progressBar + "&examId=" + examId;

                }

                return "redirect:/app/exam/evaluation?less=" + leccion + "&oid=" + evaluation.getId() + "&pb=" + progressBar + "&examId=" + examId;


            } catch (Exception e) {
            model.addAttribute("error", "Hubo un error macizo pai");
            }
            return null;
        }
        return null;
    }
}
