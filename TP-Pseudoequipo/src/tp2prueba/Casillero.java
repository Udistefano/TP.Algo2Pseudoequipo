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
	
	@Override
	public String toString() {	
		return "Casillero (" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	public boolean estaOcupado() {
		return this.dato != null;
	}
	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public T getDato() {
		return this.dato;
	}
		
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	public void setDato(T dato) {
		this.dato = dato;		
	}	
}
