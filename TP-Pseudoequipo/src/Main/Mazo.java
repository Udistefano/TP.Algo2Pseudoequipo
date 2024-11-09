package Main;

import Estructuras.Cola;

public class Mazo {
    Cola cartas;

    public void levantarCartas(int cartasALevantar, Jugador jugadorActual) {
        for (int i = 0; i < cartasALevantar; i++) {
            levantarCarta(jugadorActual);
        }
    }
}
