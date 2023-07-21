package com.api.rest.usuarios.controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.usuarios.entidades.Persona;
import com.api.rest.usuarios.entidades.Usuario;
import com.api.rest.usuarios.repositorios.PersonaRepository;
import com.api.rest.usuarios.repositorios.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@GetMapping
	public ResponseEntity<Collection<Usuario>> listarUsuarios(){
		return new ResponseEntity<>(usuarioRepository.findAll(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id){
		Usuario usuario = usuarioRepository.findById(id).orElseThrow();
		
		if(usuario != null) {
			return new ResponseEntity<>(usuarioRepository.findById(id).orElseThrow(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@Valid @RequestBody Usuario usuario){
		Optional<Persona> personaOptional = personaRepository.findById(usuario.getPersona().getId());
		
		if(!personaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		usuario.setPersona(personaOptional.get());
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuarioGuardado.getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(usuarioGuardado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@Valid @RequestBody Usuario usuario,@PathVariable Integer id){
		Optional<Persona> personaOptional = personaRepository.findById(usuario.getPersona().getId());
		
		if(!personaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		usuario.setPersona(personaOptional.get());
		usuario.setId(usuarioOptional.get().getId());
		usuarioRepository.save(usuario);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Integer id){
		Optional<Usuario> libroOptional = usuarioRepository.findById(id);
		
		if(!libroOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		usuarioRepository.delete(libroOptional.get());
		return ResponseEntity.noContent().build();
	}

}
