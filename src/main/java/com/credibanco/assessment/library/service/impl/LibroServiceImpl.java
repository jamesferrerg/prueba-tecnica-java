package com.credibanco.assessment.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.assessment.library.dto.ILibroDto;
import com.credibanco.assessment.library.model.Libro;
import com.credibanco.assessment.library.service.ILibroService;

@Service
public class LibroServiceImpl implements ILibroService{

	//Inyectar el LibroDto
	@Autowired
	private ILibroDto libroDto; 
	
	@Override
	@Transactional
	public List<Libro> findAll() {
		return (List<Libro>) libroDto.findAll();
	}

	@Override
	@Transactional
	public Libro findById(Long idLibro) {
		return libroDto.findById(idLibro).orElse(null);
	}

	@Override
	@Transactional
	public Libro save(Libro libro) {
		return libroDto.save(libro);
	}

	@Override
	@Transactional
	public void delete(Long idLibro) {
		libroDto.deleteById(idLibro);
	}

}
