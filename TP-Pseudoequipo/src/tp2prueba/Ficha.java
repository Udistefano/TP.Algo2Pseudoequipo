package tp2prueba;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private char simbolo;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	public Ficha(char simbolo) {
		//validar
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