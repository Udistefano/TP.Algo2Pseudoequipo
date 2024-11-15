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
		ValidacionesUtiles.validarSiEsNulo(partida, "Partida");
		ValidacionesUtiles.validarSiEsNulo(turnoActual, "Turno");

		System.out.println("\nIngrese las coordenadas del casillero del cual cambiar el color de la ficha:");
		Casillero<Ficha> casillero = partida.preguntarCasillero();
		if (casillero.estaOcupado()) {
			ValidacionesUtiles.validarSiFichaEstaBloqueada(casillero.getDato());
		}
		if(casillero.getDato() == null) {
			throw new Exception("\nNo se puede cambiar de color porque no hay ninguna ficha");
		}

		// TODO: en cambiarColor que pasaria si el jugador quiere cambiar el color de una ficha suya?
		Color colorActual = turnoActual.getJugador().getColor();
		casillero.getDato().setColor(colorActual); // Seteo el jugador de la ficha al nuevo jugador
		Bitmap.escribirFichaAlBitmap(casillero, colorActual);

		// TODO: fijarse si hace falta implementar esto de agregarFicha, quitarFicha y obtenerJugadorPorFicha
		// 		 capaz que no, porque no afecta al final, pero quedaria bien que si un jugador se roba la ficha
		//       de otro, podamos comprobar que tiene una ficha mas jugada, y el otro una ficha menos
//		Jugador jugadorOriginal = partida.obtenerJugadorPorFicha(casillero.getDato());
//		jugadorActual.agregarFicha();
//		jugadorOriginal.quitarFicha();
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
