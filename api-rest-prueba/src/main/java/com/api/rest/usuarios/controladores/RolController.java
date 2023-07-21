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

import com.api.rest.usuarios.entidades.Rol;
import com.api.rest.usuarios.repositorios.RolRepository;

@RestController
@RequestMapping("/api/roles")
public class RolController {
	@Autowired
	private RolRepository rolRepository;
	
	@GetMapping
	public ResponseEntity<Collection<Rol>> listarRoles(){
		return new ResponseEntity<>(rolRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rol> obtenerRol(@PathVariable int id){
		Rol rol = rolRepository.findById(id).orElseThrow();
		
		if(rol != null) {
			return new ResponseEntity<>(rolRepository.findById(id).orElseThrow(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> guardarRol(@RequestBody Rol rol){
		return new ResponseEntity<>(rolRepository.save(rol),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarRol(@PathVariable int id){
		rolRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
