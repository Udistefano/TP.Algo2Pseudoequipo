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
        Jugador jugadorAPerder = null;
        System.out.print("Ingrese el nombre del jugador al que hacerle perder un turno: ");
        String nombreDelJugador = Teclado.leerCadenaNoVacia();
        Lista<Jugador> jugadores = partida.getJugadores();
        jugadores.iniciarCursor();
        while(jugadores.avanzarCursor()) {
        	Jugador jugadorActual = jugadores.obtenerCursor();
        	if (jugadorActual.getNombre() == nombreDelJugador) {
        		jugadorAPerder = jugadorActual;
        	}
        }
        Turno turno = partida.getTurnoSiguiente(jugadorAPerder);
        turno.incrementarBloqueosRestantes(1);
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}