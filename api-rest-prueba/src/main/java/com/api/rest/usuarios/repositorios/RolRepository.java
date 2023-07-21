package com.api.rest.usuarios.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.usuarios.entidades.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer>{

}
