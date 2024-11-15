package Cartas;

import java.util.Objects;

import Jugadas.Jugada;
import Main.ValidacionesUtiles;

public abstract class Carta {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	private static Long IdActual = 1L;
	
//ATRIBUTOS -----------------------------------------------------------------------------------------------
	
	private Long id = null; //1 2 3 4 5 6 7 8 9 .... 9223372036854775807
	protected String titulo = null;
	
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre: --
     * post: inicializa una carta con el titulo por defecto y el id actual
     */
	protected Carta() {
		this.titulo = getTituloPorDefecto();
		this.id = Carta.getIdActual();
	}

    /**
     * pre:
     * @param titulo no puede ser nulo, ni vacio
     * @throws Exception si titulo es nulo o vacio
     * post: inicializa una carta con el titulo pasado por parametro y el id actual
     */
	protected Carta(String titulo) throws Exception {
		ValidacionesUtiles.validarSiEsUnaCadenaVacia(titulo, "Titulo");
		this.titulo = titulo;
		this.id = Carta.getIdActual();
	}
		
//METODOS DE CLASE ----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el id actual de la carta
     */
	private static Long getIdActual() {
		return Carta.IdActual++;
	}
	
//METODOS GENERALES ---------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return una cadena que muestra el titulo de la carta y su id
     */
	@Override
	public String toString() {
		return getTitulo();
	}

    /**
     * pre: --
     * @return el hash code de la carta
     */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

    /**
     * pre:
     * @param obj el cual a comparar con la carta actual
     * @return verdadero si obj equivale a la carta actual, falso si no
     */
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

    /**
     * pre: --
     * @return el titulo por defecto de la carta
     */
	protected abstract String getTituloPorDefecto();

    /**
     * pre: --
     * @return la jugada de la carta
     */
	public abstract Jugada getJugada() throws Exception;
	
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el titulo de la carta
     */
	public String getTitulo() {
		return titulo;
	}

    /**
     * pre: --
     * @return el id de la carta
     */
	public Long getId() {
		return id;
	}
	
//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}
