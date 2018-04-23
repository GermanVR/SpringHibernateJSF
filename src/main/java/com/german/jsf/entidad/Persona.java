package com.german.jsf.entidad;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
// @Getter
// @Setter
@Table(name = "persona")
@Access(AccessType.FIELD)
// @ToString
@Data
// @EqualsAndHashCode(exclude = { "direccion" })
// @NoArgsConstructor
// @AllArgsConstructor
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_persona")
	private long idPersona;
	private String nombre;
	private String apellidop;
	private String apellidom;
	private char sexo;
	private int edad;

	// mappedBy: Nombre del campo Java

	@OneToMany(mappedBy = "idPersona", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Direccion> direccion = new HashSet<>();

	// @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, targetEntity =
	// Curso.class)
	// @JoinTable(name = "persona_curso", joinColumns = {
	// @JoinColumn(name = "id_persona") }, inverseJoinColumns = { @JoinColumn(name =
	// "id_curso") })mySet
	// private Set<Curso> cursos;

	// @OneToMany(mappedBy = "PersonaCurso")

}
