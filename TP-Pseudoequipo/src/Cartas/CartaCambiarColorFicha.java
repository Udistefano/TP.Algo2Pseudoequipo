package Cartas;

import Jugadas.Jugada;
import Jugadas.JugadaCambiarColorFicha;

public class CartaCambiarColorFicha extends Carta {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	//CONSTRUCTORES -------------------------------------------------------------------------------------------

	public CartaCambiarColorFicha() {}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	@Override
	protected String getTituloPorDefecto() {
		return "Carta cambia color de ficha";
	}

	@Override
	public Jugada getJugada() {
		return new JugadaCambiarColorFicha(this);
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}

