package Cartas;

import Jugadas.Jugada;
import Jugadas.JugadaAnularCasillero;
import Jugadas.JugadaBloquearFicha;

public class CartaBloquearFicha extends Carta {
	 //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public CartaBloquearFicha() {}
	//METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    @Override
	protected String getTituloPorDefecto() {
		return "Carta eliminar cartas de jugador";
	}

	@Override
	public Jugada getJugada() {
		return new JugadaBloquearFicha();
	}
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}
