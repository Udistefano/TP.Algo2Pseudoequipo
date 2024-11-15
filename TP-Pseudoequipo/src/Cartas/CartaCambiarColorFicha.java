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
		return "Carta cambiar color de ficha";
	}

	@Override
	public Jugada getJugada() throws Exception {
		return new JugadaCambiarColorFicha(this);
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}

