package com.api.rest.usuarios.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private int id;
	
	@Column
	@Size(min=8)
	private String username;
	
	@Column
	@NotBlank
	private String password;
	
	@Column
	@Email
	private String mail;
	
	@Column
	@Size(min=1, max=1)
	private char sesionActive;
	
	@Column
	@Size(min=1, max=20)
	private char status;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "persona_id")
	private Persona persona;
	
	@ManyToMany
	@JoinTable(name="rol_usuarios", joinColumns=@JoinColumn(name="usuario_id",referencedColumnName="usuario_id"),inverseJoinColumns=@JoinColumn(name="rol_id",referencedColumnName="rol_id"))
	private Set<Rol> roles=new HashSet<>();
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL)
	private Set<Sesion> sesiones=new HashSet<>();



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public char getSesionActive() {
		return sesionActive;
	}

	public void setSesionActive(char sesionActive) {
		this.sesionActive = sesionActive;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public Set<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(Set<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

}
