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

		boolean esCasilleroInvalido = true;
		do {
			try {
				System.out.println("\nIngrese las coordenadas del casillero del cual cambiar el color de la ficha:");
				Casillero<Ficha> casillero = Teclado.preguntarCasillero(partida.getTablero());
				Color colorDelJugadorActual = turnoActual.getJugador().getColor();
				Ficha fichaACambiar = casillero.getDato();
	
				ValidacionesUtiles.validarSiCasilleroEstaOcupado(casillero, partida.getTablero());
				ValidacionesUtiles.validarSiFichaEstaBloqueada(fichaACambiar);
                ValidacionesUtiles.validarFichaNoEsPropia(casillero.getDato(), turnoActual.getJugador());
	
				fichaACambiar.setColor(colorDelJugadorActual);
				Bitmap.escribirFichaAlBitmap(casillero, colorDelJugadorActual);
				esCasilleroInvalido = false;
			} catch (Exception e) {
				System.out.println("\nError: " + e.getMessage());
			}
		} while (esCasilleroInvalido);
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
