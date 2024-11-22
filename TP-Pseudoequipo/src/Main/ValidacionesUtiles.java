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
     * pre:
     * @param color debe estar entre 1 y 7
     * @param jugadores no puede ser nulo
     * @throws Exception si el color ya estaba elegido por otro jugador, o si color o jugadores son invalidos
     */
    public static void validarSiEsUnico(int color, Lista<Jugador> jugadores) throws Exception {
    	Color colorObtenido = Color.getColorJugador(color);
        ValidacionesUtiles.validarSiEsNulo(jugadores, "Jugadores");
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
     * pre:
     * @param tamaño no puede ser menor a 1, ni mayor al tamaño maximo permitido del tablero
     * @throws Exception si el parametro que le pasa del tamaño del tablero es mayor a 5 o menor a 0
     */
    public static void validarTamañoTablero(int tamaño) throws Exception {
    	// FIXME: reparar hardcodeo del tamaño maximo del tablero, agregar constante a Tablero quiza?
        if(tamaño > 5) {
    		throw new Exception("El tamaño maximo del tablero debe ser de 5 x 5 x 5");
    	}
    	if(tamaño <= 0) {
    		throw new Exception("El tamaño debe ser mayor a 0");
    	}
    }
    
    /**
     * pre:
     * @param cantidadJugadores no puede ser menor a 1, ni mayor al maximo permitido de jugadores
     * @throws Exception si la cantidad de jugadores es menor a 1, o mayor al maximo de jugadores
     */
    public static void validarCantidadDeJugadores(int cantidadJugadores) throws Exception {
    	// FIXME: reparar hardcodeo del maximo de jugadores, agregar constante a Partida quiza?
        if(cantidadJugadores > 7) {
    		throw new Exception("Hay un maximo de 7 jugadores");
    	}
    	if(cantidadJugadores <= 0) {
    		throw new Exception("El tamaño debe ser mayor a 0");
    	}
    }
    
    /** NO BORRAR
     * pre:
     * @param casillero no puede ser nulo
     * @param tablero no puede ser nulo
     * @throws Exception si el casillero no se encientra en el tablero o esta ocupado, o si alguno
     *                   de los parametros es nulo
     */
    public static void validarCasillero(Casillero<Ficha> casillero, Tablero<Ficha> tablero) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
    	if(!tablero.existeElCasillero(casillero.getX(), casillero.getY(), casillero.getZ())){
    		throw new Exception("\nEl casillero no se encuentra en el tablero");
    	}
    	if (casillero.estaOcupado()) {
            throw new Exception("\nEl casillero esta ocupado");
        }
    }
    
    /** NO BORRAR este valida cuando se pretende mover o selecionar casillero con ficha
     * pre:
     * @param casillero no puede ser nulo
     * @param tablero no puede ser nulo
     * @param jugadorActual no puede ser nulo
     * @throws Exception si el casillero no se  encuentra en el tablero, si la ficha pertenece a otro jugador o si no
     *                   hay fichas en el casillero, o si alguno de los parametros es nulo
     */
    public static void validarCasilleroAJugar(Casillero<Ficha> casillero, Tablero<Ficha> tablero,  Jugador jugadorActual) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugadorActual, "Jugador");
    	if(!tablero.existeElCasillero(casillero.getX(), casillero.getY(), casillero.getZ())){
    		throw new Exception("\nEl casillero no se encuentra en el tablero");
    	}
    	if(casillero.getDato() == null) {
    		throw new Exception("\nNo hay fichas en este casillero");
    	}
    	Ficha ficha = casillero.getDato();
    	if(ficha.getColor() != jugadorActual.getColor()) {
    		throw new Exception("\nLa ficha de este casillero no pertenece al jugador actual");
    	}
    	
    }
    
    /**
     * pre: --
     * @param casillero no puede ser nulo
     * @param movimiento no puede ser nulo
     * @throws Exception si el movimiento no esta permitido
     */
    public static void validarMovimiento(Casillero<Ficha> casillero, Movimiento movimiento) throws Exception {
    	if (!casillero.existeElVecino(movimiento)) {
            throw new Exception("\nNo existe el movimiento " + movimiento);
        }
        if (casillero.getCasilleroVecino(movimiento).estaOcupado()) {
            throw new Exception("\nEl casillero en direccion " + movimiento + " al que se quiere mover esta ocupado");
        }
    }
    
    /**
     * pre:
     * @param posicionCarta no puede ser menor a 0, ni mayor a la longitud de la mano
     * @param mano no puede ser nulo
     * @throws Exception si mano es nulo, o si la posicion de la carta es invalida
     */
    public static void validarCarta(int posicionCarta, Lista<Carta> mano) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(mano, "Mano");
    	if((posicionCarta < 0) ||
            (posicionCarta > mano.getLongitud())) {
    		throw new Exception("\nElija una carta entre el 0 y " + mano.getLongitud());
    	}
    }
}
	
