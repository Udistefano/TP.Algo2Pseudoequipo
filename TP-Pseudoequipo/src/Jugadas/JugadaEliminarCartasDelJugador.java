package Jugadas;

import Cartas.Carta;
import Estructuras.ListaSimple;
import Main.Jugador;
import Main.Partida;
import Main.Teclado;
import Main.Turno;
import Main.ValidacionesUtiles;

public class JugadaEliminarCartasDelJugador extends Jugada {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

	public JugadaEliminarCartasDelJugador(Carta carta) throws Exception {
		super(carta);
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	/**
     * pre:
	 * @param partida no puede ser nulo
	 * @param turnoActual no puede ser nulo
	 * @throws Exception si algun parametro es nulo, o si el jugador no esta dentro de jugadores
     * post: Elimina todas las cartas del mazo del contrincante
     */
	@Override
	public void jugar(Partida partida, Turno turnoActual) throws Exception {
		ValidacionesUtiles.validarSiEsNulo(partida, "Partida");
        ValidacionesUtiles.validarSiEsNulo(turnoActual, "Turno");

		String nombre = Teclado.preguntarNombreDeJugador("\nIngrese el nombre del usuario al cual eliminarle las cartas: ");
		ListaSimple<Jugador> jugadores = partida.getJugadores();

		// TODO: me parece que esta carta es inutil, porque el enunciado dice "Las cartas se levantan al principio de
		//       cada turno de un mazo", y si eliminamos las cartas de un jugador, cuando sea su turno simplemente va a
		//       levantar otras cartas
		jugadores.iniciarCursor();
		while(jugadores.avanzarCursor()) {
			Jugador jugadorActual = jugadores.obtenerCursor();
			if (nombre.equals(jugadorActual.getNombre())) {
				// TODO: implementar mazo, como cola, porque sino no tiene sentido que el jugador vacie su mano, la
				//       devuelva al mazo, y luego cuando tire dados las vuelva a sacar???, ademas el enunciado pide
				//       cola de cartas explicitamente
				partida.getMazo().agregarManoDelJugador(jugadorActual);
				jugadorActual.vaciarMano();
				System.out.println("\nSe eliminaron las cartas del jugador " + jugadorActual);
				return;
			}
		}
		
        throw new Exception("No se hallo el jugador " + nombre + " al cual eliminarle las cartas");
	}

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}

