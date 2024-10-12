package com.tecsharp.teachevaluationpro.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double score;
	private String name;

	@ManyToOne
	@JoinColumn(name = "subject")
	private Subject subject;

	private String dateMessage;

	@ManyToOne
	@JoinColumn(name = "teacher")
	private User teacher;

	@ManyToOne
	@JoinColumn(name = "type")
	private ExamType type;

	private Boolean status;

	@Transient
	private List<Evaluation> evaluations;

}
