package Main;

public class Ficha {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------
    private char simbolo = ' ';
    private String color = null;

//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param simbolo no puede ser vacio
     * @param color no puede ser vacio ni nulo
     * @throws Exception si alguno de los parametros es nulo
     * post: inicializa la ficha con el simbolo y color dados
     */
    public Ficha(char simbolo, String color) throws Exception {
        if (simbolo == ' ') {
            throw new Exception("El simbolo no puede ser vacio");
        }
        if ((color == null) ||
                (color.isEmpty())) {
            throw new Exception("El color no puede ser vacio");
        }
        this.simbolo = simbolo;
        this.color = color;
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

//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    // FIXME: los setters de simbolo y color de Ficha deben ser public o protected?
    /**
     * pre:
     * @param simbolo no puede ser vacio
     * @throws Exception si simbolo es vacio
     * post: cambia el simbolo de la ficha al pasado por parametro
     */
    public void setSimbolo(char simbolo) throws Exception {
        if (simbolo == ' ') {
            throw new Exception("El simbolo no puede ser vacio");
        }
        this.simbolo = simbolo;
    }

    /**
     * pre:
     * @param color no puede ser vacio ni nulo
     * @throws Exception si color es vacio o nulo
     * post: cambia el color de la ficha al pasado por parametro
     */
    public void setColor(String color) throws Exception {
        if ((color == null) ||
                (color.isEmpty())) {
            throw new Exception("El color no puede ser vacio");
        }
        this.color = color;
    }
}