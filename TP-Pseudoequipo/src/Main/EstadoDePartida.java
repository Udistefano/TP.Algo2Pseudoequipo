package Main;

import Estructuras.ListaSimple;

public class EstadoDePartida {
    private Tablero<Ficha> tablero;
    private ListaSimple<Jugador> jugadores;
    private Mazo mazo;

    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugadores no puede ser nulo
     * @param mazo no puede ser nulo
     * @throws Exception si alguno de los parametros es nulo
     * post: crea un estado de partida con los parametros dados
     */
    public EstadoDePartida(Tablero<Ficha> tablero, ListaSimple<Jugador> jugadores, Mazo mazo) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugadores, "Jugadores");
        ValidacionesUtiles.validarSiEsNulo(mazo, "Mazo");
        
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.mazo = mazo;
    }

    /**
     * pre: --
     * post: devuelve el tablero de la partida
     */ 
    public Tablero<Ficha> getTablero() {
        return this.tablero;
    }

    /**
     * pre: --
     * post: devuelve la lista de jugadores de la partida
     */
    public ListaSimple<Jugador> getJugadores() {
        return this.jugadores;
    }

    /**
     * pre: --
     * post: devuelve el mazo de la partida
     */
    public Mazo getMazo() {
        return this.mazo;
    }
}