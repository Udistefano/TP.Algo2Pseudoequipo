package Jugadas;

import Cartas.Carta;
import Main.*;

public class JugadaCambiarColorFicha extends Jugada {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	//CONSTRUCTORES -------------------------------------------------------------------------------------------

	/**
	 * pre:
	 * @param carta no puede ser nulo
	 * @throws Exception si carta es nulo
	 * post: inicializa la jugada cambiar color de ficha, con la carta pasada por parametro
	 */
	public JugadaCambiarColorFicha(Carta carta) throws Exception {
		super(carta);
	}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * pre: debe tener una  carta de este estilo
	 * @param partida no debe ser nulo
	 * @param turnoActual no debe ser nulo
	 * @throws Exception si la ficha es null, osea que no hay ficha en el casillero
	 * post: Cambia de color la ficha en el casillero y la agrega a las fichas del nuevo jugador que es
	 * 		 propietario de esa ficha
	 */
	@Override
	public void jugar(Partida partida, Turno turnoActual) throws Exception {
		Validacion.validarSiEsNulo(partida, "Partida");
		Validacion.validarSiEsNulo(turnoActual, "Turno");

		Casillero<Ficha> casillero = partida.preguntarCasillero();
		Jugador jugadorActual = turnoActual.getJugador();
		cambiarColor(jugadorActual, casillero);
		
	}

	/**
	 * pre:
	 * @param jugador no debe ser nulo
	 * @param casillero no debe ser nulo
	 * @throws Exception si alguno de los parametros es nulo
	 * post: le cambia el color del casillero pasado por parametro al color del jugador pasado por parametro
	 */
	public void cambiarColor(Jugador jugador, Casillero<Ficha> casillero) throws Exception{
		Validacion.validarSiEsNulo(jugador, "Jugador");
		Validacion.validarSiEsNulo(casillero, "Casillero");

		// TODO: en cambiarColor que pasaria si el jugador quiere cambiar el color de una ficha suya?
		if(casillero.getDato() == null) {
			throw new Exception("No se puede cambiar de color porque no hay ninguna ficha");
		}
		Ficha ficha = casillero.getDato();  // Obtengo la ficha del casillero
		ficha.setColor(jugador.getColor());          // Seteo el jugador de la ficha al nuevo jugador
		ficha.setSimbolo(jugador.getSimbolo());  // seteo el simbolo de la ficha al del nuevo jugador (Esto sería como el color)
	}



	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
