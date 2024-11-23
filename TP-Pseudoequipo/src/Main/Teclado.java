package Main;

import Cartas.Carta;
import Estructuras.ListaSimple;

import java.util.Scanner;

// TODO: quiza dividir clase Teclado, en clases Teclado y Menu???????
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
     * @param jugador no puede ser nulo
     * post: le pregunta la carta al jugador de la mano, y la retorna
     * @throws Exception si el jugador es nulo
     */
    public static Carta preguntarCarta(Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
    	Carta carta = null;
    	boolean cartaValida = false;

    	while(!cartaValida) {
    		try {
        		System.out.println("\nCartas disponibles:");
                System.out.println(0 + " - " + "Si no quiere jugar cartas");
                jugador.mostrarMano();

                System.out.print("\nIngrese el numero de la carta a jugar: ");
                int posicion = leerNumero();
                ValidacionesUtiles.validarCarta(posicion, jugador.getMano());

                if(posicion != 0) {
                	carta = jugador.getMano().obtener(posicion);
                }
                cartaValida = true;
        	} catch (Exception e) {
        		UtilesVarios.mostrarError(e);
        	}
    	}

        return carta;
    }

    /**
     * pre: --
     * @param tablero no puede ser nulo
     * @throws Exception si no existe el casillero con las coordenadas ingresadas por el jugador, o si tablero es nulo
     * post: le pregunta al jugador las coordenadas de un casillero, valida que exista ese casillero, y lo devuelve
     */
    public static Casillero<Ficha> preguntarCasillero(Tablero<Ficha> tablero) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        Casillero<Ficha> casillero = null;
        boolean coordenadasValidas = false;
    
        while(!coordenadasValidas) {
            try {
                System.out.println("");
                int x = Teclado.preguntarCoordenada('X');
                int y = Teclado.preguntarCoordenada('Y');
                int z = Teclado.preguntarCoordenada('Z');
                casillero = tablero.getCasillero(x, y, z);

                ValidacionesUtiles.validarSiCasilleroExiste(casillero, tablero);
                coordenadasValidas = true;
            } catch (Exception e) {
                UtilesVarios.mostrarError(e);
            }    	
        }
    
        return casillero;
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
        boolean casilleroInvalido = true;
        
        do {
            try {
                casillero = preguntarCasillero(tablero);
                ValidacionesUtiles.validarSiCasilleroEstaOcupado(casillero, tablero);
                Ficha ficha = casillero.getDato();
                if(!ficha.getColor().equals(jugador.getColor())) {
                    throw new Exception("La ficha de este casillero no pertenece al jugador actual");
                }
                casilleroInvalido = false;
            } catch (Exception e) {
    			UtilesVarios.mostrarError(e);
    		} 
        } while (casilleroInvalido);
        
    	return casillero;
    }

    /**
     * pre:
     * @param jugadores no puede ser nulo
     * @return el numero del color que el jugador elija
     * @throws Exception si jugadores es nulo
     */
    public static int preguntarColor(ListaSimple<Jugador> jugadores) throws Exception {
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
        		color = leerNumero();
        		ValidacionesUtiles.validarSiColorEsUnico(color, jugadores);
        		numeroValido = true;
    		} catch (Exception e) {
                UtilesVarios.mostrarError(e);
            }
    	}
    	return color;
    }

    /**
     * pre: --
     * @return un movimiento valido ingresado por el usuario
     * @throws Exception si hay un error interno leyendo la cadena ingresada por el usuario
     */
    public static Movimiento preguntarMovimiento(Casillero<Ficha> casillero) throws Exception {
    	boolean movimientoValido = false;
    	Movimiento movimiento = null;
    	int numeroMovimiento = 0;
    	while(!movimientoValido) {
    		try {
    			System.out.println("\nMovimientos disponibles:");
    	        for (int i = 0; i < Movimiento.values().length; i++) {
    	        	System.out.println((i + 1) + " - " + Movimiento.values()[i]);
    	        }

    	        System.out.print("\nElija un movimiento (escriba el numero): ");
    	        numeroMovimiento = leerNumero();
    	        movimiento = Movimiento.getMovimientoFicha(numeroMovimiento);
    	        ValidacionesUtiles.validarMovimiento(casillero, movimiento);
    	        movimientoValido = true;
            } catch (Exception e) {
                UtilesVarios.mostrarError(e);
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
        		coordenada = Teclado.leerNumero();
        		ValidacionesUtiles.validarTamañoDelTablero(coordenada);
        		numeroValido = true;
    		} catch (Exception e) {
                UtilesVarios.mostrarError(e);
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
    			System.out.print(mensaje);
    			cantidadJugadores = Teclado.leerNumero();
    			ValidacionesUtiles.validarCantidadDeJugadores(cantidadJugadores);
    			numeroValido = true;
    		} catch (Exception e) {
    			UtilesVarios.mostrarError(e);
    		}
    	}
    	return cantidadJugadores;
    }
    
    /**
     * pre: --
     * @return lee un numero ingresado por el usuario
     * @throws Exception si hubo un error leyendo el input del usuario
     */
    public static Integer leerNumero() throws Exception {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (Exception e) {
            throw new Exception("Ingreso un numero invalido");
        }
    }

    /**
     * pre: --
     * @return la cadena que el usuario ingrese por teclado
     * @throws Exception si hubo un error leyendo el input del usuario, o si ingreso una cadena invalida
     */
    public static String leerCadena() throws Exception {
        try {
            String cadena = teclado.nextLine();
            if ((cadena == null) ||
                (cadena.isEmpty())) {
                throw new Exception("Ingreso una cadena invalida");
            }
            return cadena;
        } catch (Exception e) {
            throw new Exception("Fallo inesperado al leer el input del usuario");
        }
    }

    /**
     * pre: --
     * @param mensaje no puede ser nulo ni vacio
     * @throws Exception si mensaje es nulo o vacio
     * post: le pregunta el nombre del jugador, valida que sea correcto, y lo regresa
    */
    public static String preguntarNombreDeJugador(String mensaje) throws Exception {
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(mensaje, "Mensaje a imprimir");
        boolean esNombreInvalido = true;
        String nombre = "";

        do {
            try {
                System.out.print(mensaje);
    			// TODO: validar que sea un nombre valido
                nombre = leerCadena();
                esNombreInvalido = false;
            } catch (Exception e) {
                UtilesVarios.mostrarError(e);
            }
        } while (esNombreInvalido);
        
        return nombre;
    }
    
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
