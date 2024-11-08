package Cartas;

import java.util.Objects;

import Jugadas.Jugada;
import Main.TipoDeCarta;


public abstract class Carta {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	
	private static Long IdActual = 1L;
	
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Long id = null; //1 2 3 4 5 6 7 8 9 .... 9223372036854775807
	protected String titulo = null;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------
	
	protected Carta() {
		this.titulo = getTituloPorDefecto();
		this.id = Carta.getIdActual();
	}
	
	protected Carta(String titulo) {
		this.titulo = titulo;
		this.id = Carta.getIdActual();
	}
		
//METODOS DE CLASE ----------------------------------------------------------------------------------------
	
	private static Long getIdActual() {
		return Carta.IdActual++;
	}
	
//METODOS GENERALES ---------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return this.getTitulo() + " (" + this.id + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return Objects.equals(id, other.id);
	}
	
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	
	protected abstract String getTituloPorDefecto();
	
	public abstract Jugada getJugada();
	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

	public String getTitulo() {
		return this.titulo;
	}
	
	public Long getId() {
		return this.id;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
