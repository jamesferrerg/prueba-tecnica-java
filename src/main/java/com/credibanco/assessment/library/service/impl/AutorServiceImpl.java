package com.credibanco.assessment.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.assessment.library.dto.IAutorDto;
import com.credibanco.assessment.library.model.Autor;
import com.credibanco.assessment.library.service.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService{

	@Autowired
	private IAutorDto autorDto;
	
	@Override
	@Transactional
	public List<Autor> findAll() {
		return (List<Autor>) autorDto.findAll();
	}

	@Override
	@Transactional
	public Autor findById(Long idAutor) {
		return autorDto.findById(idAutor).orElse(null);
	}

	@Override
	@Transactional
	public Autor save(Autor autor) {
		return autorDto.save(autor);
	}

	@Override
	@Transactional
	public void delete(Long idAutor) {
		autorDto.deleteById(idAutor);
	}

}
