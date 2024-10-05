package tp2prueba;

public class Juego {
	private Tablero tablero = null;
	private Lista<Jugador> jugadores  = null;
	private Mazo mazo = null;

    public Juego(Tablero tablero, Lista<Jugador> jugadores, Mazo mazo) throws Exception {
        ValidacionUtiles.validarSiEsNulo(tablero);
        ValidacionUtiles.validarSiEsNulo(jugadores);
        ValidacionUtiles.validarSiEsNulo(mazo);

        this.tablero = tablero;
        this.jugadores = jugadores;
        this.mazo = mazo;
    }


}
