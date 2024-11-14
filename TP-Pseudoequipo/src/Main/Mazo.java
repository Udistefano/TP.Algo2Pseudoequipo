package Main;

import Estructuras.Lista;
import Estructuras.Pila;

import Cartas.Carta;
import Cartas.CartaAnularCasillero;
import Cartas.CartaBloquearFicha;
import Cartas.CartaCambiarColorFicha;
import Cartas.CartaDobleTurno;
import Cartas.CartaEliminarCartasDelJugador;
import Cartas.CartaPerderTurno;
import Cartas.CartaVolverJugadaAnterior;

import java.util.Random;


@SuppressWarnings("rawtypes")
public class Mazo {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

	private static int VALOR_MINIMO = 1;
	private static int VALOR_MAXIMO = 7;

	//ATRIBUTOS -----------------------------------------------------------------------------------------------

	// FIXME: el enunciado del TP, dice que el mazo debe ser una cola de cartas, no una pila de cartas
	public Pila <Carta> cartas = null;
    private int maximoDeCartasEnMazo;
    private Random random;

	//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre: --
     * pos: crea un mazo de cartas con 50 cartas de todos los tipos. No hay un maximo de cartas por tipo
     */
    public Mazo(int maximoCartas) {
    	this.maximoDeCartasEnMazo = maximoCartas;
    	this.cartas = new Pila<Carta>();
    	this.random = new Random();
    	Carta cartaASumar = null;
    	for (int i = 0; i < maximoDeCartasEnMazo; i++) {
    		cartaASumar = elegirTituloPorId();
			this.cartas.apilar(cartaASumar);
    	}
    }

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return una cadena mostrando todas las cartas del mazo
	 */
	@Override
    public String toString() {
    	return "El mazo posee las siguientes cartas: " + cartas;
    }

	/**
	 * pre: --
	 * @return el hashCode del mazo
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    /**
     * pre: --
     * pos: le da al un valor aleatorio entre VALOR_MINIMO y VALOR_MAXIMO
     */
    public int elegirIdParaTitulo() {
    	return random.nextInt(VALOR_MINIMO, VALOR_MAXIMO + 1);
    }
    
    /**
     * 
     * @return cartaActual: de tipo carta, elegida aleatoriamente para sumar al mazo
     */
    public Carta elegirTituloPorId() {
    	Carta cartaActual = null;
    	switch(elegirIdParaTitulo()) {
    		case 1: 
    			cartaActual = new CartaCambiarColorFicha();
    			break;
    		case 2:
    			cartaActual = new CartaAnularCasillero();
    			break;
    		case 3:
    			cartaActual = new CartaBloquearFicha();
    			break;
    		case 4:
    			cartaActual = new CartaDobleTurno();
    			break;
    		case 5:
    			cartaActual = new CartaEliminarCartasDelJugador();
    			break;
    		case 6:
    			cartaActual = new CartaPerderTurno();
    			break;
    		case 7:
    			cartaActual = new CartaVolverJugadaAnterior(); 		
    			break;
    	}
		return cartaActual;
    }
    
    /**
     * pre:
     * @param cartasALevantar: numero entero de cartas a levantar por el jugador y el dado. No puede ser nulo
     * @param jugadorActual: jugador que levanta las cartas. No puede ser nulo
     * @throws Exception: si no hay suficientes cartas en el mazo, crear un nuevo mazo
     * pos: a la mano del jugador se agregan las cratas solicitadas
     */
    public void levantarCartas(int cartasALevantar, Jugador jugadorActual) throws Exception {
    	Validacion.validarSiEsNulo(jugadorActual, "Jugador");
		// TODO: si se repite mucho en el tp, chequear si un numero es menor a 1, agregarlo a Validacion
		if (cartasALevantar < 1) {
			throw new Exception("Las cartas a levantar no pueden ser menor a 1");
		}
    	if (cartasALevantar > cartas.contarElementos()) {
    		throw new Exception("No hay suficientes cartas para levantar");
    	}
    	for (int i = 0; i < cartasALevantar; i++) {
    		jugadorActual.tomarCartas(cartas.obtener());
    		cartas.desapilar();
        }
    }
    
    /**
     * pre: --
     * @param jugador no puede ser nulo
     * @throws Exception si el jugador no tiene cartas
     * post: devuelve todas las cartas de un jugador al mazo
     */
    public void devolverTodasLasCartas(Jugador jugador) throws Exception {
		Validacion.validarSiEsNulo(jugador, "Jugador");
    	if (jugador.getMano() == null) {
    		throw new Exception("El jugador no tiene cartas");
    	}
    	Lista<Carta> cartasJugador = jugador.getMano();
    	cartasJugador.iniciarCursor();
    	while(cartasJugador.avanzarCursor()) {
    		Carta cartaActual = cartasJugador.obtenerCursor();
    		this.cartas.apilar(cartaActual);
    	}
    	jugador.devolveCartas();
    }

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return la pila de cartas
     */
	public Pila<Carta> getCartas() {
		return cartas;
	}

	/**
	 * pre: --
	 * @return el maximo de cartas del mazo
	 */
	public int getMaximoDeCartasEnMazo() {
		return maximoDeCartasEnMazo;
	}

	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
