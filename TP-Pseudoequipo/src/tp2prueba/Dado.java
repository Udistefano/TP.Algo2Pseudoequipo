package tp2prueba;

import java.util.Random;

public class Dado {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    private static int VALOR_MINIMO = 1;
    private static int VALOR_MAXIMO = 6;
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private int valor = 0;
    private Random random = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Dado() {
        this.random = new Random();
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: -
     * post: le da al dado un valor entre VALOR_MINIMO y VALOR_MAXIMO
     */
    public void jugarDado() {
        this.valor = random.nextInt(VALOR_MINIMO, VALOR_MAXIMO + 1); 
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: -
     * @return el valor del dado
     */
    public int getValor() {
        return this.valor;
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
