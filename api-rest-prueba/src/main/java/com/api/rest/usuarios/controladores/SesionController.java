package com.api.rest.usuarios.controladores;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.rest.usuarios.entidades.Sesion;
import com.api.rest.usuarios.entidades.Usuario;
import com.api.rest.usuarios.repositorios.SesionRepository;
import com.api.rest.usuarios.repositorios.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sesiones")
public class SesionController {
	
	@Autowired
	private SesionRepository sesionRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private int contador=0;
	
	@GetMapping
	public ResponseEntity<Collection<Sesion>> listarSesiones(){
		return new ResponseEntity<>(sesionRepository.findAll(),HttpStatus.OK);
		
	}
	
	@PostMapping("/valida-sesion")
	public @ResponseBody String validaSesion(@Valid @RequestBody Sesion sesion, @RequestParam(name="user") String username, @RequestParam(name="pass") String pass){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(sesion.getUsuario().getId());
		if((username.toString()==usuarioOptional.get().getUsername().toString())&&(pass.toString()==usuarioOptional.get().getPassword().toString())){
		
			sesion.setUsuario(usuarioOptional.get());
			sesionRepository.save(sesion);
			return "Ha iniciado sesión correctamente";
		}
		else{
			if (contador==3) {
				this.contador=0;
				return "El usuario ha sido bloqueado";
			}
			else {
				this.contador+=1;
				return "El usuario o la contraseña no son correctos";
			}
		}
	}
	
	@PostMapping
	public ResponseEntity<Sesion> guardarSesion(@Valid @RequestBody Sesion sesion){
		
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(sesion.getUsuario().getId());
		
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		sesion.setUsuario(usuarioOptional.get());
		Sesion sesionGuardada = sesionRepository.save(sesion);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(sesionGuardada.getUsuario().getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(sesionGuardada);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarSesion(@Valid @RequestBody Sesion sesion,@PathVariable Integer id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(sesion.getUsuario().getId());
		
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		
		sesion.setUsuario(usuarioOptional.get());
		sesionRepository.save(sesion);
		
		return ResponseEntity.noContent().build();
	}
}
