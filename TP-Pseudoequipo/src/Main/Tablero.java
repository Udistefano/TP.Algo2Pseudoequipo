package Main;

/*
 * Este tablero esta implementado en 2 dimensiones, 
 * hay que implementarlo en 3 dimensiones.
 * Faltan los metodos tambien.
 */

import Estructuras.Lista;

public class Tablero<T> {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Lista<Lista<Lista<Casillero<T>>>> tablero = null;
	private int ancho = 0;
	private int alto = 0;
	private int profundidad = 0;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * @param ancho debe ser mayor o igual a 1
	 * @param alto debe ser mayor o igual a 1
	 * @param profundidad debe ser mayor o igual a 1
	 * @throws Exception si alguna de las coordenadas es menor a 1
	 * post: crea un tablero de ancho 'ancho' contando de 1 a ancho inclusive
	 */
	public Tablero(int ancho, int alto, int profundidad) throws Exception {
		Casillero.validarCoordenadas(ancho, alto, profundidad);

		this.ancho = ancho;
		this.alto = alto;
		this.profundidad = profundidad;
		this.tablero = new Lista<Lista<Lista<Casillero<T>>>>();

		for (int x = 1; x <= ancho; x++) {
			Lista<Lista<Casillero<T>>> plano = new Lista<Lista<Casillero<T>>>();
			this.tablero.agregar(plano);

			for (int y = 1; y <= alto; y++) {
				Lista<Casillero<T>> fila = new Lista<Casillero<T>>();
				plano.agregar(fila);

				for (int z = 1; z <= profundidad; z++) {
					Casillero nuevoCasillero = new Casillero<T>(x, y, z);
					fila.agregar(nuevoCasillero);
					

				    // Relacionar vecinos, este está en 2 dimensiones para tomar referencia.
					// Estoy en (i, j), tengo que asignar (i-1, j+1), (i-1, j+0), (i-1, j-1), (i, j-1) --> 2D
					// En 3D deberia ser lo mismo pero agregandole el eje z variando desde -1 a +1
					for(int i = -1; i <= 1; i++) {  // sería el eje z
						for(int k = -1; k <= 1; k++) {
							if (this.existeElCasillero(x-1, k, i)) { // seria el eje y para un plano
								relacionarCasillerosVecinos(this.getCasillero(x-1, k, i), nuevoCasillero, -1, k, i);					
							}
						}
						if (this.existeElCasillero(x, y-1, i)) {
							relacionarCasillerosVecinos(this.getCasillero(x, y-1, i), nuevoCasillero, 0, -1, i);
						}
					}
					
				}
				
				//Avanzo a siguiente fila para la busqueda de vecinos
				this.tablero.avanzarCursor();
			}
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
	public void relacionarCasillerosVecinos(Casillero<T> casillero1, Casillero<T> casillero2, int x, int y, int z) {
		//Validar
		casillero2.setCasilleroVecino(casillero1, x, y, z);
		casillero1.setCasilleroVecino(casillero2, Casillero.invertirCoordenadaDeVecino(x), 
										Casillero.invertirCoordenadaDeVecino(y), 
										Casillero.invertirCoordenadaDeVecino(z));
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