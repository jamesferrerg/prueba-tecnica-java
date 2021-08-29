package com.credibanco.assessment.library;

import com.credibanco.assessment.library.model.Editorial;

public class Datos {
	
	public static Editorial crearEditorial001() {
		return new Editorial(1L, "Mundo", "Calle algo", 465461L, "mundo@mail.com", 5);
	}
	
	public static Editorial crearEditorial002() {
		return new Editorial(2L, "Universo", "Cra Z", 789466L, "universo@mail.com", 3);
	}

}
