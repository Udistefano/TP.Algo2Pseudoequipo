package Main;

import Cartas.CartaBloquearFicha;
import Estructuras.Lista;

public class Main {
    public static void main(String[] args) throws Exception {

    	Tablero tablero = new Tablero(3,3,3);
    	Jugador jugador1 = new Jugador("Roberto", 'x', "verde", 3);
    	Jugador jugador2 = new Jugador("Luz", 'o', "azul", 3);
    	Lista<Jugador> jugadores = new Lista<Jugador>();
    	jugadores.agregar(jugador1);
    	jugadores.agregar(jugador2);
    	Mazo mazo = new Mazo(50);
    	Partida partida = new Partida(tablero, jugadores, mazo);
    	
    	
    	
    }
}
