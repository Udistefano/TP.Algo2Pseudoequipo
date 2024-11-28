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

        this.tablero = new Tablero<Ficha>(tablero.getAncho(), tablero.getAlto(), tablero.getProfundidad());
        this.jugadores = new ListaSimple<Jugador>();
        this.mazo = new Mazo(mazo.getMaximoDeCartasEnMazo());
        
        for (int x = 1; x <= this.tablero.getAncho(); x++) {
            for (int y = 1; y <= this.tablero.getAlto(); y++) {
                for (int z = 1; z <= this.tablero.getProfundidad(); z++) {
                    Ficha ficha = tablero.obtener(x, y, z);
                    if (ficha != null) {
                        this.tablero.agregar(x, y, z, ficha);
                    }
                }
            }
        }

        for (int i = 1; i <= jugadores.getLongitud(); i++) {
            Jugador jugador = jugadores.obtener(i);
            this.jugadores.agregar(jugador);
        }

        // TODO: copiar mazo en estado de partida
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