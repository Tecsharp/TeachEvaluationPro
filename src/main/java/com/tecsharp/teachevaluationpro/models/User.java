package com.tecsharp.teachevaluationpro.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	private String email;

	private Integer points;

	private Integer type;

	private String name;

	private String lastnameone;

	private String lastnametwo;

	@ManyToOne
	@JoinColumn(name = "filial")
	private Filial filial;

	@ManyToOne
	@JoinColumn(name = "level")
	private Level level;

	@Transient
	private List<Classroom> classroomList;

}
