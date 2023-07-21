package com.api.rest.usuarios.controladores;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.usuarios.entidades.RolOpcion;
import com.api.rest.usuarios.repositorios.RolOpcionRepository;

@RestController
@RequestMapping("/api/opciones")
public class RolOpcionController {
	@Autowired
	private RolOpcionRepository rolOpcionRepository;
	
	@GetMapping
	public ResponseEntity<Collection<RolOpcion>> listarOpcionesRol(){
		return new ResponseEntity<>(rolOpcionRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RolOpcion> obtenerOpcionesRol(@PathVariable int id){
		RolOpcion rol = rolOpcionRepository.findById(id).orElseThrow();
		
		if(rol != null) {
			return new ResponseEntity<>(rolOpcionRepository.findById(id).orElseThrow(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> guardarOpcionRol(@RequestBody RolOpcion rol){
		return new ResponseEntity<>(rolOpcionRepository.save(rol),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarOpcionRol(@PathVariable int id){
		rolOpcionRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
