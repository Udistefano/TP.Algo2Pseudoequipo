package Jugadas;

import Cartas.Carta;
import Main.*;

public abstract class Jugada {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Carta carta = null;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param carta no puede ser nula
	 * @throws Exception si la carta es nula
	 * post: inicializa la jugada con la carta pasada por parametro
	 */
	public Jugada(Carta carta) throws Exception {
		ValidacionesUtiles.validarSiEsNulo(carta, "Carta");
		this.carta = carta;
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param partida no puede ser nulo
	 * @param turnoActual no puede ser nulo
	 * @throws Exception si alguno de los parametros es nulo
	 * post: juega la jugada, en la partida y turno pasados por parametros
	 */
	public abstract void jugar(Partida partida, Turno turnoActual) throws Exception;

	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return la carta de la jugada
	 */
	public Carta getCarta() {
		return carta;
	}


//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
