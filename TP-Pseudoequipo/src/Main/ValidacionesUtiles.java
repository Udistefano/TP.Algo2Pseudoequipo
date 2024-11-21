package Main;

import Cartas.Carta;
import Estructuras.Lista;

public class ValidacionesUtiles {
    /**
     * pre:
     * @param objeto no debe ser nulo
     * @param nombre no debe ser vacio
     * post: valida que el objeto con nombre pasado por parametro no sea nulo
     */
    public static void validarSiEsNulo(Object objeto, String nombre) throws Exception {
        if (nombre == null) {
            throw new Exception("El nombre del objeto a validar no puede ser nulo");
        }
        if (objeto == null) {
            throw new Exception("El objeto " + nombre + " no puede ser nulo");
        }
    }

    /**
     * pre:
     * @param cadena no debe ser nulo ni vacio
     * post: valida que cadena no sea nulo ni vacio
     */
    public static void validarSiEsUnaCadenaVacia(String cadena, String nombre) throws Exception {
        if ((cadena == null)
                || (cadena.isEmpty())) {
            throw new Exception("La cadena " + nombre + " no puede ser vacia");
        }
    }

    /**
     * pre:
     * @param caracter no debe estar vacio
     * post: valida que el caracter no este vacio
     */
    public static void validarSiEsCaracterVacio(char caracter) throws Exception {
        if (caracter == ' ') {
            throw new Exception("El caracter no puede ser vacio");
        }
    }

    /**
     * pre:
     * @param caracter debe ser una letra del abecedario
     * post: valida que el caracter sea una letra del abecedario
     */
    public static void validarSiEsUnaLetra(char caracter) throws Exception {
        int valorDecimal = Character.toLowerCase(caracter);
        // 97 = a, 122 = z, 164 = ñ
        if ((valorDecimal < 97) ||
                (valorDecimal > 122 && valorDecimal != 164)) {
            throw new Exception("El caracter no es un letra");
        }
    }
    
    /**
     * pre: debe existir la ficha en casillero
     * @param ficha no debe ser nulo
     * post: valida si la ficha esta bloqueada, sino, se puede utilizar normalmente
     */
    public static void validarSiFichaEstaBloqueada(Ficha ficha) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");
        if (ficha.estaBloqueada()) {
        	throw new Exception("\nNo se puede realizar la jugada, la ficha " + ficha + " esta bloqueada");
        }
    }

    /**
     * pre: numero
     * post: valida que numero no sea menor a 1
     */
    public static void validarSiNumeroEsMenorAUno(int numero, String nombre) throws Exception {
        if (numero < 1) {
            throw new Exception("El numero " + nombre + " no puede ser menor a uno");
        }
    }
    
    /**
     * pre: --
     * @param color
     * @param jugadores
     * @throws Exception si el color ya estaba elegido por otro jugador
     */
    public static void validarSiEsUnico(int color, Lista<Jugador> jugadores) throws Exception {
    	Color colorObtenido = Color.getColorJugador(color);
        if (!jugadores.estaVacia()) {
    		jugadores.iniciarCursor();
    		while(jugadores.avanzarCursor()) {
    			Jugador jugadorActual = jugadores.obtenerCursor();
    			if (jugadorActual.getColor() == colorObtenido) {
    				throw new Exception ("El color " + color + " ya esta asociado a otro jugador");
    			}
    		}
    	}
    }
    
    /**
     * pre: --
     * @param tamaño
     * @throws Exception si el parametro que le pasa del tamaño del tablero es mayor a 5 o menor a 0
     */
    public static void validarTamañoTablero(int tamaño) throws Exception {
    	if(tamaño > 5) {
    		throw new Exception("El tamaño maximo del tablero debe ser de 5 x 5 x 5");
    	}
    	if(tamaño <= 0) {
    		throw new Exception("El tamaño debe ser mayor a 0");
    	}
    }
    
    /**
     * pre: --
     * @param cantidadJugadores
     * @throws Exception si el parametro que le pasa del tamaño del tablero es mayor a 5 o menor a 0
     */
    public static void validarCantidadDeJugadores(int cantidadJugadores) throws Exception {
    	if(cantidadJugadores > 7) {
    		throw new Exception("Hay un maximo de 7 jugadores");
    	}
    	if(cantidadJugadores <= 0) {
    		throw new Exception("El tamaño debe ser mayor a 0");
    	}
    }
    
    /** NO BORRAR
     * pre: --
     * @param casillero
     * @param tablero
     * @throws Exception si el casillero no se encientra en el tablero o esta ocupado
     */
    public static void validarCasillero(Casillero casillero, Tablero tablero) throws Exception {
    	if(!tablero.existeElCasillero(casillero.getX(), casillero.getY(), casillero.getZ())){
    		throw new Exception("El casillero no se encuentra en el tablero");
    	}
    	if (casillero.estaOcupado()) {
            throw new Exception("\nEl casillero esta ocupado");
        }
    }
    
    /** NO BORRAR este valida cuando se pretende mover o selecionar casillero con ficha
     * pre: --
     * @param casillero
     * @param tablero
     * @param jugadorActual
     * @throws Exception si el casillero no se  encuentra en el tablero, si la ficha pertenece a otro jugador o si no hay fichas en el casillero
     */
    public static void validarCasilleroAJugar(Casillero<Ficha> casillero, Tablero tablero,  Jugador jugadorActual) throws Exception {
    	if(!tablero.existeElCasillero(casillero.getX(), casillero.getY(), casillero.getZ())){
    		throw new Exception("El casillero no se encuentra en el tablero");
    	}
    	if(casillero.getDato() == null) {
    		throw new Exception("No hay fichas en este casillero");
    	}
    	Ficha ficha = casillero.getDato();
    	if(ficha.getColor() != jugadorActual.getColor()) {
    		throw new Exception("La ficha de este casillero no pertenece al jugador actual");
    	}
    	
    }
    
    /**
     * pre: --
     * @param casillero
     * @param movimiento
     * @throws Exception si el movimiento no esta permitido
     */
    public static void validarMovimiento(Casillero casillero, Movimiento movimiento) throws Exception {
    	if (!casillero.existeElVecino(movimiento)) {
            throw new Exception("\nNo existe el movimiento " + movimiento);
        }
        if (casillero.getCasilleroVecino(movimiento).estaOcupado()) {
            throw new Exception("\nEl casillero en direccion " + movimiento + " al que se quiere mover esta ocupado");
        }
    }
    
    public static void validarCarta(int posicionCarta, Lista<Carta> mano) throws Exception {
    	if(posicionCarta <0 || posicionCarta > mano.getLongitud()) {
    		throw new Exception("Elija una carta entre el 0 y " + mano.getLongitud());
    	}
    }
}
	
