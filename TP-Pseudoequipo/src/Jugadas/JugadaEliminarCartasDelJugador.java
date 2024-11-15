package Jugadas;

import Cartas.Carta;
import Estructuras.Lista;
import Main.Jugador;
import Main.Partida;
import Main.Teclado;
import Main.Turno;

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
		System.out.print("\nIngrese el nombre del usuario a eliminar: ");
		String nombreDelJugadorContricante = Teclado.leerCadenaNoVacia();

		Lista<Jugador> jugadores = partida.getJugadores();
		jugadores.iniciarCursor();
		while(jugadores.avanzarCursor()) {
			Jugador jugadorActual = jugadores.obtenerCursor();
			if (nombreDelJugadorContricante.equals(jugadorActual.getNombre())) {
				jugadorActual.vaciarMano();
				return;
			}
		}
		throw new Exception("El jugador no está dentro de los jugadores");
	}

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}

