package Main;

import Cartas.Carta;
import Estructuras.Lista;
import Estructuras.Vector;

public class Partida {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Tablero<Ficha> tablero = null;
    private Lista<Jugador> jugadores = null;
    private Mazo mazo = null;
    private Dado dado = null;
    private Vector<Turno> turnos = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugadores no puede ser nulo
     * @param mazo no puede ser nulo
     * @throws Exception si alguno de los parametros es nulo
     * post: inicializa la partida con el tablero, jugadores y mazo pasados por parametro
     */
    public Partida(Tablero<Ficha> tablero, Lista<Jugador> jugadores, Mazo mazo) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugadores, "Lista de Jugadores");
        ValidacionesUtiles.validarSiEsNulo(mazo, "Mazo");

        this.tablero = tablero;
        Bitmap.inicializar(tablero.getAncho(), tablero.getAlto(), tablero.getProfundidad());
        Bitmap.crearImagen();
        this.jugadores = jugadores;
        this.mazo = mazo;
//        this.turnos = new Lista<Turno>();
        // TODO: implementar ListaCircular , y hacer turnos una ListaCircular
        this.turnos = new Vector<Turno>(jugadores.getLongitud(), null);
        for (int i = 1; i <= jugadores.getLongitud(); i++) {
            Turno turno = new Turno(jugadores.obtener(i));
            this.turnos.agregar(i, turno);
        }
        this.dado = new Dado();
        Bitmap.escribirArchivo();
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * @throws Exception
     * post: inicia la partida, y retorna el ganador
     */
    public Jugador iniciar() throws Exception {
    	boolean hayGanador = false;
        int posicion = 1;
        Jugador jugadorActual = null;

        UtilesVarios.mostrarTextoAlrededorDeLineas("Inicio de partida");
        while(!hayGanador) {
            if (posicion > turnos.getLongitud()) {
                posicion = 1;
            }

            Turno turnoActual = turnos.obtener(posicion);
            jugadorActual = turnoActual.getJugador();

            Casillero<Ficha> casilleroDestino = jugarTurno(turnoActual);
            // Chequeamos que casilleroDestino no sea nulo, porque si el jugador perdio un turno (tiene bloqueos)
            // entonces devolvera un casillero nulo
            if (casilleroDestino != null) {
                hayGanador = verificarGanador(casilleroDestino);
            }
            posicion++;
        }
        UtilesVarios.mostrarTextoAlrededorDeLineas("Fin de partida");

        return jugadorActual;
    }


    /**
     * pre:
     * @param turno no puede ser nulo
     * post: juega el turno (si no tiene bloqueos), y retorna el casillero al que se movio o jugo una ficha
     * @throws Exception si turno es nulo
     */
    public Casillero<Ficha> jugarTurno(Turno turno) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(turno, "Turno");
        Casillero<Ficha> casilleroDestino = null;

        turno.iniciarTurno();
        if (!turno.estaBloqueado()) {
            while (turno.haySubturnos()) {
                turno.utilizarSubturno();
                Jugador jugador = turno.getJugador();
                
                UtilesVarios.mostrarTextoAlrededorDeLineas("Turno del jugador " + jugador);
                
                dado.tirarDado();
                System.out.println("\n" + jugador + " tira el dado! Dio el numero " + dado.getValor());
                System.out.println(jugador + " levanta " + dado.getValor() + " cartas del mazo");
                mazo.levantarCartas(dado.getValor(), jugador);

                if (!jugador.tieneTodasLasFichasEnElTablero()) {
                    casilleroDestino = jugadaInicial(this.tablero, jugador);
                } else {
                    casilleroDestino = mover(this.tablero, jugador);
                }
                Bitmap.escribirFicha(casilleroDestino, jugador.getColor());

                Carta cartaActual = Teclado.preguntarCarta(jugador.getMano());
                if (cartaActual != null) {
                    cartaActual.getJugada().jugar(this, turno);
                    jugador.quitarCartaDeLaMano(cartaActual);
                    this.mazo.agregarCarta(cartaActual);
                }
            }
        }
        turno.terminarTurno();

        return casilleroDestino;
    }

    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugador no puede ser nulo
     * @throws Exception si el tablero o jugador son nulos, o el casillero que el jugador eligio esta ocupado
     * post: juega la jugada inicial, le pregunta un casillero, y mueve una ficha del jugador ahi
     */
    public Casillero<Ficha> jugadaInicial(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
        if (jugador.tieneTodasLasFichasEnElTablero()) { // Validacion quiza redundante, pero por si acaso
            throw new Exception("Al jugador no le quedan mas fichas para jugar");
        }

        System.out.println(jugador + " tiene " + jugador.getCantidadDeFichasRestantes() + " fichas");
        
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
				System.out.println("\nError: " + e.getMessage());
            }
        } while (esCasilleroInvalido);

        casillero.setDato(ficha);
        jugador.jugarFicha();

        return casillero;
    }

    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugador no puede ser nulo
     * @return mueve la ficha hacia el casillero en la direccion 'movimiento' ingresados por el jugador en el tablero,
     * y retorna el casillero donde se movio la ficha
     * @throws Exception si el tablero o jugador son nulos, o si el casillero en la direccion 'movimiento' no existe,
     *                   o esta ocupado
     */
    public Casillero<Ficha> mover(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(tablero, "Tablero");
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");

        System.out.print(jugador + " no le quedan mas fichas para jugar");
        System.out.println("Tendra que mover una ficha del tablero");

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
				System.out.println("\nError: " + e.getMessage());
            }
        } while (esCasilleroInvalido);

        tablero.mover(casillero, casillero.getCasilleroVecino(movimiento), casillero.getDato());

        Bitmap.escribirFicha(casillero.getCasilleroVecino(movimiento), jugador.getColor());
        Bitmap.quitarFicha(casillero);
        System.out.println("\nLa ficha en el casillero " + casillero + " del jugador " + jugador +
                           " se ha movido en direccion " + movimiento);

        return casillero.getCasilleroVecino(movimiento);
    }

    public boolean verificarGanador(Casillero<Ficha> casillero) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        // FIXME: habria que agregar atributo cantidadDeFichasNecesariasParaGanar a Partida, siendo inicialmente
        //        3??? o permitirle pasarle por parametro, y validar que haya almenos una dimension alto o ancho o
        //        profundidad que sea de ese tamaño
        int cantidadDeFichas = 3;

        // TODO: esto seguramente contar las fichas seguidas en todas las direcciones, seguramente se pueda mejorar
        int cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_ARRIBA,
                Direccion.ATRAS_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA,
                Direccion.ATRAS_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA_ARRIBA,
                Direccion.ATRAS_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA_ABAJO,
                Direccion.ATRAS_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        // Plano del centro
        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_ARRIBA,
                Direccion.CENTRO_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA,
                Direccion.CENTRO_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA_ARRIBA,
                Direccion.CENTRO_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_CENTRO,
                Direccion.ADELANTE_CENTRO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA_ABAJO,
                Direccion.CENTRO_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        // Plano de adelante
        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_ARRIBA,
                Direccion.ADELANTE_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA,
                Direccion.ADELANTE_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA_ARRIBA,
                Direccion.ADELANTE_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        cantidadDeFichasSeguidas = contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA_ABAJO,
                Direccion.ADELANTE_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true;
        }

        return false;
    }

    /**
     * pre:
     * @param casillero          no puede ser nulo
     * @param direccion          no puede ser nulo
     * @param direccionContraria no puede ser nulo
     * @return la cantidad de fichas seguidas del valor de la ficha, en la direccion respecto del casillero pasados por
     * parametro, y la direccion contraria
     * @throws Exception si alguno de los parametros es nulo
     */
    public int contarFichasSeguidasEnDireccion(Casillero<Ficha> casillero, Direccion direccion, Direccion direccionContraria) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(casillero, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(direccion, "Direccion");
        ValidacionesUtiles.validarSiEsNulo(direccionContraria, "Direccion");

        // FIXME: esto no anda, porque siempre es 2 como minimo, porque sumamos adentro de contarFichasSEguidasd
        //        es un poco hardcode pero si le restamos 1 anda
        return this.contarFichasSeguidas(casillero, direccion, casillero.getDato()) +
                this.contarFichasSeguidas(casillero, direccionContraria, casillero.getDato()) - 1;
    }

    /**
     * pre:
     *
     * @param casillero no puede ser nulo
     * @param direccion no puede ser nulo
     * @param ficha     no puede ser nulo
     * @return la cantidad de fichas seguidas del valor de la ficha, en la direccion respecto del casillero pasados por
     * parametro
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

    /**
     * pre:
     * @param jugador no puede ser nulo
     * @return el turno siguiente de jugador pasado por parametro
     * @throws Exception si el jugador es nulo
     */
    public Turno getTurnoSiguiente(Jugador jugador) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");

        // TODO: getTurnoSiguiente se puede remplazar con el equals de Turno
        for (int i = 1; i <= turnos.getLongitud(); i++) {
            if (turnos.obtener(i).getJugador().equals(jugador)) {
                return turnos.obtener(i);
            }
        }
//        turnos.iniciarCursor();
//        while (turnos.avanzarCursor()) {
//            if (turnos.obtenerCursor().getJugador().equals(jugador)) {
//                return turnos.obtenerCursor();
//            }
//        }
        throw new Exception("No se encontro el turno siguiente del jugador " + jugador.getNombre());
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el tablero de la partida
     */
    public Tablero<Ficha> getTablero() {
        return tablero;
    }

    /**
     * pre: --
     * @return una copia de los jugadores de la partida
     */
    public Lista<Jugador> getJugadores() throws Exception {
        Lista<Jugador> copiaDeJugadores = new Lista<Jugador>();

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
        return mazo;
    }
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
