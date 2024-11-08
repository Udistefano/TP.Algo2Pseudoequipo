package Main;

import java.util.Scanner;

public class Teclado {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    private static Scanner teclado = null;

    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre: --
     * post: inicializa el teclado
     */
    public Teclado() {
        teclado = new Scanner(System.in);
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------

    /**
     * pre: --
     * post: inicializamos el teclado si no lo esta
     */
    public static void inicializar() {
        if (teclado == null) {
            teclado = new Scanner(System.in);
        }
    }

    /**
     * pre: --
     * post: finalizamos el teclado si esta inicializado
     */
    public static void finalizar() {
        if (teclado != null) {
            teclado.close();
        }
    }

    public static String preguntarNombreJugadorAEliminar() {
        // NO ESTA IMPLEMENTADO TODAVIA!
        System.out.println("Que jugador quiere eliminar?");
        preguntarNombreJugador();
    }

    public static String preguntarNombreJugador() {
        // NO ESTA IMPLEMENTADO TODAVIA!
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
