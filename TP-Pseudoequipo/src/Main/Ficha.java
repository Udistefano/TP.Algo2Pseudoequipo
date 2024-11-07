package Main;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	 private char simbolo = ' ';
	 private Jugador jugador = null;
	 
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	public Ficha(Jugador jugador) {
		//TODO: validar constructor ficha
		this.jugador = jugador;
		this.simbolo = jugador.getSimbolo();
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
		return this.simbolo;
	}
	public Jugador getjugador() {
		return this.jugador;
	}
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
	
}