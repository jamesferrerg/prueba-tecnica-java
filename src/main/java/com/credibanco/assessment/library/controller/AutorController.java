package com.credibanco.assessment.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.library.model.Autor;
import com.credibanco.assessment.library.service.IAutorService;

@CrossOrigin(origins = { "http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class AutorController {
	
	@Autowired
	private IAutorService autorService;
	
	@GetMapping("/autores")
	public ResponseEntity<?> listarAutores(){
		return ResponseEntity.ok().body(autorService.findAll());
	}
	
	@GetMapping("/autores/{idAutor}")
	public ResponseEntity<?> mostrar(@PathVariable Long idAutor){
		Autor autor = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			autor = autorService.findById(idAutor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(autor == null) {
			response.put("mensaje", "El autor con ID ".concat(idAutor.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}
	
	@PostMapping("/autores")
	public ResponseEntity<?> create(@Valid @RequestBody Autor autor, BindingResult result){
		
		Autor autorNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			autorNew = autorService.save(autor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El autor ha sido creado con exito");
		response.put("autor", autorNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/autores/{idAutor}")
	public ResponseEntity<?> update(@Valid @RequestBody Autor autor, BindingResult result, @PathVariable Long idAutor){
		
		Autor autorCurrent = autorService.findById(idAutor);
		Autor autorUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (autorCurrent == null) {
			response.put("mensaje", "Error: no se puede editar el autor con id: ".concat(idAutor.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			autorCurrent.setNombreCompleto(autor.getNombreCompleto());
			autorCurrent.setFechaNacimiento(autor.getFechaNacimiento());
			autorCurrent.setCiudadProcedencia(autor.getCiudadProcedencia());
			autorCurrent.setEmail(autor.getEmail());
			
			autorUpdate = autorService.save(autorCurrent);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El autor ha sido actualizado con éxito");
		response.put("autor", autorUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/autores/{idAutor}")
	public ResponseEntity<?> delete(@PathVariable Long idAutor){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			autorService.delete(idAutor);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El registro ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
