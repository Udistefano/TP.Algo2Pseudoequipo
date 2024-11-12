package Cartas;

import Jugadas.Jugada;
import Jugadas.JugadaDobleTurno;

public class CartaDobleTurno extends Carta {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre: --
     * post: inicializa una carta con el titulo por defecto y el id actual
     */
    public CartaDobleTurno() {
        super();
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el titulo por defecto de la carta
     */
    @Override
    protected String getTituloPorDefecto() {
        return "Doble turno";
    }

    /**
     * pre: --
     * @return la jugada de la carta
     */
    @Override
    public Jugada getJugada() throws Exception {
        return new JugadaDobleTurno(this);
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
