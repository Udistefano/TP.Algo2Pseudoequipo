package tp2prueba;

import ar.uba.fi.cb100.semana07.jueves.Casillero;

public class Casillero<T> {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	public static int CANTIDAD_DE_VECINOS = 9; // Esto no se valido todavia
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private T dato = null;
	private Casillero<T>[][][] vecinos;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre:
	 * @param x: 1 o mayor
	 * @param y: 1 o mayor
	 * @param z: 1 o mayor
	 * @throws Exception si alguno de los parametros es menor a 1
	 */
	public Casillero(int x, int y, int z) throws Exception {
		validarCoordenada(x);
		validarCoordenada(y);
		validarCoordenada(z);

		this.x = x;
		this.y = y;
		this.z = z;
		this.vecinos = new Casillero[CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS];
        for (int i = 0; i < this.vecinos.length ; i++) {
            for (int j = 0; j < this.vecinos.length; j++) {
                for (int k = 0; k < this.vecinos.length; k++) {
                    this.vecinos[i][j][k] = null;
                }
            }
        }
        this.vecinos[1][1][1] = this; // Definir el casillero actual como vecino de sí mismo
    }
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	public static int invertirCoordenadaDeVecino(int i) {
	    return -i;
	}
	
//METODOS GENERALES ---------------------------------------------------------------------------------------

	/**
	 * pre: -
	 * @return una cadena mostrando las coordenadas del casillero
	 */
	@Override
	public String toString() {	
		return "Casillero (" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	/**
	 * pre: --
	 * @param coordenada
	 * @throws Exception
	 * post: Se valida que las coordenadas sean positivas y mayores a 0
	 */
	public static void validarCoordenada(int coordenada) throws Exception {
		if(coordenada < 1) {
			throw new Exception("La coordenada " + coordenada + " debe ser mayor a 1");
		}
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	public boolean estaOcupado() {
		return this.dato != null;
	}
	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * pre: -
	 * @return la coordenada x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * pre: -
	 * @return la coordenada y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * pre: -
	 * @return la coordenada z
	 */
	public int getZ() {
		return this.z;
	}

	/**
	 * pre: -
	 * @return el dato de tipo T
	 */
	public T getDato() {
		return this.dato;
	}
	
	/**
     * pre: 
     * @param x: -1, 0 o 1 para indicar izquierda, centro o derecha respectivamente
     * @param y: -1, 0 o 1 para indicar arriba, centro o abajo respectivamente
     * @param z: -1, 0 o 1 para indicar delante, centro o detrás respectivamente
     * @return devuelve el casillero vecino
	 * @throws Exception 
     */
    public Casillero<T> getCasilleroVecino(int x, int y, int z) throws Exception {
    	validarCoordenada(x);
    	validarCoordenada(y);
    	validarCoordenada(z);
        return this.vecinos[x + 1][y + 1][z + 1]; // Ajustar índice para matriz 3x3x3
    }
    

    /**
     * Devuelve una matriz 3D con los vecinos, y el casillero actual en el centro
     * @return
     */
    @SuppressWarnings("unchecked")
    public Casillero<T>[][][] getCasillerosVecinos() {
        Casillero<T>[][][] matriz = new Casillero[3][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    matriz[i][j][k] = this.vecinos[i][j][k];
                }
            }
        }
        return matriz;
    }
		
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param dato
	 * post: setea el dato del casillero al dato pasado por parametro
	 */
	public void setDato(T dato) {
		this.dato = dato;		
	}
	
	/**
     * pre: 
     * @param casillero: el casillero vecino
     * @param x: -1, 0 o 1 para indicar izquierda, centro o derecha respectivamente
     * @param y: -1, 0 o 1 para indicar arriba, centro o abajo respectivamente
     * @param z: -1, 0 o 1 para indicar delante, centro o detrás respectivamente
     */
    public void setCasilleroVecino(Casillero<T> casillero, int x, int y, int z) {
        this.vecinos[x + 1][y + 1][z + 1] = casillero;
    }
}
