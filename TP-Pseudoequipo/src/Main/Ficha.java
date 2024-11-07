package Main;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	// TODO: habria que sacar jugador de clase ficha???
	private Jugador jugador = null;
	private char simbolo = ' ';
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param simbolo no puede ser vacio
	 * @throws Exception si simbolo es vacio
	 * post: inicializa una ficha con el simbolo pasado por parametro, y jugador nulo
	 */
	public Ficha(char simbolo) throws Exception {
		if (simbolo == ' ') {
			throw new Exception("El simbolo de la ficha no puede ser vacio");
		}
		this.simbolo = simbolo;
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return una cadena mostrando el simbolo de la ficha
	 */
	@Override
	public String toString() {
		return "" + this.simbolo;
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return el simbolo de la ficha
	 */
	public char getSimbolo() {
		return this.simbolo;
	}

	/**
	 * pre:
	 * @param ficha no puede ser nula
	 * @return verdadero si el dato de la ficha pasada por parametro y la ficha actual son iguales, falso si no
	 * @throws Exception si la ficha es nula
	 */
    public boolean esElMismoSimbolo(Ficha ficha) throws Exception {
		if (ficha == null) {
			throw new Exception("La ficha no puede ser nulo");
		}
		return this.simbolo == ficha.getSimbolo();
    }

//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}