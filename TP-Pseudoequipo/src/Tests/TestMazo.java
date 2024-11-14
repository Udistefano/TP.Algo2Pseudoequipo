package Tests;

import Main.Jugador;
import Main.Mazo;

public class TestMazo {
	public static void main(String[] args) throws Exception {
		Mazo baraja = new Mazo(5);
		Mazo baraja2 = new Mazo(45);
		Jugador messi = new Jugador("Lionel MEssi", 'm', "albiceleste", 10);
		System.out.println(baraja.getCartas());
		System.out.println(baraja2.getCartas());
		System.out.println(baraja.getMaximoDeCartasEnMazo());
		System.out.println("Mano de cartas de messi: " + messi.getMano());
		baraja.levantarCartas(2, messi);
		System.out.println("Mano de cartas de messi: " + messi.getMano());
		System.out.println(baraja.getCartas());
		baraja.levantarCartas(3, messi);
		System.out.println("Mano de cartas de messi: " + messi.getMano());
		System.out.println(baraja.getCartas());
	}
}
