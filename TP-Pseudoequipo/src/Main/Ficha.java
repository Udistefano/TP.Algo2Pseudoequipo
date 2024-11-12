package Main;

import Cartas.Carta;

import java.util.Objects;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
    private char simbolo = ' ';
    private String color = null;
    private boolean estaBloqueada;

//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param simbolo no puede ser vacio
     * @param color no puede ser vacio ni nulo
     * @throws Exception si alguno de los parametros es nulo
     * post: inicializa la ficha con el simbolo y color dados
     */
    public Ficha(char simbolo, String color) throws Exception {
        Validacion.validarSiEsNulo(simbolo, "Simbolo");
        Validacion.validarSiEsUnaCadenaVacia(color, "Color");
        this.simbolo = simbolo;
        this.color = color;
        this.estaBloqueada = false;
    }

//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return una cadena mostrando el simbolo de la ficha
     */
    @Override
    public String toString() {
        return "" + this.simbolo;
    }

    /**
     * pre:
     * @param obj con el cual comparar a la ficha actual
     * @return verdadero si obj equivale a la ficha actual, falso si no
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Ficha other = (Ficha) obj;
        return (this.simbolo == other.getSimbolo()) &&
                (this.color.equals(other.getColor()));
    }

    /**
     * pre: --
     * @return el hashCode de la ficha actual
     */
    @Override
    public int hashCode() {
        return Objects.hash(simbolo, color);
    }

//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el simbolo de la ficha
     */
    public char getSimbolo() {
        return this.simbolo;
    }

    /**
     * pre: --
     * @return el color de la ficha
     */
    public String getColor() {
        return this.color;
    }
    
    /**
     * pre: --
     * @return si la ficha esta bloqueada
     */
    public boolean isEstaBloqueada() {
		return estaBloqueada;
	}

//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    
    /**
     * pre: --
     * @param estaBloqueada
     * post: Cambia el valor de estaBloqueada
     */
	public void setEstaBloqueada(boolean estaBloqueada) {
		this.estaBloqueada = estaBloqueada;
	}

	// FIXME: los setters de simbolo y color de Ficha deben ser public o protected?
    /**
     * pre:
     * @param simbolo no puede ser vacio
     * @throws Exception si simbolo es vacio
     * post: cambia el simbolo de la ficha al pasado por parametro
     */
    public void setSimbolo(char simbolo) throws Exception {
        Validacion.validarSiEsNulo(simbolo, "Simbolo");
        this.simbolo = simbolo;
    }

    /**
     * pre:
     * @param color no puede ser vacio ni nulo
     * @throws Exception si color es vacio o nulo
     * post: cambia el color de la ficha al pasado por parametro
     */
    public void setColor(String color) throws Exception {
        Validacion.validarSiEsUnaCadenaVacia(color, "Color");
        this.color = color;
    }
}