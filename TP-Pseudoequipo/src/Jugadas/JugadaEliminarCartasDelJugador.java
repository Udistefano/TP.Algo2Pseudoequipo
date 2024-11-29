package Jugadas;

import Cartas.Carta;
import Main.Jugador;
import Main.Partida;
import Main.Teclado;
import Main.Turno;
import Main.UtilesVarios;
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

		// TODO: implementar que si el jugador se borra sus cartas, entonces no se rompe en Partida, cuando quiera
		//       eliminar la carta que jugo
		boolean esNombreInvalido = true;
		do {
			try {
				System.out.print("\nIngrese el nombre del jugador al cual eliminarle las cartas: ");
				String nombreBuscado = Teclado.leerCadena();
				Jugador jugadorBuscado = partida.getJugadores().obtener(nombreBuscado);

				partida.getMazo().agregarManoDelJugador(jugadorBuscado);
				
				System.out.println("\nSe eliminaron las cartas del jugador " + nombreBuscado);
				esNombreInvalido = false;	
			} catch (Exception e) {
				UtilesVarios.mostrarError(e);
			}
		} while (esNombreInvalido);
	}

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}

