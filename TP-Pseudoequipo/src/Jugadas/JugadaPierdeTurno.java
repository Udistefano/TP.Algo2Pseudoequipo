package Jugadas;

import Cartas.Carta;
import Main.Partida;
import Main.Jugador;

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
        //Preguntar jugador
        Jugador jugador = null; //del partida partida.getJugadores(); //pregunto alguno
        Turno turno = null; //le pregunto al partida el proximo turno del jugador
        turno.incrementarBloqueosRestantes(1);
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}