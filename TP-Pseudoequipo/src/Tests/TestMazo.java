package Tests;

import Main.Mazo;

public class TestMazo {
	public static void main(String[] args) throws Exception {
		Mazo baraja = new Mazo(5);

		System.out.println(baraja.getCartas());
		System.out.println(baraja.getMaximoDeCartasEnMazo());
		
	}
}
