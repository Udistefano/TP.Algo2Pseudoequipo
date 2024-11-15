package Jugadas;

import Cartas.Carta;
import Estructuras.Lista;
import Main.*;

/**
 * El jugador decide a quien le hace perder el turno
 */
public class JugadaPerderTurno extends Jugada {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param carta no puede ser nula
     * @throws Exception si la carta es nula
     */
    public JugadaPerderTurno(Carta carta) throws Exception {
        super(carta);
    }

//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre:
     * @param partida no puede ser nulo
     * @param turnoActual no puede ser nulo
     * @throws Exception si algun parametro es nulo
     * post: le pierde un turno al jugador que se le pregunta al jugador actual
     */
    @Override
    public void jugar(Partida partida, Turno turnoActual) throws Exception {
        Validacion.validarSiEsNulo(partida, "Partida");
        Validacion.validarSiEsNulo(turnoActual, "Turno");
        Jugador jugadorAPerder = null;
        String nombreDelJugador = Teclado.preguntarNombreDelJugadorAEliminar();
        Lista<Jugador> jugadores = partida.getJugadores();
        // FIXME: esto deberia poder hacerse como estaba hecho antes, intentarlo
        jugadores.iniciarCursor();
        while(jugadores.avanzarCursor()) {
        	Jugador jugadorActual = jugadores.obtenerCursor();
        	if(jugadorActual.getNombre() == nombreDelJugador) {
        		jugadorAPerder = jugadorActual;
        	}
        }
        if (jugadorAPerder == null) {
        	throw new Exception("No se encontró el jugador");
        }
        Turno turno = partida.getTurnoSiguiente(jugadorAPerder);
        turno.incrementarBloqueosRestantes(1);
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}