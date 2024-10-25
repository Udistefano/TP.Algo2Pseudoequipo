package Main;

public class Casillero<T> {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	public static int CANTIDAD_DE_VECINOS = 3; // Esto no se valido todavia
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private T dato = null;
	private Casillero<T>[][][] vecinos = null;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre:
	 * @param x debe ser mayor o igual a 1
	 * @param y debe ser mayor o igual a 1
	 * @param z debe ser mayor o igual a 1
	 * @throws Exception si alguno de los parametros es menor a 1
	 * post: crea un casillero con las coordenadas pasadas por parametros y sus vecinos
	 */
	@SuppressWarnings("unchecked")
	public Casillero(int x, int y, int z) throws Exception {
		validarCoordenadas(x, y, z);

		this.x = x;
		this.y = y;
		this.z = z;
		this.vecinos =  new Casillero[CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS];
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
	 * pre:
	 * @param x debe ser mayor o igual a 1
	 * @param y debe ser mayor o igual a 1
	 * @param z debe ser mayor o igual a 1
	 * @throws Exception si alguna de las coordenadas es menor a 1
	 * post: valida que las coordenadas sean mayores o igual a 1
	 */
	public static void validarCoordenadas(int x, int y, int z) throws Exception {
		validarCoordenada(x);
		validarCoordenada(y);
		validarCoordenada(z);
	}

	/**
	 * pre:
	 * @param coordenada debe ser mayor o igual a 1
	 * @throws Exception si la coordenada es menor a 1
	 * post: Se valida que las coordenada sea mayor o igual a 1
	 */
	public static void validarCoordenada(int coordenada) throws Exception {
		if(coordenada < 1) {
			throw new Exception("La coordenada " + coordenada + " debe ser mayor a 1");
		}
	}

//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre: --
	 * post: elimina el dato del casillero
	 */
	public void vaciar() {
		this.setDato(null);
	}

	/**
	 * pre: --
	 * @return verdadero si hay un dato en casillero, falso si el casillero esta vacio
	 */
	public boolean estaOcupado() {
		return this.dato != null;
	}

	/**
	 * pre:
	 * @param dato no puede ser nulo
	 * @return verdadero si el dato del casillero equivale al dato pasado por parametro, falso no si equivale
	 * @throws Exception si el dato es nulo
	 */
	public boolean tiene(T dato) throws Exception {
		if (dato == null) {
			throw new Exception("El dato no puede ser nulo");
		}
		return this.dato.equals(dato);
	}

	/**
	 * pre:
	 * @param movimiento un movimiento en 3D, no puede ser nulo
	 * @return verdadero si existe el casillero vecino que este en la direccion 'movimiento', falso si no existe
	 * @throws Exception si no es un movimiento valido o si es nulo
	 */
	public boolean existeElVecino(Movimiento movimiento) throws Exception {
		switch (movimiento) {
			case ABAJO:
				return this.vecinos[2][1][1] != null;
			case ARRIBA:
				return this.vecinos[0][1][1] != null;
			case DERECHA:
				return this.vecinos[1][2][1] != null;
			case IZQUIERDA:
				return this.vecinos[1][0][1] != null;
			case ADELANTE:
				return this.vecinos[1][1][0] != null;
			case ATRAS:
				return this.vecinos[1][1][2] != null;
			default:
				throw new Exception("No se encontro el casillero vecino para chequear su existencia");
		}
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

//	/**
//     * pre:
//     * @param x: -1, 0 o 1 para indicar izquierda, centro o derecha respectivamente
//     * @param y: -1, 0 o 1 para indicar arriba, centro o abajo respectivamente
//     * @param z: -1, 0 o 1 para indicar delante, centro o detrás respectivamente
//     * @return devuelve el casillero vecino
//	 * @throws Exception si alguna de las coordenadas es menor a 1
//     */
//    public Casillero<T> getCasilleroVecino(int x, int y, int z) throws Exception {
//		validarCoordenadas(x, y, z);
//
//        return this.vecinos[x + 1][y + 1][z + 1]; // Ajustar índice para matriz 3x3x3
//    }

	/**
	 * pre:
	 * @param movimiento un movimiento en 3D, no puede ser nulo
	 * @return el casillero vecino que este en la movimiento pasada por parametro
	 * @throws Exception si no es un movimiento valido o si es nulo
	 */
	public Casillero<T> getCasilleroVecino(Movimiento movimiento) throws Exception {
		return this.getCasilleroVecino(Direccion.valueOf(movimiento.toString()));
	}

	/**
	 * pre:
	 * @param direccion un direccion en 3D, no puede ser nulo
	 * @return el casillero vecino que este en la direccion pasada por parametro
	 * @throws Exception si no es un direccion valido o si es nulo
	 */
	public Casillero<T> getCasilleroVecino(Direccion direccion) throws Exception {
		switch (direccion) {
			case ATRAS_ABAJO:
				return this.vecinos[2][1][0];
			case ATRAS_ARRIBA:
				return this.vecinos[0][1][0];
			case ATRAS_DERECHA:
				return this.vecinos[1][2][0];
			case ATRAS_IZQUIERDA:
				return this.vecinos[1][0][0];
			case ATRAS_DERECHA_ARRIBA:
				return this.vecinos[0][2][0];
			case ATRAS_DERECHA_ABAJO:
				return this.vecinos[2][2][0];
			case ATRAS_IZQUIERDA_ARRIBA:
				return this.vecinos[0][0][0];
			case ATRAS_IZQUIERDA_ABAJO:
				return this.vecinos[2][0][0];
			case CENTRO_ABAJO:
				return this.vecinos[2][1][1];
			case CENTRO_ARRIBA:
				return this.vecinos[0][1][1];
			case CENTRO_DERECHA:
				return this.vecinos[1][2][1];
			case CENTRO_IZQUIERDA:
				return this.vecinos[1][0][1];
			case CENTRO_DERECHA_ARRIBA:
				return this.vecinos[0][2][1];
			case CENTRO_DERECHA_ABAJO:
				return this.vecinos[2][2][1];
			case CENTRO_IZQUIERDA_ARRIBA:
				return this.vecinos[0][0][1];
			case CENTRO_IZQUIERDA_ABAJO:
				return this.vecinos[2][0][1];
			case ADELANTE_ABAJO:
				return this.vecinos[2][1][2];
			case ADELANTE_ARRIBA:
				return this.vecinos[0][1][2];
			case ADELANTE_DERECHA:
				return this.vecinos[1][2][2];
			case ADELANTE_IZQUIERDA:
				return this.vecinos[1][0][2];
			case ADELANTE_DERECHA_ARRIBA:
				return this.vecinos[0][2][2];
			case ADELANTE_DERECHA_ABAJO:
				return this.vecinos[2][2][2];
			case ADELANTE_IZQUIERDA_ARRIBA:
				return this.vecinos[0][0][2];
			case ADELANTE_IZQUIERDA_ABAJO:
				return this.vecinos[2][0][2];
			default:
				throw new Exception("No se encontro el casillero vecino");
		}
	}

    /**
	 * pre: --
     * @return Devuelve una matriz 3D con los vecinos, y el casillero actual en el centro
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
