package com.credibanco.assessment.library;

import static org.mockito.Mockito.*;
import static com.credibanco.assessment.library.Datos.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.credibanco.assessment.library.controller.EditorialController;
import com.credibanco.assessment.library.service.IEditorialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.credibanco.assessment.library.model.Editorial;

@WebMvcTest(EditorialController.class)
public class EditorialControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private IEditorialService editorialService;
	
	ObjectMapper objectMapper;
	
	@BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }
	
	@Test
	void testDetalle() throws Exception{
		//Given
		when(editorialService.findById(1L)).thenReturn(crearEditorial001());
		
		//When
		mvc.perform(get("/api/editoriales/1").contentType(MediaType.APPLICATION_JSON))
		//Then
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.nombre").value("Mundo"));
		
		verify(editorialService).findById(1L);
	}
	
	@Test
	void testListar() throws Exception{
		// Given
		List<Editorial> editoriales = Arrays.asList(crearEditorial001(), crearEditorial002());
		when(editorialService.findAll()).thenReturn(editoriales);
		
		// When
		mvc.perform(get("/api/editoriales").contentType(MediaType.APPLICATION_JSON))
		//Then
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].nombre").value("Mundo"))
			.andExpect(jsonPath("$[1].nombre").value("Universo"))
			.andExpect(jsonPath("$[0].telefono").value("465461"))
			.andExpect(jsonPath("$[1].telefono").value("789466"))
			.andExpect(jsonPath("$", hasSize(2)));

		verify(editorialService).findAll();
	}
	
	@Test
	void testGuardar() throws Exception{
		Editorial editorial = new Editorial(null, "Planeta", "Diag. 123", 58976L, "planeta@mail.com", 9);
		when(editorialService.save(any())).then(invocation ->{
			Editorial ed =invocation.getArgument(0);
			ed.setIdEditorial(3L);
			return ed;
		});
		
		mvc.perform(post("/api/editoriales").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(editorial)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.editorial.idEditorial", is(3)))
				.andExpect(jsonPath("$.editorial.nombre", is("Planeta")));
		
		verify(editorialService).save(any());
		
	}

}
