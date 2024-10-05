package tp2prueba;

public class Juego {
	private Tablero tablero = null;
	private Lista<Jugador> jugadores  = null;
	private Mazo mazo = null;

    public Juego(Tablero tablero, Lista<Jugador> jugadores, Mazo mazo) throws Exception {
        if (tablero == null) {
            throw new Exception("El tablero no puede ser nulo");
        }
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.mazo = mazo;
    }
//public Juego(int[] dimensiones,int cantJugadores)throws Exception{
//	this.tablero = new Tablero(dimensiones);
//
//	for(int i = 0 ; i < cantJugadores; i++){
//		this.jugadores = new Lista(cantJugadores);
//		}
//	}
}
