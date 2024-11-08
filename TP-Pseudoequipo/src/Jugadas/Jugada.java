package Jugadas;

import Cartas.Carta;
import Main.Ficha;
import Main.Tablero;


public abstract class Jugada {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
		
		private Carta carta = null;
		
	//CONSTRUCTORES -------------------------------------------------------------------------------------------
		
		public Jugada(Carta carta) {
			this.carta = carta;
		}
		
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
		
		public abstract void jugar(Partida tateti, 
									Turno turnoActual) throws Exception;

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
		
		public Carta getCarta() {
			return carta;
		}
		
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	}