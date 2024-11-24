package Main;

import java.util.Objects;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Color color = null;
    private EstadoDeBloqueo estadoDeBloqueo;

//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param color no puede ser vacio ni nulo
     * @throws Exception si alguno de los parametros es nulo
     * post: inicializa la ficha con el simbolo y color dados
     */
    public Ficha(Color color) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(color, "Color");
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
        return "" + this.color;
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
        return (this.color.equals(other.getColor()));
    }

    /**
     * pre: --
     * @return el hashCode de la ficha
     */
    @Override
    public int hashCode() {
        return Objects.hash(color);
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
     * @return el color de la ficha
     */
    public Color getColor() {
        Color copiaDeColor = this.color;
        return copiaDeColor;
    }
    
    /**
     * pre: --
     * @return el estado de bloqueo de la ficha
     */
    public EstadoDeBloqueo getEstadoDeBloqueo() {
        EstadoDeBloqueo copiaDeEstadoDeBloqueo = this.estadoDeBloqueo;
		return copiaDeEstadoDeBloqueo;
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

    /**
     * pre:
     * @param color no puede ser vacio ni nulo
     * @throws Exception si color es vacio o nulo
     * post: cambia el color de la ficha al pasado por parametro
     */
    public void setColor(Color color) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(color, "Color");
        this.color = color;
    }
}