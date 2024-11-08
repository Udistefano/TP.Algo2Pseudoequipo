package Jugadas;

import Cartas.Carta;
import Main.Partida;
import Main.Jugador;
import Main.Turno;

/**
 * El jugador decide a quien le hace perder el turno
 */
public class JugadaPierdeTurno extends Jugada {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    public JugadaPierdeTurno(Carta carta) {
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
     * post: juega la jugada pierde turno
     */
    @Override
    public void jugar(Partida partida, Turno turnoActual) throws Exception {
        // TODO: validar nombreDelJugador, jugador
        String nombreDelJugador = Teclado.preguntarNombreJugadorAEliminar();
        Jugador jugador = partida.getJugador(nombreDelJugador);
        Turno turno = partida.getTurnoSiguiente(jugador);

        turno.incrementarBloqueosRestantes(1);
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}