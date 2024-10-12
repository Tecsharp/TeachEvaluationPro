package com.tecsharp.teachevaluationpro.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@TypeDef(name = "json", typeClass = JsonType.class)
@Data
@Entity
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double score;

	private String question;

	@org.hibernate.annotations.Type(type = "json")
	@javax.persistence.Column(columnDefinition = "array_text_answers")
	private String[] arrayTextAnswers;

	@Column(name = "correct_answer")
	private String correctAnswer;

	@Column(name = "message_answer_correct")
	private String messageAnswerCorrect;

	@Column(name = "header_text")
	private String headerText;

	@OneToOne
	@JoinColumn(name = "exam")
	private Exam exam;


}
