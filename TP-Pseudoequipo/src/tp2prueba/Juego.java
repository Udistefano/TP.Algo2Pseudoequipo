package tp2prueba;

public class Juego {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Tablero tablero = null;
    private Lista<Jugador> jugadores  = null;
    private Mazo mazo = null;
    private Dado dado = null;


    /**
     * pre: -
     * post: inicia juego y lo termina cuando un jugador lo gano
     */
    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Juego(Tablero tablero, Lista<Jugador> jugadores, Mazo mazo,Dado dado) throws Exception {
        ValidacionUtiles.validarSiEsNulo(tablero);
        ValidacionUtiles.validarSiEsNulo(jugadores);
        ValidacionUtiles.validarSiEsNulo(mazo);

        this.dado = dado;
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
    public void iniciarJuego() throws Exception {
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
    public boolean verificarGanador(Jugador jugador) throws Exception {
        // TODO: implementar Juego.verificarGanador
        return true;
    }

    public void jugarTurno(Jugador jugador) throws Exception {
        // TODO: implementar Juego.jugarTurno
        this.dado.jugarDado();
        jugador.tomarCartas( dado.getValor(), this.mazo);
        //poner o mover ficha
            //quedan fichas?
                //si->seleccionar celda valida
                //no->
        //quiere jugar carta
            //si->mostrar cartas->seleccionar carta->realizar accion
            //no->salir 
        //termina turno
    }
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
