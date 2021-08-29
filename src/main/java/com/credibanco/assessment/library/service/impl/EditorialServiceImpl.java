package com.credibanco.assessment.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credibanco.assessment.library.dto.IEditorialDto;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.service.IEditorialService;

@Service
public class EditorialServiceImpl implements IEditorialService {

	@Autowired
	private IEditorialDto editorialDto;

	@Override
	@Transactional
	public List<Editorial> findAll() {
		return (List<Editorial>) editorialDto.findAll();
	}

	@Override
	@Transactional
	public Editorial findById(Long idEditorial) {
		return editorialDto.findById(idEditorial).orElse(null);
	}

	@Override
	@Transactional
	public Editorial save(Editorial editorial) {
		return editorialDto.save(editorial);
	}

	@Override
	@Transactional
	public void delete(Long idEditorial) {
		editorialDto.deleteById(idEditorial);
	}

}
