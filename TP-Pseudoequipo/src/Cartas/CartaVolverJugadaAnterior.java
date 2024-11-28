package Cartas;

import Jugadas.Jugada;
import Jugadas.JugadaVolverJugadaAnterior;

public class CartaVolverJugadaAnterior extends Carta {

    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    
    public CartaVolverJugadaAnterior() {
        super();
    }
	
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    @Override
	protected String getTituloPorDefecto() {
		return "Carta para volver jugada atrás";
	}

	@Override
	public Jugada getJugada() throws Exception {
		return new JugadaVolverJugadaAnterior(this);
	}
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}

