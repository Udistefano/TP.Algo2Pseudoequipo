package tp2prueba;

import ar.uba.fi.cb100.semana07.jueves.Casillero;

/*
 * Este tablero esta implementado en 2 dimensiones, 
 * hay que implementarlo en 3 dimensiones.
 * Faltan los metodos tambien.
 */

public class Tablero<T> {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Lista<Lista<Lista<Casillero<T>>>> tablero = null;
	private int ancho = 0;
	private int alto = 0;
	private int profundidad = 0;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * 
	 * @param ancho
	 * @param alto
	 * @param profundidad
	 * @throws Exception 
	 * post: crea un tablero de ancho 'ancho' contando de 1 a ancho inclusive
	 */
	public Tablero(int ancho, int alto, int profundidad) throws Exception {
		Casillero.validarCoordenada(ancho);
		Casillero.validarCoordenada(alto);
		Casillero.validarCoordenada(profundidad);

		this.ancho = ancho;
		this.alto = alto;
		this.profundidad = profundidad;
		this.tablero = new Lista<Lista<Lista<Casillero<T>>>>();

		for (int i = 1; i <= ancho; i++) {
			Lista<Lista<Casillero<T>>> plano = new Lista<Lista<Casillero<T>>>();
			for (int j = 1; j <= alto; j++) {
				Lista<Casillero<T>> fila = new Lista<Casillero<T>>();
				for (int k = 1; k <= profundidad; k++) {
					Casillero nuevoCasillero = new Casillero<T>(i, j, k);
					fila.agregar(nuevoCasillero);
					

				    // Relacionar vecinos, este está en 2 dimensiones para tomar referencia.
					//Estoy en (i, j), tengo que asignar (i-1, j + 0), (i-1, j-1), (i, j-1) --> esto es en 2 dimensiones.
					// En 3 dimensiones deberia ser lo mismo pero agregandole el eje z variando desde -1 a +1
					for(int k = -1; k <= 1; k++) {
						if (this.existeElCasillero(i-1, k)) {
							
							relacionarCasillerosVecinos(this.getCasillero(i-1, k), nuevoCasillero, -1, k);					
						}
					}
					if (this.existeElCasillero(i, j-1)) {
						relacionarCasillerosVecinos(this.getCasillero(i, j-1), nuevoCasillero, 0, -1);
					}
				}
				
				//Avanzo a siguiente fila para la busqueda de vecinos
				this.tablero.avanzarCursor();
					
					
				plano.agregar(fila);
			}

			this.tablero.agregar(plano);
		}
		
	}


//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param casillero1: no puede ser vacio
	 * @param casillero2: no puede ser vacio
	 * @param i: rango entre -1, 0 y 1
	 * @param j: rango entre -1, 0 y 1
	 * post: relaciona los dos vecinos en sus matrices de vecinos, en el casillero1 como i y j, y en casillero2
	 *       como el opuesto
	 */
	
	// Este metodo esta en 2 dimensiones, pasar a 3dimensiones y que valide,
	public void relacionarCasillerosVecinos(Casillero<T> casillero1, Casillero<T> casillero2, int i, int j) {
		//Validar
		casillero2.setCasilleroVecino(casillero1, i, j);
		casillero1.setCasilleroVecino(casillero2, Casillero.invertirCoordenadaDeVecino(i), Casillero.invertirCoordenadaDeVecino(j));
	}
	
	
	
	/**
	 * TODO: documentar metodos de comportamiento de tablero
	 * @param x
	 * @param y
	 * @param z
	 * @param ficha
	 * @throws Exception
	 */
	public void agregar(int x, int y, int z, T ficha) throws Exception {
		Casillero<T> casillero = getCasillero(x, y, z);
		casillero.setDato(ficha);
	}

	/**
	 * TODO: validar getCasillero de tablero
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * @throws Exception
	 */
	public Casillero<T> getCasillero(int x, int y, int z) throws Exception {
		//validar
		return this.tablero.obtener(x).obtener(y).obtener(z);
	}
	
	public boolean existeElCasillero(int x, int y, int z) throws Exception {
		boolean existe = true;
		if ((x < 1) || (y < 1) || (z < 1)){
			   existe = false;
		   }
		if ((x > getAncho()) ||
			(y > getAlto()) || 
			(z > getProfundidad())) {
				existe = false;
		}
		return existe;
	}

	/**
	 * TODO: documentar Tablero.obtener
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 * @throws Exception
	 */
	public T obtener(int x, int y, int z) throws Exception {
		return this.getCasillero(x, y, z).getDato();
	}

//GETTERS SIMPLES -----------------------------------------------------------------------------------------


	/**
	 * TODO: documentar getters de tablero
	 * @return
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 *
	 * @return
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 *
	 * @return
	 */
	public int getProfundidad() {
		return profundidad;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}