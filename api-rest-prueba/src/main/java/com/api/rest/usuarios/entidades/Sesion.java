package com.api.rest.usuarios.entidades;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sesiones")
public class Sesion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sesion_id")
	private int id;
	
	@Column(name="fecha_ingreso")
	@JsonFormat(pattern="YYYY-MM-dd")
	private Date fechaIngreso;
	
	@Column(name="fecha_cierre")
	@JsonFormat(pattern="YYYY-MM-dd")
	private Date fechaCierre;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	
	
	

}
