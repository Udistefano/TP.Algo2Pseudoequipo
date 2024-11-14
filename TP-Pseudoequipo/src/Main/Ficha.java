package Main;

import java.util.Objects;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
    private char simbolo = ' ';
    private String color = null;
    private EstadoDeBloqueo estadoDeBloqueo;

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
        this.estadoDeBloqueo = EstadoDeBloqueo.DESBLOQUEADO;
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
     * @return el hashCode de la ficha
     */
    @Override
    public int hashCode() {
        return Objects.hash(simbolo, color);
    }

//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * post: bloquea la ficha
     */
    public void bloquear() {
        this.estadoDeBloqueo = EstadoDeBloqueo.BLOQUEADO;
    }

    /**
     * pre: --
     * post: desbloquea la ficha
     */
    public void desbloquear() {
        this.estadoDeBloqueo = EstadoDeBloqueo.DESBLOQUEADO;
    }

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
     * @return el estado de bloqueo de la ficha
     */
    public EstadoDeBloqueo getEstadoDeBloqueo() {
		return estadoDeBloqueo;
	}

    /**
     * pre: --
     * @return verdadero si la ficha esta bloqueda, falso si no
     */
    public boolean estaBloqueada() {
        return estadoDeBloqueo == EstadoDeBloqueo.BLOQUEADO;
    }

    /**
     * pre: --
     * @return verdadero si la ficha esta desbloqueda, falso si no
     */
    public boolean estaDesbloqueada() {
        return estadoDeBloqueo == EstadoDeBloqueo.DESBLOQUEADO;
    }

//SETTERS SIMPLES -----------------------------------------------------------------------------------------

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