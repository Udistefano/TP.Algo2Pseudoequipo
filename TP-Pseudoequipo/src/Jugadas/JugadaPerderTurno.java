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
        ValidacionesUtiles.validarSiEsNulo(partida, "Partida");
        ValidacionesUtiles.validarSiEsNulo(turnoActual, "Turno");

        String nombre = Teclado.preguntarNombreDeJugador("\nIngrese el nombre del jugador al que hacerle perder un turno: ");
        Lista<Jugador> jugadores = partida.getJugadores();
        
        // TODO: quiza hacer un jugadores.obtener(nombre) en jugadaPerderTurno y jugadaEliminarCartasDelJugador
        jugadores.iniciarCursor();
        while(jugadores.avanzarCursor()) {
        	Jugador jugadorActual = jugadores.obtenerCursor();
        	if (jugadorActual.getNombre().equals(nombre)) {
                Turno turno = partida.getTurnoSiguiente(jugadorActual);
                turno.incrementarBloqueosRestantes(1);
                return;
            }
        }

        throw new Exception("No se hallo el jugador " + nombre + " al cual perderle el turno");
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}