package com.api.rest.usuarios.controladores;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.rest.usuarios.excepciones.ResourceNotFoundException;
import com.api.rest.usuarios.repositorios.PersonaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@GetMapping
	public List<Persona> listarPersonas(){
		return personaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> verDetallePersona(@PathVariable int id){
		Persona personaBuscada = personaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor con el ID : " + id + " no encontrado"));
		return ResponseEntity.ok().body(personaBuscada);
	}
	
	@PostMapping
	public ResponseEntity<Persona> guardarPersona(@Valid @RequestBody Persona persona) {
		Persona personaGuardada=personaRepository.save(persona);
		URI ubicacion=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(personaGuardada.getId()).toUri();
		return ResponseEntity.created(ubicacion).body(personaGuardada);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<Persona> actualizarPersona(@PathVariable Integer id,@Valid @RequestBody Persona persona){
		Optional<Persona> personaOptional = personaRepository.findById(id);
		
		if(!personaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		persona.setId(personaOptional.get().getId());
		personaRepository.save(persona);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Persona> eliminarPersona(@PathVariable Integer id){
		Optional<Persona> personaOptional = personaRepository.findById(id);
		
		if(!personaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		personaRepository.delete(personaOptional.get());
		return ResponseEntity.noContent().build();
	}

}
