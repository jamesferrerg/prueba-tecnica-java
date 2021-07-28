package com.credibanco.assessment.library.service;

import java.util.List;

import com.credibanco.assessment.library.model.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	public Libro findById(Long idLibro);
	
	public Libro save(Libro libro);
	
	public void delete(Long idLibro);

}
