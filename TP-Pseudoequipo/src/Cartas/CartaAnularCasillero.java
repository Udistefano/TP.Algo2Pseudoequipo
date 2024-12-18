package Cartas;

import Jugadas.Jugada;
import Jugadas.JugadaAnularCasillero;


public class CartaAnularCasillero extends Carta {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public CartaAnularCasillero() {}
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    @Override
	protected String getTituloPorDefecto() {
		return "Carta anular casillero";
	}

	@Override
	public Jugada getJugada() throws Exception {
		return new JugadaAnularCasillero(this);
	}
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}
