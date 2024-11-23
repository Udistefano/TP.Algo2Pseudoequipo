package Cartas;

import Jugadas.Jugada;
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
		return "Carta bloquear ficha de jugador";
	}

	@Override
	public Jugada getJugada() throws Exception {
		return new JugadaBloquearFicha(this);
	}
    
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

}
