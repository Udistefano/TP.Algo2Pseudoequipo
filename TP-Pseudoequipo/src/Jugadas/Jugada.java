package Jugadas;

import Cartas.Carta;
import Estructuras.Lista;
import Main.Casillero;
import Main.Ficha;
import Main.Jugador;
import Main.Partida;
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
	
	// jugar carta Borrar cartas de jugador
	public void jugar(String nombreJugador, Lista<Jugador> jugadores) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	// jugar carta cambiar color ficha
	public void jugar(Jugador jugador, Casillero<Ficha> casillero) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	public Carta getCarta() {
		return carta;
	}


//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
