package Main;

import Cartas.Carta;
import Estructuras.Lista;

import java.util.Scanner;

public class Teclado {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    // TODO: habria que preguntarle al profe si esta bien que usemos un teclado estatico, o pasarlo a partida como
    //     : atributo
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
     * post: le pregunta al usuario el nombre del usuario a eliminar, y lee el input del usuario
     * @throws Exception si hubo un error leyendo la cadena no vacia del usuario
     */
    public static String preguntarNombreDelJugadorAEliminar() throws Exception {
        System.out.println("Ingrese el nombre del usuario a eliminar: ");
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

    /**
     * pre: --
     * post: 
     */
    public static Carta preguntarCarta(Lista<Carta> mano) throws Exception {
        // TODO: preguntarCarta quiza habria que moverlo a Partida, sabe mucho para Teclado?¿¿?¿?
        System.out.println("Ahora tendra que jugar una carta");
        mano.iniciarCursor();
        System.out.println(0 + "-" + "Si no quiere jugar cartas");
        for(int i = 1;i <= mano.getLongitud();i++){
            mano.avanzarCursor();
            System.out.println(i + "-" + mano.obtenerCursor());
        }
        System.out.println("Ingrese el Numero de la posicion de la carta a jugar: ");
        int posicion = leerNumeroNatural();
        if(posicion != 0) {
        	return mano.obtener(posicion);
        }
        else {
        	return null;
        }
    }

    /**
     * pre: --
     * @return un movimiento valido ingresado por el usuario
     * @throws Exception si hay un error interno leyendo la cadena ingresada por el usuario
     */
    public static Movimiento preguntarMovimiento() throws Exception {
        System.out.println("Ingrese un movimiento: ");
        System.out.println("Movimientos disponibles: arriba, abajo, derecha, izquierda, atras, adelante");
        String movimiento;

        do {
            movimiento = leerCadenaNoVacia();
        } while (!Movimiento.existe(movimiento));

        return Movimiento.obtener(movimiento);
    }

    /**
     * pre:
     * @param nombreDeCoordenada debe ser una letra del abecedario
     * @return el valor de la coordenada ingresado por el usuario
     * @throws Exception si nombreDeCoordenada no es una letra del abecedario
     */
    public static int preguntarCoordenada(char nombreDeCoordenada) throws Exception {
        // TODO: Validacion.validarLetra(coordenada)
        System.out.println("Ingrese la coordenada " + nombreDeCoordenada + ": ");
        return leerNumeroNatural();
    }

    /**
     * pre: --
     * @return lee un numero natural ingresado por el usuario
     */
    public static int leerNumeroNatural() {
        int numeroNatural;

        do {
            numeroNatural = leerNumero();
        } while (numeroNatural < 0);

        return numeroNatural;
    }

    /**
     * pre: --
     * @return lee un numero ingresado por el usuario
     */
    public static int leerNumero() {
        Integer numero = null;

        do {
            try {
                numero = teclado.nextInt();
            } catch (Exception e) {}
        } while (numero == null);

        return numero;
    }

    /**
     * pre: --
     * @return la cadena no vacia ni nula que el usuario ingrese por teclado
     * @throws Exception si hubo un error leyendo el input del usuario
     */
    public static String leerCadenaNoVacia() throws Exception {
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
    public static String leerCadena() throws Exception {
        if (teclado.hasNextLine()) {
            return teclado.nextLine();
        }
        throw new Exception("Error leyendo el input por teclado del usuario");
    }

    /**
     * pre: --
     * @return el caracter que el usuario ingrese por teclado
     * @throws Exception si hubo un error leyendo el input del usuario
     */
    public static Character leerCaracter() throws Exception {
        return leerCadena().charAt(0);
    }

    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
