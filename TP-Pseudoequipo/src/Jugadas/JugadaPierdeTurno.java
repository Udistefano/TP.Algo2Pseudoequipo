package Jugadas;

import Cartas.Carta;
import Main.*;

/**
 * El jugador decide a quien le hace perder el turno
 */
public class JugadaPierdeTurno extends Jugada {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param carta no puede ser nula
     * @throws Exception si la carta es nula
     */
    public JugadaPierdeTurno(Carta carta) throws Exception {
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

        String nombreDelJugador = Teclado.preguntarNombreDelJugadorAEliminar();
        Jugador jugador = partida.getJugadores().obtener(nombreDelJugador);
        Turno turno = partida.getTurnoSiguiente(jugador);

        turno.incrementarBloqueosRestantes(1);
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}