package com.api.rest.usuarios.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="persona")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="persona_id")
	private int id;
	
	@Column
	private String nombres;
	
	@Column
	private String apellidos;
	
	@Column
	@Size(min=10, max=10)
	@Pattern(regexp="d{10}")
	private String identificacion;
	
	@Column(name="fecha_nacimiento")
	@JsonFormat(pattern="YYYY-MM-dd")
	private Date fechaNacimiento;
	
	@OneToMany(mappedBy="persona",cascade=CascadeType.ALL)
	private Set<Usuario> usuarios=new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
