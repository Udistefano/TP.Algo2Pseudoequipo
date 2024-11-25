package Main;

import Cartas.Carta;
import Estructuras.ListaSimple;

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
     * pre: debe existir la ficha en casillero
     * @param ficha no debe ser nulo
     * post: valida si la ficha esta bloqueada, sino, se puede utilizar normalmente
     */
    public static void validarSiFichaEstaBloqueada(Ficha ficha) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");
        if (ficha.estaBloqueada()) {
        	throw new Exception("No se puede realizar la jugada, la ficha " + ficha + " esta bloqueada");
        }
    }

    /**
     * pre: numero
     * post: valida que numero no sea menor a 1
     */
    public static void validarSiNumeroEsMenorAUno(int numero, String nombre) throws Exception {
        if (numero < 1) {
            throw new Exception("El numero " + nombre + " " + numero + " no puede ser menor a uno");
        }
    }
    
    /**
     * pre:
     * @param color debe estar entre 1 y 7
     * @param jugadores no puede ser nulo
     * @throws Exception si el color ya estaba elegido por otro jugador, o si color o jugadores son invalidos
     */
    public static void validarSiColorEsUnico(int color, ListaSimple<Jugador> jugadores) throws Exception {
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
    public static void validarTamañoDelTablero(int tamaño) throws Exception {
        if(tamaño > Tablero.getTamañoMaximoDeCoordenada()) {
    		throw new Exception("El tamaño " + tamaño + " del tablero no puede superar al tamaño maximo permitido " +
                                Tablero.getTamañoMaximoDeCoordenada());
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
        if(cantidadJugadores > Partida.getCantidadMaximaDeJugadores()) {
    		throw new Exception("Hay un maximo de " + Partida.getCantidadMaximaDeJugadores() + " jugadores");
    	}
    	if(cantidadJugadores <= 0) {
    		throw new Exception("Tiene que haber minimo un jugador");
    	}
    }
    
    /**
     * pre:
     * @param casillero no puede ser nulo
     * @param tablero no puede ser nulo
     * @throws Exception si el casillero no se encuentra en el tablero, o si alguno de los parametros es nulo
     */
    public static void validarSiCasilleroExiste(Casillero<Ficha> casillero, Tablero<Ficha> tablero) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        if(!tablero.existeElCasillero(casillero.getX(), casillero.getY(), casillero.getZ())){
            throw new Exception("El casillero no se encuentra en el tablero");
        }
    }

    /**
     * pre:
     * @param casillero no puede ser nulo
     * @param tablero no puede ser nulo
     * @throws Exception si el casillero no se encuentra en el tablero o esta ocupado, o si alguno de los parametros es
     *                   nulo
     */
    public static void validarSiCasilleroEstaLibre(Casillero<Ficha> casillero, Tablero<Ficha> tablero) throws Exception {
        validarSiCasilleroExiste(casillero, tablero);
        if (casillero.estaOcupado()) {
            throw new Exception("El casillero esta ocupado");
        }
    }

    /**
     * pre:
     * @param casillero no puede ser nulo
     * @param tablero no puede ser nulo
     * @throws Exception si el casillero no se encuentra en el tablero o esta libre, o si alguno de los parametros es
     *                   nulo
     */
    public static void validarSiCasilleroEstaOcupado(Casillero<Ficha> casillero, Tablero<Ficha> tablero) throws Exception {
        validarSiCasilleroExiste(casillero, tablero);
        if (!casillero.estaOcupado()) {
            throw new Exception("El casillero esta vacio");
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
    public static void validarCasilleroAMover(Casillero<Ficha> casillero, Tablero<Ficha> tablero,  Jugador jugadorActual) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugadorActual, "Jugador");
    	validarSiCasilleroEstaOcupado(casillero, tablero);
    	Ficha ficha = casillero.getDato();
    	if(!ficha.getColor().equals(jugadorActual.getColor())) {
    		throw new Exception("La ficha de este casillero no pertenece al jugador actual");
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
            throw new Exception("No existe el movimiento " + movimiento);
        }
        if (casillero.getCasilleroVecino(movimiento).estaOcupado()) {
            throw new Exception("El casillero en direccion " + movimiento + " al que se quiere mover esta ocupado");
        }
    }
    
    /**
     * pre:
     * @param posicionCarta no puede ser menor a 0, ni mayor a la longitud de la mano
     * @param mano no puede ser nulo
     * @throws Exception si mano es nulo, o si la posicion de la carta es invalida
     */
    public static void validarCarta(int posicionCarta, ListaSimple<Carta> mano) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(mano, "Mano");
    	if((posicionCarta < 0) ||
            (posicionCarta > mano.getLongitud())) {
    		throw new Exception("Elija una carta entre el 0 y " + mano.getLongitud());
    	}
    }

    /**
     * pre:
     * @param ficha no puede ser nulo
     * @param jugador no puede ser nulo
     * @throws Exception si la ficha no es del jugador, o si algun parametro es nulo
     */
    public static void validarFichaEsPropia(Ficha ficha, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
        if (!ficha.getColor().equals(jugador.getColor())) {
            throw new Exception("El color de la ficha (" + ficha.getColor() + ") es distinto al del jugador (" +
                                jugador.getColor() + ")");
        }
    }

    /**
     * pre:
     * @param ficha no puede ser nulo
     * @param jugador no puede ser nulo
     * @throws Exception si la ficha es del jugador, o si algun parametro es nulo
     */
    public static void validarFichaNoEsPropia(Ficha ficha, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
        if (ficha.getColor().equals(jugador.getColor())) {
            throw new Exception("El color de la ficha (" + ficha.getColor() + ") es igual al del jugador (" +
                                jugador.getColor() + ")");
        }
    }

    public static void validarCoordenadaDeImagen(int x, int dimensionX) throws Exception {
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(x, "X");
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(dimensionX, "Dimension X");
        if (x > dimensionX) {
            throw new Exception("La coordenada X " + x + " no puede ser mayor a la dimension X del tablero " +
                                dimensionX);
        }
    }

    /**
     * pre:
     * @param nombre no puede ser nulo ni vacio
     * @param jugadores no puede ser nulo
     * @throws Exception si ya hay un jugador con el nombre pasado por parametro
     */
    public static void validarSiNombreEsUnico(String nombre, ListaSimple<Jugador> jugadores) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(jugadores, "Jugadores");
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(nombre, "Nombre");
        jugadores.iniciarCursor();
        while (jugadores.avanzarCursor()) {
            if (jugadores.obtenerCursor().getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                throw new Exception("Ya hay un jugador con el nombre " + nombre);
            }
        }
    }

    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugador no puede ser nulo
     * @throws Exception si no hay fichas enemigas en el tablero, o si algun parametro es nulo
     */
    public static void validarSiHayFichasEnemigasHabilitadasEnElTablero(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");

        for (int x = 1; x <= tablero.getAncho(); x++) {
            for (int y = 1; y <= tablero.getAlto(); y++) {
                for (int z = 1; z <= tablero.getProfundidad(); z++) {
                    Ficha ficha = tablero.obtener(x, y, z);
                    if ((ficha != null) &&
                            (!ficha.getColor().equals(jugador.getColor())) &&
                            (!ficha.estaBloqueada())) {
                        return;
                    }
                }
            }
        }

        throw new Exception("No hay fichas enemigas habilitadas en el tablero");
    }

    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugador no puede ser nulo
     * @throws Exception si no hay fichas en el tablero, o si algun parametro es nulo
     */
    public static void validarSiHayFichasHabilitadasEnElTablero(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");

        for (int x = 1; x <= tablero.getAncho(); x++) {
            for (int y = 1; y <= tablero.getAlto(); y++) {
                for (int z = 1; z <= tablero.getProfundidad(); z++) {
                    Ficha ficha = tablero.obtener(x, y, z);
                    if ((ficha != null) &&
                            (!ficha.estaBloqueada())) {
                        return;
                    }
                }
            }
        }

        throw new Exception("No hay fichas habilitadas en el tablero");
    }
}
	
