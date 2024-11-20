package Main;

import Estructuras.Lista;

public class Main {
    public static void main(String[] args) throws Exception {
		Teclado.inicializar();
		System.out.println("Bienvenidos al TaTeTi 3D!\n");

		int ancho = Teclado.preguntarCoordenadaTablero("Ingrese el ancho del tablero: ");

		int alto = Teclado.preguntarCoordenadaTablero("Ingrese el alto del tablero: ");

		int profundidad = Teclado.preguntarCoordenadaTablero("Ingrese la profundidad del tablero: ");

		// TODO: habria que preguntarle al usuario estas cantidades o hardcodearlo?
		int cantidadDeCartas = 50;
		int cantidadDeFichasMaximasPermitidas = 3;
		Tablero<Ficha> tablero = new Tablero<Ficha>(ancho, alto, profundidad);
		Lista<Jugador> jugadores = new Lista<Jugador>();
		Mazo mazo = new Mazo(cantidadDeCartas);
		
		System.out.print("\nCuantos jugadores seran en este juego?: ");
		int cantidadDeJugadores = Teclado.leerNumeroNatural();
		if ((cantidadDeJugadores > 7) || (cantidadDeJugadores <= 1)) {
			throw new Exception("El juego es de a 2 hasta 7 jugadores");
		}

		for (int i = 1; i <= cantidadDeJugadores; i++) {
			System.out.println("\nJugador #" + i + ":");
			System.out.print("\nIngrese su nombre: ");
			String nombre = Teclado.leerCadenaNoVacia();
			// TODO: validar que no exista un jugador con ese color ya
			int numeroDeColor = Teclado.preguntarColor();
			ValidacionesUtiles.validarSiEsUnico(numeroDeColor, jugadores);

			Jugador jugador = new Jugador(nombre, numeroDeColor, cantidadDeFichasMaximasPermitidas);
			jugadores.agregar(jugador);
		}

		Partida partida = new Partida(tablero, jugadores, mazo);
		Jugador ganador = partida.iniciar();

		if (ganador != null) {
			System.out.println("\nEl ganador del TaTeTi 3D fue " + ganador + "!!");
		} else {
			System.out.println("\nHubo un empate en el TaTeTi 3D!");
		}
		Teclado.finalizar();
    }
}
