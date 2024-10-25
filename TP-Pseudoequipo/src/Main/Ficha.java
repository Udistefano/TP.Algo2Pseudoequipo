package Main;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	private Jugador jugador = null;
	private char simbolo;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	public Ficha(char simbolo) {
		//TODO: validar constructor ficha
		this.simbolo = simbolo;
	}
	
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "" + this.simbolo;
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	
	public char getSimbolo() {
		return simbolo;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
}