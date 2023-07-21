package com.api.rest.usuarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.rest.usuarios.entidades.Sesion;

public interface SesionRepository extends JpaRepository<Sesion,Integer>{

}
