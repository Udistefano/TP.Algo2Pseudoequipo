package tests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import tp2prueba.Ficha;
import tp2prueba.Tablero;

public class TestDeTablero {
	
	@Test
	public void TestDeTablero() throws Exception {
		
		// Testeamos que creamos el tablero y podemos acceder a sus coordenadas
		Tablero<Ficha> tablero = new Tablero(3, 3, 3);
		
		Assertions.assertEquals(3, tablero.getAncho(), "El casillero en x no tiene valor 3");
		Assertions.assertEquals(3, tablero.getAlto(), "El casillero en y no tiene valor 3");
		Assertions.assertEquals(3, tablero.getProfundidad(), "El casillero en z no tiene valor 3");
		
		// Se valida que la excepción funcione
		Assertions.assertThrows(Exception.class, () -> {
			Tablero<Ficha> tablero2 = new Tablero(0, -3, 334);
        });
		
		//creo Ficha ficha
		Ficha ficha = new Ficha('x');
		
		// se agrega la ficha en el casillero con coordenadas (3, 2, 1)
		tablero.agregar(3, 1, 2, ficha);
		
		// se comnprueba que la ficha esté en las coordenadas (3, 2, 1)
		Assertions.assertEquals(ficha, tablero.obtener(3, 1, 2), "Ficha no esta en 3, 1, 2");
		Assertions.assertEquals(ficha, tablero.getCasillero(3, 1, 2).getDato(), "Ficha no esta en 3, 1, 2");
		
	
	}
}
