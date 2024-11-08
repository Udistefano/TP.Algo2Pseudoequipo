package Jugadas;

import Cartas.Carta;
import Estructuras.Lista;
import Main.Jugador;
import Main.Partida;

public class JugadaEliminarCartaslDelJugador extends Jugada {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

	public JugadaEliminarCartaslDelJugador(Carta carta) {
		super(carta);
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	// a este codigo despues pasarle la partida y el turno, con esto, pedirle al usuario
	// el nombre del jugador al que le borrará las cartas, si ese esta ahí, se realiza lo ya hecho
	/**
     * pre: debe tner una  carta de este estilo
     * @param nombreJugador
     * @param jugadores
     * @throws Exception si el jugador no esta dentro de jugadores
     * post: Elimina todas las cartas del mazo del contrincante
     */
	@Override
	public void jugar(Partida tateti, Turno turnoActual) throws Exception {
		Jugador jugador = null;
		Lista<Jugador> jugadores = tateti.getJugadores();
		String nombreJugador = null; // pide jugador por teclado
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

