package Main;

import Estructuras.ListaSimple;

public class Main {
    public static void main(String[] args) throws Exception {
		Teclado.inicializar();
		System.out.println("Bienvenidos al TaTeTi 3D!");

		int ancho = Teclado.preguntarCoordenadaTablero("\nIngrese el ancho del tablero: ");
		int alto = Teclado.preguntarCoordenadaTablero("\nIngrese el alto del tablero: ");
		int profundidad = Teclado.preguntarCoordenadaTablero("\nIngrese la profundidad del tablero: ");

		// TODO: habria que preguntarle al usuario estas cantidades o hardcodearlo?
		int cantidadDeCartas = 50;
		int cantidadDeFichasMaximasPermitidas = 3;
		Tablero<Ficha> tablero = new Tablero<Ficha>(ancho, alto, profundidad);
		ListaSimple<Jugador> jugadores = new ListaSimple<Jugador>();
		Mazo mazo = new Mazo(cantidadDeCartas);
        Bitmap.inicializar(ancho, alto, profundidad);
		
		int cantidadDeJugadores = Teclado.preguntarCantidadJugadores("\nCuantos jugadores seran en este juego?: ");
		
		for (int i = 1; i <= cantidadDeJugadores; i++) {
			System.out.println("\nJugador #" + i + ":");
			String nombre = Teclado.preguntarNombreDeJugador("\nIngrese su nombre: ");
			int numeroDeColor = Teclado.preguntarColor(jugadores);

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
