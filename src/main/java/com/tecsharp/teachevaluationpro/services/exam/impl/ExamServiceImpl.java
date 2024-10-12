package com.tecsharp.teachevaluationpro.services.exam.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsharp.teachevaluationpro.models.Exam;
import com.tecsharp.teachevaluationpro.models.ExamDate;
import com.tecsharp.teachevaluationpro.models.Subject;
import com.tecsharp.teachevaluationpro.repositories.exam.ExamRepository;
import com.tecsharp.teachevaluationpro.repositories.examdate.ExamDateRepository;
import com.tecsharp.teachevaluationpro.repositories.examtype.ExamTypeRepository;
import com.tecsharp.teachevaluationpro.services.evaluation.EvaluationService;
import com.tecsharp.teachevaluationpro.services.exam.ExamService;
import com.tecsharp.teachevaluationpro.services.subject.SubjectService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	ExamRepository examRepo;

	@Autowired
	UserService userService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	ExamTypeRepository examTypeService;

	@Autowired
	ExamDateRepository examDateRepo;
	
	@Autowired
	EvaluationService evaluationService;

	@Override
	public Exam createExam(String examName, Integer numberQuestions, Long examSubject, Long examType, Long teacherId) {

		Exam exam = new Exam();
		exam.setName(examName);
		exam.setTeacher(userService.findUserById(teacherId));
		exam.setStatus(true);
		exam.setSubject(subjectService.getSubjectById(examSubject));
		exam.setType(examTypeService.getExamTypeById(examType));

		examRepo.saveExam(exam);

		return examRepo.findExamByName(examName);
	}

	@Override
	public Exam getExamById(Long id) {
		Exam exam = examRepo.getExamById(id);
		
		exam.setEvaluations(evaluationService.evaluationsListByExam(exam));
		
		return exam;
	}

	@Override
	public void convertStringToDateAndSave(String initDate, String initTime, String endDate, String endTime,
			Exam exam) {

		String init = initDate + " " + initTime;
		String end = endDate + " " + endTime;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date finalEndDate = format.parse(end);
			Date finalInitDate = format.parse(init);

			ExamDate exm1 = new ExamDate();
			exm1.setInitDate(finalInitDate);
			exm1.setEndDate(finalEndDate);
			exm1.setExam(exam);

			examDateRepo.saveDataDateExam(exm1);

			LocalDate fechaInicio = LocalDate.parse(initDate);
			LocalDate fechaFin = LocalDate.parse(endDate);

			// Calcular el período entre las dos fechas
			Period periodo = Period.between(fechaInicio, fechaFin);

			// Obtener los días restantes del período
			int diasRestantes = periodo.getDays();
			exam.setDateMessage("Dias restantes: " + diasRestantes);
			examRepo.saveExam(exam);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getStringDaysRemaining(ExamDate examDate, Exam exam) {

		return null;
	}

	@Override
	public Boolean nameAlreadyExistCheck(String examName) {

		try {
			examRepo.findExamByName(examName);

		} catch (Exception e) {
			return true;
		}
		return false;

	}

}
