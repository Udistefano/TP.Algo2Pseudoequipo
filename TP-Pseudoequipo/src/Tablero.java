package tp2Prueba;

import java.nio.channels.FileChannel;

public class Tablero {
	private int[] dimensiones = null;//X,Y,Z
	private Lista<Ficha> celdas = null;
	/***
	 * 
	 * @param dimensiones:
	 * @throws Exception:
	*/
	public Tablero(int[] dimensiones)throws Exception{
		this.dimensiones = dimensiones; 
		
	}
	
	//Coloca una ficha de un jugador en una coordenada específica.
	//colocar_ficha(jugador, ficha, coordenadas){}
	
	//Mueve una ficha de un lugar a otro.
	//mover_ficha(origen, destino){}
	//-------------------------------------------------------

	// Revisa si hay una alineación de fichas del jugador en el tablero.
	//verificar_alineacion(jugador){	}
	
	//-------------------------------------------------------
	// Anula un casillero impidiendo que se use.
	//anular_casillero(coordenadas){

	
	
	//-------------------------------------------------------
	//Imprime o representa gráficamente el estado actual del tablero.
	//	mostrar_tablero(){	}
	//-------------------------------------------------------

}