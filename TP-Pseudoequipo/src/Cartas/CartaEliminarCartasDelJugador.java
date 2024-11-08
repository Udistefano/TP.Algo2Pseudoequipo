package Cartas;

import Jugadas.Jugada;
import Jugadas.JugadaEliminarCartaslDelJugador;


public class CartaEliminarCartasDelJugador extends Carta {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
		
		public CartaEliminarCartasDelJugador() {}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
		
		@Override
		protected String getTituloPorDefecto() {
			return "Carta eliminar cartas de jugador";
		}

		@Override
		public Jugada getJugada() {
			return new JugadaEliminarCartaslDelJugador(this);
		}
		
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}

