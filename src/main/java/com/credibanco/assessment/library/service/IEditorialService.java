package com.credibanco.assessment.library.service;

import java.util.List;

import com.credibanco.assessment.library.model.Editorial;

public interface IEditorialService {
	
	public List<Editorial> findAll();
	
	public Editorial findById(Long idEditorial);
	
	public Editorial save(Editorial editorial);
	
	public void delete(Long idEditorial);

}
