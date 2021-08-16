package com.credibanco.assessment.library;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import com.credibanco.assessment.library.model.Autor;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.model.Libro;

@DisplayName("Prueba entidad autor")
class AutorTest {
	
	private static ValidatorFactory validatorFactory;
    private static Validator validator;
 
    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
 
    @AfterAll
    public static void close() {
        validatorFactory.close();
    }
    
	@Nested
	@DisplayName("Unicamente entidad Autor")
	class CamposAutorTest{
		@Test
		@DisplayName("Nombre del autor")
		void testNombre() {
			Autor autor = new Autor();
			autor.setNombreCompleto("James");
			String esperado = "James";
			String real = autor.getNombreCompleto();
			assertNotNull(real, () -> "el nombre no puede ser vacio");
			assertEquals(esperado, real, () -> "el nombre no era el que se esperaba");
			assertTrue(real.equals("James"));
		}
		
		@Test
		@DisplayName("Fecha de nacimiento del autor")
		void testFecha() {
			Autor autor = new Autor();
			Date act = new Date();
			autor.setFechaNacimiento(act);
			assertEquals(act, autor.getFechaNacimiento());
		}
		
		@SuppressWarnings("deprecation")
		@ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
		@ValueSource(strings = {"1", "2", "5", "9"})
		@DisplayName("Id del autor")
		void testId(String id) {
			Autor autor = new Autor();
			autor.setIdAutor(new Long(id));
			Long esperado = new Long(1);
			Long real = autor.getIdAutor();
			assertTrue(real.compareTo(esperado)>=0);
		}
	    
	    @Test
	    @DisplayName("Validacion javax.validation")
	    public void shouldHaveNoViolations() {
	        //given:
	        Autor autor = new Autor();
	        autor.setNombreCompleto("Poul");
	        //autor.setEmail("paul@mail.com");
	 
	        //when:
	        Set<ConstraintViolation<Autor>> violations
	                = validator.validate(autor);
	 
	        //then:
	        assertTrue(violations.isEmpty());
	    }
	    
	    @Test
	    @DisplayName("No Validacion javax.validation")
	    public void shouldDetectInvalidName() {
	        //given too short name:
	        Autor autor = new Autor();
	        autor.setNombreCompleto("");
	        //autor.setEmail("paul@mail.com");
	 
	        //when:
	        Set<ConstraintViolation<Autor>> violations
	                = validator.validate(autor);
	 
	        //then:
	        assertEquals(violations.size(), 1);
	 
	        ConstraintViolation<Autor> violation
	                = violations.iterator().next();
	        assertEquals("nombre de autor no puede estar vacio",
	                violation.getMessage());
	        assertEquals("nombreCompleto", violation.getPropertyPath().toString());
	        assertEquals("", violation.getInvalidValue());
	    }
	}

	
    
    @Test
    @DisplayName("Libro con autor y editorial")
    void testLibro() {
    	Autor autor = new Autor();
        autor.setNombreCompleto("Poul");
        
        Editorial editorial = new Editorial();
        editorial.setNombre("Mundo");
        
        Libro libro = new Libro();
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        //String real1 = autor.getNombreCompleto();
        //String real2= editorial.getNombre();
        
        assertAll(() -> assertEquals("Poul", libro.getAutor().getNombreCompleto()),
        		  () -> assertEquals("Mundo", libro.getEditorial().getNombre()));
        
    }
    
    @Nested
    @DisplayName("Unicamente prueba de properties")
    class TestPropiedades {
    	
    	@Test
        @Disabled
        void mostrarSystemProperties() {
        	Properties propiedades = System.getProperties();
        	propiedades.forEach((k, v) -> System.out.println(k + ":" + v));
        }
        
        @Test
        @EnabledIfSystemProperty(named = "java.version", matches = ".*11.*")
        void testJavaVersion() {
        }
        
        @Test
        @EnabledOnJre(JRE.JAVA_11)
        void testJDK11() {
        }
    }
    
 
    
	

}
