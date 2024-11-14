package Main;

import Estructuras.Cola;
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
	public Cola <Carta> cartas = null;
    private int maximoDeCartasEnMazo = 50;
    private int valor = 0;
    private Random random = null;
    
    /**
     * pre: --
     * pos: crea un mazo de cartas con 50 cartas de todos los tipos. No hay un maximo de cartas por tipo
     */
    public void crearMazo () {
    	for (int i = 0; i < (maximoDeCartasEnMazo - cartas.contarElementos()) ; i++) {
    		Carta cartaASumar = elegirTituloPorId();
			cartas.acolar(cartaASumar);
    	}
    }
    
    /**
     * pre: --
     * pos: le da al  un valor entre VALOR_MINIMO y VALOR_MAXIMO
     */
    public void elegirIdParaTitutlo() {
    	valor = random.nextInt(VALOR_MINIMO, VALOR_MAXIMO + 1);
    }
    
    
    /**
     * 
     * @return cartaActual: de tipo carta, elegida aleatoriamente para sumar al mazo
     */
    public Carta elegirTituloPorId() {
    	elegirIdParaTitutlo();
    	valor = getValor();
    	Carta cartaActual = null;
    	switch(valor) {
    		case 1: 
    			cartaActual = new CartaCambiarColorFicha();
    		case 2:
    			cartaActual = new CartaAnularCasillero();
    		case 3:
    			cartaActual = new CartaBloquearFicha();
    		case 4:
    			cartaActual = new CartaDobleTurno();
    		case 5:
    			cartaActual = new CartaEliminarCartasDelJugador();
    		case 6:
    			cartaActual = new CartaPierdeTurno();
    		case 7:
    			cartaActual = new CartaVolverJugadaAnterior(); 		
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
    		cartas.desacolar();
        }
    }
    
    public int getValor() {
        return valor;
    }
    
}