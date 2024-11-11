package Main;

import Estructuras.Cola;
import java.util.Objects;
import Cartas.Carta;

@SuppressWarnings("rawtypes")
public class Mazo {
	Cola <Carta> cartas = null;
    int maximoDeCartasEnMazo = 50;
    
    /**
     * pre: --
     * pos: crea un mazo de cartas con 50 cartas de todos los tipos. No hay un maximo de cartas por tipo
     */
    public void crearMazo () {
    	for(int i = 0; i < (maximoDeCartasEnMazo - cartas.contarElementos()) ; i++) {
    		cartas.acolar(carta);
    	}
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
}
