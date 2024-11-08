package Jugadas;

import Cartas.Carta;
import Estructuras.Lista;
import Main.Casillero;
import Main.Ficha;
import Main.Jugador;
import Main.Partida;
import Main.Tablero;

public class JugadaCambiarColorFicha extends Jugada {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	//CONSTRUCTORES -------------------------------------------------------------------------------------------

	public JugadaCambiarColorFicha(Carta carta) {
		super(carta);
	}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------


	/**
	 * pre: debe tener una  carta de este estilo
	 * @param nombreJugador
	 * @param jugadores
	 * @throws Exception si la ficha es null, osea que no hay ficha en el casillero
	 * post: Cambia de color la ficha en el casillero y la agrega a las fichas del nuevo jugador que es propietariod e esa ficha
	 */
	@Override
	public void jugar(Partida partida, Turno turnoActual) throws Exception {
		// pide el casillero a cambiar, con direcciones x, y, z
		int x = 0;
		int y = 0;
		int z = 0;
		Tablero tablero = partida.getTablero();
		Casillero casillero = tablero.getCasillero(x, y, z);
		Jugador jugadorActual = turnoActual.getJugador;
		cambiarColor(jugadorActual, casillero);
		
	}
	
	public void cambiarColor(Jugador jugador, Casillero<Ficha> casillero) throws Exception{
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
