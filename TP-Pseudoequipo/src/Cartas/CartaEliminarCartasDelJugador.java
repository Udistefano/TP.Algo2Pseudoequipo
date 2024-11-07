package Cartas;

import Estructuras.Lista;
import Main.Jugador;




public class CartaEliminarCartasDelJugador extends Carta {


//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	protected CartaEliminarCartasDelJugador() throws Exception {
		this.titulo = "Carta eliminar cartas de jugador";
		// TODO Auto-generated constructor stub
	}
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
	/**
     * pre: debe tner una  carta de este estilo
     * @param nombreJugador
     * @param jugadores
     * @throws Exception si el jugador no esta dentro de jugadores
     * post: Elimina todas las cartas del mazo del contrincante
     */
	public void realizarEfecto(String nombreJugador, Lista<Jugador> jugadores) throws Exception {
		Jugador jugador = null;
		jugadores.iniciarCursor();
		while(jugadores.avanzarCursor()) {
			Jugador jugadorActual = jugadores.obtenerCursor();
			if (nombreJugador == jugadorActual.getNombre()) {
				jugador = jugadorActual;
			}
		}
		if (jugador == null ) {
			throw new Exception("El jugador no está dentro de los jugadores");
		}
		jugador.mano = null;
	}
	
	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}
