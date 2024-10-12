package com.tecsharp.teachevaluationpro.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.core.serializer.Serializer;

import lombok.Data;

@Data
@Entity
public class Classroom implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "grade")
	private Grade grade;

	@ManyToOne
	@JoinColumn(name = "filial")
	private Filial filial;

	@Transient
	private Integer studentsNumber;

	@ManyToOne
	@JoinColumn(name = "level")
	private Level level;

}
