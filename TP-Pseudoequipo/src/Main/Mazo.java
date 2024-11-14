package Main;

import Estructuras.Cola;
import Estructuras.Pila;

import java.util.Objects;
import Cartas.Carta;
import Cartas.CartaAnularCasillero;
import Cartas.CartaBloquearFicha;
import Cartas.CartaCambiarColorFicha;
import Cartas.CartaDobleTurno;
import Cartas.CartaEliminarCartasDelJugador;
import Cartas.CartaPierdeTurno;
import Cartas.CartaVolverJugadaAnterior;

import java.util.Random;


@SuppressWarnings("rawtypes")
public class Mazo {
	
    private static int VALOR_MINIMO = 1;
    private static int VALOR_MAXIMO = 7;
	public Pila <Carta> cartas = null;
    private int maximoDeCartasEnMazo;
    private Random random;
    
    //CONSTRUCTOR
    /**
     * pre: --
     * pos: crea un mazo de cartas con 50 cartas de todos los tipos. No hay un maximo de cartas por tipo
     */
    public Mazo(int maximoCartas) {
    	this.maximoDeCartasEnMazo = maximoCartas;
    	this.cartas = new Pila<Carta>();
    	this.random = new Random();
    	Carta cartaASumar;
    	for (int i = 0; i < maximoDeCartasEnMazo ; i++) {
    		cartaASumar = elegirTituloPorId();
			cartas.apilar(cartaASumar);
    	}
    }
    
    //METODOS GENERALES
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
    
    @Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
    
    
    // METODOS DE COMPORTAMIENTO
    
    /**
     * pre: --
     * pos: le da al  un valor entre VALOR_MINIMO y VALOR_MAXIMO
     */
    public int elegirIdParaTitulo() {
    	int numero = random.nextInt(VALOR_MINIMO, VALOR_MAXIMO + 1);
    	return numero;
    }
    
    
    /**
     * 
     * @return cartaActual: de tipo carta, elegida aleatoriamente para sumar al mazo
     */
    public Carta elegirTituloPorId() {
    	int numero = elegirIdParaTitulo();
    	Carta cartaActual = null;
    	switch(numero) {
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
    			cartaActual = new CartaPierdeTurno();
    			break;
    		case 7:
    			cartaActual = new CartaVolverJugadaAnterior(); 		
    			break;
    	}
		return cartaActual;
    }
    
    /**
     * 
     * @param cartasALevantar: numero entero de cartas a levantar por el jugador y el dado. No puede ser nulo
     * @param jugadorActual: jugador que levanta las cartas. No puede ser nulo
     * @throws Exception: si no hay suficientes cartas en el mazo, crear un nuevo mazo
     * pos: a la mano del jugador se agregan las cratas solicitadas
     */
    public void levantarCartas(int cartasALevantar, Jugador jugadorActual) throws Exception {
    	Validacion.validarSiEsNulo(jugadorActual, "Jugador");
    	if (cartasALevantar > cartas.contarElementos()) {
    		throw new Exception ("No hay suficientes cartas para levantar");
    	}
    	for (int i = 0; i < cartasALevantar; i++) {
    		jugadorActual.tomarCartas(cartas.obtener());
    		cartas.desapilar();
        }
    }
    
    
    // GETTERS

    /**
     * pre: --
     * @return la cola de cartas
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
    
    
    
}