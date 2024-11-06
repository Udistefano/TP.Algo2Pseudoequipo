package Main;

// TODO: documentar metodos de clase Ficha
public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private Jugador jugador = null;
	private char simbolo;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	public Ficha(char simbolo) {
		//TODO: validar constructor ficha
		this.simbolo = simbolo;
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "" + this.simbolo;
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	public char getSimbolo() {
		return simbolo;
	}

	/**
	 * pre:
	 * @param ficha no puede ser nulo
	 * @return verdadero si el dato de la ficha pasada por parametro y la ficha actual son iguales, falso si no
	 */
    public boolean esElMismoSimbolo(Ficha ficha) throws Exception {
		if (ficha == null) {
			throw new Exception("La ficha no puede ser nulo");
		}
		return this.simbolo == ficha.getSimbolo();
    }

//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
}