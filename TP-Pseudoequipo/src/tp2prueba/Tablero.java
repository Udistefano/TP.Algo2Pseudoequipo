package tp2prueba;

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
					fila.agregar(new Casillero<T>(i, j, k));
				}
				plano.agregar(fila);
			}

			this.tablero.agregar(plano);
		}
		
	}

//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------


	
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