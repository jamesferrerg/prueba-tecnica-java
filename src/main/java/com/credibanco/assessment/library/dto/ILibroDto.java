package com.credibanco.assessment.library.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credibanco.assessment.library.model.Libro;

public interface ILibroDto extends JpaRepository<Libro, Long>{

}
