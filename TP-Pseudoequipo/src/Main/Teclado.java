package Main;

import Cartas.Carta;

import java.util.Scanner;

public class Teclado {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    // TODO: habria que preguntarle al profe si esta bien que usemos un teclado estatico
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

    /**
     * pre: --
     * post: le pregunta al usuario el nombre del jugador a eliminar, y lee el input del usuario
     * @throws Exception si hubo un error leyendo la cadena no vacia del usuario
     */
    public static String preguntarNombreDelJugadorAEliminar() throws Exception {
        System.out.println("Ingrese el nombre del jugador a eliminar: ");
        return leerCadenaNoVacia();
    }

    /**
     * pre: --
     * post: le pregunta al usuario su nombre, y lee el input del usuario
     * @throws Exception si hubo un error leyendo la cadena no vacia del usuario
     */
    public static String preguntarNombreDelJugador() throws Exception {
        System.out.println("Ingrese su nombre: ");
        return leerCadenaNoVacia();
    }

    public static Carta preguntarCarta() {

    }

    /**
     * pre: --
     * @return la cadena no vacia ni nula que el usuario ingrese por teclado
     * @throws Exception si hubo un error leyendo el input del usuario
     */
    private static String leerCadenaNoVacia() throws Exception {
        String cadena = null;
        do {
            cadena = leerCadena();
        } while ((cadena == null) ||
                (cadena.isEmpty()));
        return cadena;

    }

    /**
     * pre: --
     * @return la cadena que el usuario ingrese por teclado
     * @throws Exception si hubo un error leyendo el input del usuario
     */
    private static String leerCadena() throws Exception {
        if (teclado.hasNextLine()) {
            return teclado.nextLine();
        }
        throw new Exception("Error leyendo el input por teclado del usuario");
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
