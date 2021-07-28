package com.credibanco.assessment.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "libros")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro")
	private Long idLibro;
	
	@Column(nullable=false)
	@NotEmpty(message = "titulo no puede estar vacio")
	private String titulo;
	
	@Column(name="fecha_year")
	private Integer fechaYear;
	
	@Column(nullable=false)
	@NotEmpty(message = "genero no puede estar vacio")
	private String genero;
	
	@Column(name = "numero_de_paginas")
	@NotNull(message = "numero de paginas no puede estar vacio")
	private Integer numeroPaginas;
	
	@JoinColumn(name="editorial_id")
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Editorial editorial;
	
	@JoinColumn(name="autor_id")
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull(message="autor no está registrado")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Autor autor;

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getFechaYear() {
		return fechaYear;
	}

	public void setFechaYear(Integer fechaYear) {
		this.fechaYear = fechaYear;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
