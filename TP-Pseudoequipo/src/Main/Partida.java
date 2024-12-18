package Main;

import Cartas.Carta;
import Estructuras.ListaSimple;
import Estructuras.ListaSimpleCircular;

public class Partida {
    // ATRIBUTOS DE CLASE
    // --------------------------------------------------------------------------------------

    private static int CANTIDAD_MAXIMA_DE_JUGADORES = 7;
    private static int CANTIDAD_DE_FICHAS_NECESARIAS_PARA_GANAR = 3;

    // ATRIBUTOS
    // -----------------------------------------------------------------------------------------------

    private Tablero<Ficha> tablero = null;
    private ListaSimple<Jugador> jugadores = null;
    private Mazo mazo = null;
    private Dado dado = null;
    private ListaSimpleCircular<Turno> turnos = null;
    private EstadoDePartida estadoAnterior = null;

    // CONSTRUCTORES
    // -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param tablero   no puede ser nulo
     * @param jugadores no puede ser nulo
     * @param mazo      no puede ser nulo
     * @throws Exception si alguno de los parametros es nulo
     *                   post: inicializa la partida con el tablero, jugadores y
     *                   mazo pasados por parametro
     */
    public Partida(Tablero<Ficha> tablero, ListaSimple<Jugador> jugadores, Mazo mazo) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugadores, "Lista de Jugadores");
        ValidacionesUtiles.validarSiEsNulo(mazo, "Mazo");

        this.tablero = tablero;
        this.jugadores = jugadores;
        this.mazo = mazo;
        this.turnos = new ListaSimpleCircular<Turno>();
        this.jugadores.iniciarCursor();
        while (this.jugadores.avanzarCursor()) {
            this.turnos.agregar(new Turno(this.jugadores.obtenerCursor()));
        }
        this.dado = new Dado();
    }

    // METODOS DE CLASE
    // ----------------------------------------------------------------------------------------
    // METODOS GENERALES
    // ---------------------------------------------------------------------------------------
    // METODOS DE COMPORTAMIENTO
    // -------------------------------------------------------------------------------

    /**
     * pre: --
     * @throws Exception
     *                   post: inicia la partida, y retorna el ganador
     */
    public Jugador iniciar() throws Exception {
        boolean hayGanador = false;
        Jugador jugadorActual = null;

        UtilesVarios.mostrarTextoAlrededorDeLineas("Inicio de partida");
        this.turnos.iniciarCursor();
        while (!hayGanador) {
            this.turnos.avanzarCursor();
            jugadorActual = this.turnos.obtenerCursor().getJugador();

            Casillero<Ficha> casilleroDestino = jugarTurno(this.turnos.obtenerCursor());
            // Chequeamos que casilleroDestino no sea nulo, porque si el jugador perdio un
            // turno (tiene bloqueos) entonces devolvera un casillero nulo
            if (casilleroDestino != null) {
                hayGanador = verificarGanador(casilleroDestino);
            }
        }
        UtilesVarios.mostrarTextoAlrededorDeLineas("Fin de partida");

        return jugadorActual;
    }

    /**
     * pre:
     * @param turno no puede ser nulo
     *              post: juega el turno (si no tiene bloqueos), y retorna el
     *              casillero al que se movio o jugo una ficha
     * @throws Exception si turno es nulo
     */
    public Casillero<Ficha> jugarTurno(Turno turno) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(turno, "Turno");
        Casillero<Ficha> casilleroDestino = null;

        turno.agregarSubturno();
        if (!turno.estaBloqueado()) {
            while (turno.haySubturnos()) {
                turno.utilizarSubturno();
                Jugador jugador = turno.getJugador();

                UtilesVarios.mostrarTextoAlrededorDeLineas("Turno del jugador " + jugador);

                dado.tirarDado();
                System.out.println("\n" + jugador + " tira el dado! Dio el numero " + dado.getValor());
                System.out.println(jugador + " levanta " + dado.getValor() + " cartas del mazo");
                levantarCartas(jugador, dado.getValor());
                guardarEstadoDePartidaAnterior();

                if (!jugador.tieneTodasLasFichasEnElTablero()) {
                    casilleroDestino = jugadaInicial(this.tablero, jugador);
                } else if (!jugadorTieneAlgunaFichaParaMover(jugador)) {
                    System.out.println("\n" + jugador + " no tiene fichas en el tablero para mover");
                } else {
                    casilleroDestino = mover(this.tablero, jugador);
                }

                if (casilleroDestino != null) {
                    Bitmap.escribirFicha(casilleroDestino, jugador.getColor());
                }

                Carta cartaActual = Teclado.preguntarCarta(jugador);
                if (cartaActual != null) {
                    cartaActual.getJugada().jugar(this, turno);
                    // Chequeamos que el jugador tenga la carta antes de quitarsela de la mano, porque quiza elimino
                    // sus propias cartas
                    if (jugador.getMano().existe(cartaActual)) {
                        jugador.quitarCartaDeLaMano(cartaActual);
                    }
                    this.mazo.agregarCarta(cartaActual);
                }
            }
        }
        turno.terminarTurno();

        return casilleroDestino;
    }

    /**
     * pre:
     * @return verdadero si el jugador tiene alguna ficha para mover, falso si no
     * @throws Exception si el jugador es nulo
     */
    private boolean jugadorTieneAlgunaFichaParaMover(Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");

        for (int x = 1; x <= this.tablero.getAncho(); x++) {
            for (int y = 1; y <= this.tablero.getAlto(); y++) {
                for (int z = 1; z <= this.tablero.getProfundidad(); z++) {
                    Ficha ficha = this.tablero.obtener(x, y, z);
                    if ((ficha != null) &&
                            (ficha.getColor().equals(jugador.getColor())) &&
                            (!ficha.estaBloqueada())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * pre:
     * @param cantidadDeCartasALevantar no puede ser menor a 1
     * @throws Exception si el jugador es nulo, o la cantidad de cartas a levantar
     *                   es menor a 1
     *                   post: levanta la cantidad de cartas del mazo pasadas por
     *                   parametro, y las agrega a la mano del jugador
     */
    private void levantarCartas(Jugador jugador, int cantidadDeCartasALevantar) throws Exception {
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(cantidadDeCartasALevantar, "Cantidad de cartas a levantar");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");

        try {
            int cantidadDeCartasLevantadas = mazo.levantarCartas(cantidadDeCartasALevantar, jugador);
            if (cantidadDeCartasLevantadas < cantidadDeCartasALevantar) {
                System.out.println(
                        "\n" + jugador + " solo pudo levantar " + cantidadDeCartasLevantadas + " cartas del mazo");
            }
        } catch (Exception e) {
            UtilesVarios.mostrarError(e);
        }
    }

    /**
     * pre: --
     * @throws Exception si no se pudo guardar el estado de la partida
     * post: guarda el estado de la partida actual
     */
    public void guardarEstadoDePartidaAnterior() throws Exception {
        this.estadoAnterior = new EstadoDePartida(this.tablero, this.jugadores, this.mazo);
    }

    /**
     * pre: --
     * @throws Exception si el estado anterior es nulo
     * post: restaura el estado de la partida anterior
     */
    public void volverEstadoDePartidaAnterior() throws Exception {
        if (this.estadoAnterior == null) {
            throw new Exception("No se ha guardado ningun estado de la partida anterior");
        }
        this.tablero = this.estadoAnterior.getTablero();
        this.jugadores = this.estadoAnterior.getJugadores();
        this.mazo = this.estadoAnterior.getMazo();
    }

    /**
     * pre:
     * @param jugador no puede ser nulo
     * @throws Exception si el tablero o jugador son nulos, o el casillero que el
     *                   jugador eligio esta ocupado
     *                   post: juega la jugada inicial, le pregunta un casillero, y
     *                   mueve una ficha del jugador ahi
     */
    public Casillero<Ficha> jugadaInicial(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
        if (jugador.tieneTodasLasFichasEnElTablero()) { // Validacion quiza redundante, pero por si acaso
            throw new Exception("Al jugador no le quedan mas fichas para jugar");
        }

        System.out.println(jugador + " tiene " + jugador.getCantidadDeFichasRestantes() + " fichas");
        System.out.println(jugador + " tendra que jugar una ficha");

        Ficha ficha = new Ficha(jugador.getColor());
        Casillero<Ficha> casillero = null;
        boolean esCasilleroInvalido = true;
        do {
            try {
                System.out.println("\nIngrese las coordenadas del casillero donde jugar una ficha:");
                casillero = Teclado.preguntarCasillero(tablero);
                ValidacionesUtiles.validarSiCasilleroEstaLibre(casillero, tablero);
                esCasilleroInvalido = false;
            } catch (Exception e) {
                UtilesVarios.mostrarError(e);
            }
        } while (esCasilleroInvalido);

        casillero.setDato(ficha);
        jugador.jugarFicha();
        System.out.println("\nSe ha jugado la ficha del jugador " + jugador + " en el " + casillero);

        return casillero;
    }

    /**
     * pre:
     * @param jugador no puede ser nulo
     * @return mueve la ficha hacia el casillero en la direccion 'movimiento'
     *         ingresados por el jugador en el tablero,
     *         y retorna el casillero donde se movio la ficha
     * @throws Exception si el tablero o jugador son nulos, o si el casillero en la
     *                   direccion 'movimiento' no existe,
     *                   o esta ocupado
     */
    public Casillero<Ficha> mover(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");

        System.out.println(jugador + " no le quedan mas fichas para jugar");
        System.out.println(jugador + " tendra que mover una ficha del tablero");

        Casillero<Ficha> casillero = null;
        Movimiento movimiento = null;
        boolean esCasilleroInvalido = true;
        do {
            try {
                System.out.println("\nIngrese las coordenadas del casillero del cual mover su ficha");

                casillero = Teclado.preguntarCasillero(tablero);
                ValidacionesUtiles.validarSiCasilleroEstaOcupado(casillero, tablero);
                ValidacionesUtiles.validarSiFichaEstaBloqueada(casillero.getDato());
                ValidacionesUtiles.validarFichaEsPropia(casillero.getDato(), jugador);

                movimiento = Teclado.preguntarMovimiento(casillero);
                esCasilleroInvalido = false;
            } catch (Exception e) {
                UtilesVarios.mostrarError(e);
            }
        } while (esCasilleroInvalido);

        tablero.mover(casillero, casillero.getCasilleroVecino(movimiento), casillero.getDato());
        Bitmap.quitarFicha(casillero);
        System.out.println("\nLa ficha en el casillero " + casillero + " del jugador " + jugador +
                " se ha movido en direccion " + movimiento);

        return casillero.getCasilleroVecino(movimiento);
    }

    /**
     * pre:
     * @return si hubo algun ganador respecto al casillero agregado
     * @throws Exception si casillero es nulo
     */
    public boolean verificarGanador(Casillero<Ficha> casillero) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");

        int cantidadDeFichas = getCantidadDeFichasNecesariasParaGanar();
        Direccion[][] paresDeDirecciones = {
                { Direccion.ATRAS_ARRIBA, Direccion.ATRAS_ABAJO },
                { Direccion.ATRAS_IZQUIERDA, Direccion.ATRAS_DERECHA },
                { Direccion.ATRAS_IZQUIERDA_ARRIBA, Direccion.ATRAS_DERECHA_ABAJO },
                { Direccion.ATRAS_IZQUIERDA_ABAJO, Direccion.ATRAS_DERECHA_ARRIBA },
                { Direccion.CENTRO_ARRIBA, Direccion.CENTRO_ABAJO },
                { Direccion.CENTRO_IZQUIERDA, Direccion.CENTRO_DERECHA },
                { Direccion.CENTRO_IZQUIERDA_ARRIBA, Direccion.CENTRO_DERECHA_ABAJO },
                { Direccion.CENTRO_IZQUIERDA_ABAJO, Direccion.CENTRO_DERECHA_ARRIBA },
                { Direccion.ATRAS_CENTRO, Direccion.ADELANTE_CENTRO },
                { Direccion.ADELANTE_ARRIBA, Direccion.ADELANTE_ABAJO },
                { Direccion.ADELANTE_IZQUIERDA, Direccion.ADELANTE_DERECHA },
                { Direccion.ADELANTE_IZQUIERDA_ARRIBA, Direccion.ADELANTE_DERECHA_ABAJO },
                { Direccion.ADELANTE_IZQUIERDA_ABAJO, Direccion.ADELANTE_DERECHA_ARRIBA }
        };

        for (Direccion[] par : paresDeDirecciones) {
            int cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, par[0], par[1]);
            if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
                return true;
            }
        }

        return false;
    }

    /**
     * pre:
     * @param direccion          no puede ser nulo
     * @param direccionContraria no puede ser nulo
     * @return la cantidad de fichas seguidas del valor de la ficha, en la direccion
     *         respecto del casillero pasados por
     *         parametro, y la direccion contraria
     * @throws Exception si alguno de los parametros es nulo
     */
    public int contarFichasSeguidasEnDireccion(Casillero<Ficha> casillero, Direccion direccion,
            Direccion direccionContraria) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(direccion, "Direccion");
        ValidacionesUtiles.validarSiEsNulo(direccionContraria, "Direccion");

        return this.contarFichasSeguidas(casillero, direccion, casillero.getDato()) +
                this.contarFichasSeguidas(casillero, direccionContraria, casillero.getDato()) - 1;
    }

    /**
     * pre:
     * @param direccion no puede ser nulo
     * @param ficha     no puede ser nulo
     * @return la cantidad de fichas seguidas del valor de la ficha, en la direccion
     *         respecto del casillero pasados por
     *         parametro
     * @throws Exception si alguno de los parametros es nulo
     */
    public int contarFichasSeguidas(Casillero<Ficha> casillero, Direccion direccion, Ficha ficha) throws Exception {
        if ((casillero == null) ||
                (casillero.getDato() == null)) {
            return 0;
        }
        ValidacionesUtiles.validarSiEsNulo(direccion, "Direccion");
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");

        if (!casillero.getDato().equals(ficha)) {
            return 0;
        }
        return 1 + contarFichasSeguidas(casillero.getCasilleroVecino(direccion), direccion, ficha);
    }

    // GETTERS SIMPLES
    // -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return los turnos de la partida
     */
    public ListaSimpleCircular<Turno> getTurnos() throws Exception {
        ListaSimpleCircular<Turno> copiaDeTurnos = new ListaSimpleCircular<Turno>();

        this.turnos.iniciarCursor();
        this.turnos.avanzarCursor();
        Turno primerTurno = this.turnos.obtenerCursor();
        do {
            copiaDeTurnos.agregar(this.turnos.obtenerCursor());
            this.turnos.avanzarCursor();
        } while (!this.turnos.obtenerCursor().equals(primerTurno));

        return copiaDeTurnos;
    }

    /**
     * pre: --
     * @return el tablero de la partida
     */
    public Tablero<Ficha> getTablero() {
        Tablero<Ficha> copiaDeTablero = this.tablero;
        return copiaDeTablero;
    }

    /**
     * pre: --
     * @return una copia de los jugadores de la partida
     */
    public ListaSimple<Jugador> getJugadores() throws Exception {
        ListaSimple<Jugador> copiaDeJugadores = new ListaSimple<Jugador>();

        jugadores.iniciarCursor();
        while (jugadores.avanzarCursor()) {
            copiaDeJugadores.agregar(jugadores.obtenerCursor());
        }

        return copiaDeJugadores;
    }

    /**
     * pre: --
     * @return el mazo de la partida
     */
    public Mazo getMazo() {
        Mazo copiaDeMazo = this.mazo;
        return copiaDeMazo;
    }

    /**
     * pre: --
     * @return la cantidad maxima permitida de jugadores que puede tener una partida
     */
    public static int getCantidadMaximaDeJugadores() {
        return CANTIDAD_MAXIMA_DE_JUGADORES;
    }

    /**
     * pre: --
     * @return la cantidad necesaria de fichas en raya para ganar una partida
     */
    public static int getCantidadDeFichasNecesariasParaGanar() {
        return CANTIDAD_DE_FICHAS_NECESARIAS_PARA_GANAR;
    }

    // SETTERS SIMPLES
    // -----------------------------------------------------------------------------------------
}
