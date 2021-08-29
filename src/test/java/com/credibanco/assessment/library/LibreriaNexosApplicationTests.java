package com.credibanco.assessment.library;

import static com.credibanco.assessment.library.Datos.crearEditorial001;
import static com.credibanco.assessment.library.Datos.crearEditorial002;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.credibanco.assessment.library.dto.IEditorialDto;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.service.IEditorialService;

@SpringBootTest
class LibreriaNexosApplicationTests {
	
	/*
	 * @MockBean IEditorialDto editorialRepository;
	 * 
	 * @Autowired IEditorialService service;
	 */
	
	@MockBean
	IEditorialDto editorialRepository;
	
	@Autowired
	IEditorialService editorialService;

	@Test
	void contextLoads() {
		/*
		 * when(editorialRepository.findById(1L)).thenReturn(crearEditorial01());
		 * when(editorialRepository.findById(2L)).thenReturn(crearEditorial02());
		 * 
		 * Editorial editorial1 = service.findById(1L); Editorial editorial2 =
		 * service.findById(2L);
		 * 
		 * assertEquals(467897L, editorial1.getTelefono()); assertEquals("Universo",
		 * editorial2.getNombre());
		 */
		
		
	}
	
	@Test
	void testFindAll() {
		List<Editorial> datos = Arrays.asList(crearEditorial001(), crearEditorial002());
		when(editorialRepository.findAll()).thenReturn(datos);
		
		List<Editorial> editoriales = editorialService.findAll();
		
		assertFalse(editoriales.isEmpty());
		assertEquals(2, editoriales.size());
		/*assertTrue(editoriales.contains(crearEditorial001()));
		
		verify(editorialRepository).findAll();*/
	}

}
