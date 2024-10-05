package tp2prueba;

public class Juego {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Tablero tablero = null;
    private Lista<Jugador> jugadores  = null;
    private Mazo mazo = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Juego(Tablero tablero, Lista<Jugador> jugadores, Mazo mazo) throws Exception {
        ValidacionUtiles.validarSiEsNulo(tablero);
        ValidacionUtiles.validarSiEsNulo(jugadores);
        ValidacionUtiles.validarSiEsNulo(mazo);

        this.tablero = tablero;
        this.jugadores = jugadores;
        this.mazo = mazo;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    /**
     * pre: -
     * post: inicia juego y lo termina cuando un jugador lo gano
     */
    public void iniciarJuego() {
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            this.jugadores.avanzarCursor();
            this.jugarTurno(this.jugadores.obtenerCursor());

            if (this.verificarGanador(this.jugadores.obtenerCursor())) {
                juegoTerminado = true;
            }
        }
    }

    /**
     * pre:
     * @param jugador
     * @return un booleano indicando si el jugador gano el juego
     */
    public boolean verificarGanador(Jugador jugador) {
        // TODO: implementar Juego.verificarGanador
        return true;
    }

    public void jugarTurno(Jugador jugador) {
        // TODO: implementar Juego.jugarTurno
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
