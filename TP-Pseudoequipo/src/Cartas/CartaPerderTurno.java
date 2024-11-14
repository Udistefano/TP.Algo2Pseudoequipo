package Cartas;

import Jugadas.Jugada;
import Jugadas.JugadaPerderTurno;

public class CartaPerderTurno extends Carta {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    public CartaPerderTurno() {}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return el titulo por defecto de la carta
	 */
	@Override
	protected String getTituloPorDefecto() {
		return "Carta hace perder turno a jugador";
	}

	/**
	 * pre: --
	 * @return la jugada de la carta
	 */
	@Override
	public Jugada getJugada() throws Exception {
		return new JugadaPerderTurno(this);
	}
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
