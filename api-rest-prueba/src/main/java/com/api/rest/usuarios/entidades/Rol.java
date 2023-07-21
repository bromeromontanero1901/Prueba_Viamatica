package com.api.rest.usuarios.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="rol")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rol_id")
	private int id;
	
	@Column(name="nombre_rol")
	private String nombre;
	
	@ManyToMany
	@JoinTable(name="rol_usuarios", joinColumns=@JoinColumn(name="rol_id",referencedColumnName="rol_id"),inverseJoinColumns=@JoinColumn(name="usuario_id",referencedColumnName="usuario_id"))
	private Set<Usuario> usuarios=new HashSet<>();
	
	@ManyToMany
	@JoinTable(name="rol_rol_opciones", joinColumns=@JoinColumn(name="rol_id",referencedColumnName="rol_id"),inverseJoinColumns=@JoinColumn(name="rol_opcion_id",referencedColumnName="rol_opcion_id"))
	private Set<RolOpcion> rolOpciones=new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<RolOpcion> getRolOpciones() {
		return rolOpciones;
	}

	public void setRolOpciones(Set<RolOpcion> rolOpciones) {
		this.rolOpciones = rolOpciones;
	}

	
}
