package Jugadas;

import Cartas.Carta;
import Estructuras.Lista;
import Main.Casillero;
import Main.Jugador;
import Main.Partida;
import Main.Tablero;
import Main.Teclado;
import Main.Turno;
import Main.Validacion;

public class JugadaAnularCasillero extends Jugada {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
	//CONSTRUCTORES -------------------------------------------------------------------------------------------

		public JugadaAnularCasillero(Carta carta) throws Exception {
			super(carta);
		}
		
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
		
		/**
		 * pre: El casillero debe estar en el tablero
		 * post: anula el casillero seleccionado
		 */
		@Override
		public void jugar(Partida partida, Turno turnoActual) throws Exception {
			// pide el casillero a anular, con direcciones x, y, z
			int x = Teclado.preguntarCoordenada('x');
			int y = Teclado.preguntarCoordenada('y');
			int z = Teclado.preguntarCoordenada('z');
			Tablero tablero = partida.getTablero();
			Casillero casillero = tablero.getCasillero(x, y, z);
			casillero.setDato(null);
		}



	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
