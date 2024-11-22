package Main;

import Cartas.Carta;
import Estructuras.Lista;

import static org.junit.Assert.assertNotNull;

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
     * pre:
     * @param mano no puede ser nulo
     * post: le pregunta la carta al jugador de la mano, y la retorna
     * @throws Exception si la mano es nula
     */
    public static Carta preguntarCarta(Lista<Carta> mano) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(mano, "Mano");
    	Carta carta = null;
    	boolean cartaValida = false;
    	while(!cartaValida) {
    		try {
        		System.out.println("\nCartas disponibles:");
                System.out.println(0 + " - " + "Si no quiere jugar cartas");
                mano.iniciarCursor();
                for(int i = 1; i <= mano.getLongitud(); i++){
                    mano.avanzarCursor();
                    System.out.println(i + " - " + mano.obtenerCursor());
                }
                System.out.print("\nIngrese el numero de la posicion de la carta a jugar: ");
                int posicion = leerNumeroNatural();
                ValidacionesUtiles.validarCarta(posicion, mano);
                if(posicion != 0) {
                	carta = mano.obtener(posicion);
                }
                carta = null;
                cartaValida = true;
        	} catch (Exception e) {
        		System.out.println("Error: " + e.getMessage());
        	}
    	}
        return carta;
    }
    
    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugador no puede ser nulo
     * post: le pregunta al jugador el casillero a jugador, lo obtiene y lo retorna
     * @throws Exception si alguno de los parametros es nulo
     */
    public static Casillero<Ficha> preguntarCasilleroAJugar(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
    	Casillero<Ficha> casillero = null;
    	boolean coordenadasValidas = false;
    	while(!coordenadasValidas) {
    		try {
    			int x = Teclado.preguntarCoordenada('X');
                int y = Teclado.preguntarCoordenada('Y');
                int z = Teclado.preguntarCoordenada('Z');
                casillero = tablero.getCasillero(x, y, z);
                ValidacionesUtiles.validarCasilleroAJugar(casillero, tablero, jugador);
                coordenadasValidas = true;
    		} catch (Exception e) {
    			System.out.println("Error: " + e.getMessage());
    		}    	
    	}
    	return casillero;
       
    }
    /**
     * pre:
     * @param jugadores no puede ser nulo
     * @return el numero del color que el jugador elija
     * @throws Exception si jugadores es nulo
     */
    public static int preguntarColor(Lista<Jugador> jugadores) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(jugadores, "Lista de jugadores");
        System.out.println("\nColores disponibles:");
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println((i + 1) + " - " + Color.values()[i]);
        }
        int color = 0;
    	boolean numeroValido = false;
    	while(!numeroValido) {
    		try {
    			System.out.print("\nElija un color (escriba el numero): ");
        		color = leerNumeroNatural();
        		ValidacionesUtiles.validarSiEsUnico(color, jugadores);
        		numeroValido = true;
    		} catch (Exception e) {
                System.out.println("\nOcurrio un error al preguntar el color: " + e.getMessage() + "\n");
            }
    	}
    	return color;
    }

    /**
     * pre: --
     * @return un movimiento valido ingresado por el usuario
     * @throws Exception si hay un error interno leyendo la cadena ingresada por el usuario
     */
    public static Movimiento preguntarMovimiento(Casillero casillero) throws Exception {
    	System.out.println("Ingrese un movimiento: ");
    	boolean movimientoValido = false;
    	Movimiento movimiento = null;
    	int numeroMovimiento = 0;
    	while(!movimientoValido) {
    		try {
    			System.out.println("Movimientos disponibles:");
    	        for (int i = 0; i < Movimiento.values().length; i++) {
    	        	System.out.println((i + 1) + " - " + Movimiento.values()[i]);
    	        }
    	        System.out.print("\nElija un movimiento (escriba el numero): ");
    	        numeroMovimiento = leerNumeroNatural();
    	        movimiento = Movimiento.getMovimientoFicha(numeroMovimiento);
    	        ValidacionesUtiles.validarMovimiento(casillero, movimiento);
    	        movimientoValido = true;
    	        } catch (Exception e) {
    	        	System.out.println("Error: " + e.getMessage());
    		}
    	}
        return movimiento;
    }

    /**
     * pre:
     * @param nombreDeCoordenada debe ser una letra del abecedario
     * @return el valor de la coordenada ingresado por el usuario
     * @throws Exception si nombreDeCoordenada no es una letra del abecedario
     */
    public static int preguntarCoordenada(char nombreDeCoordenada) throws Exception {
        // TODO: Validacion.validarLetra(coordenada)
        System.out.print("Ingrese la coordenada " + nombreDeCoordenada + ": ");
        return leerNumero();
    }

    /**
     * pre: debe colocarse en una tipo int en el main
     * @param mensaje no puede ser vacio ni nulo
     * post: pregunta la coordenada del tablero a los jugadores y valida mediante un try catch
     * @throws Exception si mensaje es vacio o nulo
     */
    public static int preguntarCoordenadaTablero(String mensaje) throws Exception {
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(mensaje, "Pregunta de coordenada");
    	int coordenada = 0;
    	boolean numeroValido = false;
    	while(!numeroValido) {
    		try {
    			System.out.print(mensaje);
        		coordenada = Teclado.leerNumeroNatural();
        		ValidacionesUtiles.validarTamañoTablero(coordenada);
        		numeroValido = true;
        		
    		} catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
    	}
    	return coordenada;
    }
    
    /**
     * pre: debe colocarse en una tipo int en el main
     * @param mensaje no puede ser nulo ni vacio
     * post: pregunta la cantidad de jugadores a jugar
     * @throws Exception si mensaje es vacio o nulo
     */
    public static int preguntarCantidadJugadores(String mensaje) throws Exception {
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(mensaje, "Pregunta de coordenada");
    	int cantidadJugadores = 0;
    	boolean numeroValido = false;
    	while(!numeroValido) {
    		try {
    			System.out.print("\nCuantos jugadores seran en este juego?: ");
    			cantidadJugadores = Teclado.leerNumeroNatural();
    			ValidacionesUtiles.validarCantidadDeJugadores(cantidadJugadores);
    			numeroValido = true;
    		} catch (Exception e) {
    			System.out.println("Error: " + e.getMessage());
    		}
    	}
    	return cantidadJugadores;
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
