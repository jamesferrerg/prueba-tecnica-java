package com.credibanco.assessment.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "editoriales")
public class Editorial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_editorial")
	private Long idEditorial;
	
	@Column(nullable=false)
	@NotEmpty(message = "nombre no puede estar vacio")
	private String nombre;
	
	@Column(name = "direccion_correspondencia", nullable=false)
	@NotEmpty(message = "dirección de correspondencia no puede estar vacio")
	private String direccionCorrespondencia;
	
	@Column(nullable=false)
	@NotNull(message = "telefono no puede estar vacio")
	private Long telefono;
	
	@Email(message = "correo electronico no es valido")
	private String email;
	
	@Column(name = "max_libros_registrados", nullable=false)
	@NotNull(message = "maximo de libros registrados no puede estar vacio")
	private Integer maxLibrosRegistrados;

	public Long getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(Long idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccionCorrespondencia() {
		return direccionCorrespondencia;
	}

	public void setDireccionCorrespondencia(String direccionCorrespondencia) {
		this.direccionCorrespondencia = direccionCorrespondencia;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMaxLibrosRegistrados() {
		return maxLibrosRegistrados;
	}

	public void setMaxLibrosRegistrados(Integer maxLibrosRegistrados) {
		this.maxLibrosRegistrados = maxLibrosRegistrados;
	}
}
