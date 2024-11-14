package Main;

import Cartas.CartaBloquearFicha;
import Estructuras.Lista;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
//    	Tablero tablero = new Tablero(3,3,3);
//    	Jugador jugador1 = new Jugador("Roberto", 'x', "verde", 3);
//    	Jugador jugador2 = new Jugador("Luz", 'o', "azul", 3);
//    	Lista<Jugador> jugadores = new Lista<Jugador>();
//    	jugadores.agregar(jugador1);
//    	jugadores.agregar(jugador2);
//    	Mazo mazo = new Mazo(50);
//    	Partida partida = new Partida(tablero, jugadores, mazo);

		Teclado.inicializar();
		System.out.println("Bienvenidos al TaTeTi 3D");

		System.out.println("Ingrese el ancho del tablero:");
		int ancho = Teclado.leerNumeroNatural();

		System.out.println("Ingrese el alto del tablero:");
		int alto = Teclado.leerNumeroNatural();

		System.out.println("Ingrese la profundidad del tablero:");
		int profundidad = Teclado.leerNumeroNatural();

		// TODO: habria que preguntarle al usuario estas cantidades o hardcodearlo?
		int cantidadDeCartas = 50;
		int cantidadDeFichasMaximasPermitidas = 3;
		Tablero<Ficha> tablero = new Tablero<Ficha>(ancho, alto, profundidad);
		Lista<Jugador> jugadores = new Lista<Jugador>();
		Mazo mazo = new Mazo(cantidadDeCartas);

		System.out.println("Cuantos jugadores seran en este juego?: ");
		int cantidadDeJugadores = Teclado.leerNumeroNatural();

		for (int i = 1; i <= cantidadDeJugadores; i++) {
			System.out.println("Ingrese el nombre del jugador #" + i + ": ");
			String nombre = Teclado.leerCadenaNoVacia();

			System.out.println("Ingrese el simbolo del jugador #" + i + ": ");
			Character simbolo = Teclado.leerCaracter();

			System.out.println("Ingrese el color del jugador #" + i + ": ");
			String color = Teclado.leerCadenaNoVacia();
			// FIXME: esto de color hay que hacerlo funcionar!

			Jugador jugador = new Jugador(nombre, simbolo, color, cantidadDeFichasMaximasPermitidas);
			jugadores.agregar(jugador);
		}

		Partida partida = new Partida(tablero, jugadores, mazo);
		partida.iniciar();
    }
}
