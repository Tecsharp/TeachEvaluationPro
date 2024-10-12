package com.tecsharp.teachevaluationpro.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "evaluation_user_result")
public class EvaluationUserResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double score;
	private String question;
	private String userAnswer;

	@ManyToOne
	@JoinColumn(name = "exam")
	private Exam exam;

	@ManyToOne
	@JoinColumn(name = "user")
	private User user;

}
