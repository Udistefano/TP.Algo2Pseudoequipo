package Jugadas;

import Cartas.Carta;
import Main.Bitmap;
import Main.Partida;
import Main.Turno;
import Main.UtilesVarios;
import Main.ValidacionesUtiles;

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
        ValidacionesUtiles.validarSiEsNulo(partida, "Partida");
        ValidacionesUtiles.validarSiEsNulo(turnoActual, "Turno actual");

        try {
            partida.volverEstadoDePartidaAnterior();
            Bitmap.escribirArchivo();
            System.out.println("\nSe ha vuelto a la jugada anterior.");
        } catch (Exception e) {
            UtilesVarios.mostrarError(e);
        }
    }
}
