package com.credibanco.assessment.library.service;

import java.util.List;

import com.credibanco.assessment.library.model.Autor;

public interface IAutorService {
	
	public List<Autor> findAll();
	
	public Autor findById(Long idAutor);
	
	public Autor save(Autor autor);
	
	public void delete(Long idAutor);

}
