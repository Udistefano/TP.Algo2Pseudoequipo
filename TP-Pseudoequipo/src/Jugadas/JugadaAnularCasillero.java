package Jugadas;

import Cartas.Carta;
import Main.*;

public class JugadaAnularCasillero extends Jugada {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param carta no puede ser nulo
     * @throws Exception si carta es nulo
     *                   post: inicializa la jugada anular casillero, con la carta pasada por parametro
     */
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
        Validacion.validarSiEsNulo(partida, "Partida");
        Validacion.validarSiEsNulo(turnoActual, "Turno");
        Casillero<Ficha> casillero = partida.preguntarCasillero();
        casillero.setDato(null);
        Bitmap.quitarFichaAlBitmap(casillero);
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
