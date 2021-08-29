package com.credibanco.assessment.library;

import static com.credibanco.assessment.library.Datos.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.credibanco.assessment.library.dto.IEditorialDto;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.service.IEditorialService;

@DataJpaTest
public class IntegracionJpaTest {
	
	@Autowired
	IEditorialDto editorialRepository;
	
	@MockBean
	private IEditorialService editorialService;
	
	@Test
	void testFindById() {
		Optional<Editorial> editorial = editorialRepository.findById(1L);
		assertTrue(editorial.isPresent());
		assertEquals("Mundo", editorial.orElseThrow().getNombre());
	}
	
	@Test
	void testFindByIdThrowException() {
		Optional<Editorial> editorial = editorialRepository.findById(12L);
		assertThrows(NoSuchElementException.class, editorial::orElseThrow);
		assertFalse(editorial.isPresent());
	}
	
	@Test
	void testFindAll() {
		List<Editorial> editorial = editorialRepository.findAll();
		assertFalse(editorial.isEmpty());
		assertEquals(2, editorial.size());
	}
	
	@Test
	void testSave() {
		// Given
		Editorial editorialPlaneta = new Editorial(null, "Planeta", "Calle 2 w", 633211L, "planeta@mail.com", 3);
		
		//When
		//Editorial editorial = editorialRepository.findById(guardar.getIdEditorial()).orElseThrow();
		Editorial editorial = editorialRepository.save(editorialPlaneta);
		
		// Then
		assertEquals("Planeta", editorial.getNombre());
		assertEquals(633211L, editorial.getTelefono());
		//assertEquals(3, editorial.getIdEditorial());
	}
	
	@Test
	void testDelete() {
		Editorial editorial = editorialRepository.findById(2L).orElseThrow();
		assertEquals("Universo", editorial.getNombre());
		
		editorialRepository.delete(editorial);
		
		assertThrows(NoSuchElementException.class, () -> {
			editorialRepository.findById(2L).orElseThrow();
		});
		assertEquals(1, editorialRepository.findAll().size());
		
	}

}
