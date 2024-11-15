package Jugadas;

import Cartas.Carta;
import Main.Partida;
import Main.Turno;

public class JugadaVolverJugadaAnterior extends Jugada {
    /**
     * pre:
     * @param carta no puede ser nula
     * @throws Exception si la carta es nula
     * post: inicializa la jugada con la carta pasada por parametro
     */
    public JugadaVolverJugadaAnterior(Carta carta) throws Exception {
        super(carta);
    }

    /**
     * pre:
     * @param partida     no puede ser nulo
     * @param turnoActual no puede ser nulo
     * @throws Exception
     */
    @Override
    public void jugar(Partida partida, Turno turnoActual) throws Exception {
        // TODO: implementar jugada volver jugada anterior
    }
}
