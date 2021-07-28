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

import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.service.IEditorialService;

@CrossOrigin(origins = { "http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class EditorialController {
	
	@Autowired
	private IEditorialService editorialService;
	
	@GetMapping("/editoriales")
	public ResponseEntity<?> listarEditoriales(){
		return ResponseEntity.ok().body(editorialService.findAll());
	}
	
	@GetMapping("/editoriales/{idEditorial}")
	public ResponseEntity<?> mostrar(@PathVariable Long idEditorial){
		Editorial editorial = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			editorial = editorialService.findById(idEditorial);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(editorial == null) {
			response.put("mensaje", "La editorial con ID ".concat(idEditorial.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Editorial>(editorial, HttpStatus.OK);
	}
	
	@PostMapping("/editoriales")
	public ResponseEntity<?> create(@Valid @RequestBody Editorial editorial, BindingResult result){
		
		Editorial editorialNew = null;
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
			editorialNew = editorialService.save(editorial);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La editorial ha sido creado con exito");
		response.put("editorial", editorialNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/editoriales/{idEditorial}")
	public ResponseEntity<?> update(@Valid @RequestBody Editorial editorial, BindingResult result, @PathVariable Long idEditorial){
		
		Editorial editorialCurrent = editorialService.findById(idEditorial);
		Editorial editorialUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (editorialCurrent == null) {
			response.put("mensaje", "Error: no se puede editar el editorial con id: ".concat(idEditorial.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			editorialCurrent.setNombre(editorial.getNombre());
			editorialCurrent.setDireccionCorrespondencia(editorial.getDireccionCorrespondencia());
			editorialCurrent.setTelefono(editorial.getTelefono());
			editorialCurrent.setEmail(editorial.getEmail());
			editorialCurrent.setMaxLibrosRegistrados(editorial.getMaxLibrosRegistrados());
			
			editorialUpdate = editorialService.save(editorialCurrent);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La editorial ha sido actualizado con éxito");
		response.put("editorial", editorialUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/editoriales/{idEditorial}")
	public ResponseEntity<?> delete(@PathVariable Long idEditorial){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			editorialService.delete(idEditorial);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El registro ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
