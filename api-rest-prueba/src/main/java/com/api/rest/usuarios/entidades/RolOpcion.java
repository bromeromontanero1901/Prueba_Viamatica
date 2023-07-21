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
@Table(name="rol_opciones")
public class RolOpcion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rol_opcion_id")
	private int id;


	@Column(name="nombre_opcion")
	private String nombre;
	
	@ManyToMany
	@JoinTable(name="rol_rol_opciones", joinColumns=@JoinColumn(name="rol_opcion_id",referencedColumnName="rol_opcion_id"),inverseJoinColumns=@JoinColumn(name="rol_id",referencedColumnName="rol_id"))
	private Set<Rol> roles=new HashSet<>();

	
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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	

}
