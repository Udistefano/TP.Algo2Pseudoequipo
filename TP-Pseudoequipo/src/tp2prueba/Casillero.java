package tp2prueba;

public class Casillero<T> {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private int x = 0;
	private int y = 0;
	private int z = 0;
	private T dato = null;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	/**
	 * pre:
	 * @param x: 1 o mayor
	 * @param y: 1 o mayor
	 * @param z: 1 o mayor
	 * @throws Exception si alguno de los parametros es menor a 1
	 */
	public Casillero(int x, int y, int z) throws Exception {
		if (x < 1) {
			throw new Exception("X debe ser mayor a 0");
		}
		if (y < 1) {
			throw new Exception("Y debe ser mayor a 0");
		}
		if (z < 1) {
			throw new Exception("Z debe ser mayor a 0");
		}

		this.x = x;
		this.y = y;
		this.z = z;
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------

	/**
	 * pre: -
	 * @return una cadena mostrando las coordenadas del casillero
	 */
	@Override
	public String toString() {	
		return "Casillero (" + this.x + ", " + this.y + ", " + this.z + ")";
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
		
//SETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param dato
	 * post: setea el dato del casillero al dato pasado por parametro
	 */
	public void setDato(T dato) {
		this.dato = dato;		
	}	
}
