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
		return "Carta eliminar cartas de jugador";
	}

	@Override
	public Jugada getJugada() {
		return new JugadaAnularCasillero();
	}
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}
