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
    private Dado dado;
    // FIXME: en Partida, turnos deberia ser un Vector, una Lista, o una Pila ???????
//    private Lista<Turno> turnos = null;
    private Vector<Turno> turnos = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param tablero no puede ser nulo
     * @param jugadores no puede ser nulo
     * @param mazo no puede ser nulo
     * @throws Exception si alguno de los parametros es nulo
     * post: inicializa la partida con el tablero, jugadores y mazo pasados por parametro, e inicializa
     *       el teclado
     */
    public Partida(Tablero<Ficha> tablero, Lista<Jugador> jugadores, Mazo mazo) throws Exception {
        Validacion.validarSiEsNulo(tablero);
        Validacion.validarSiEsNulo(jugadores);
        Validacion.validarSiEsNulo(mazo);

        Teclado.inicializar();
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.mazo = mazo;
//        this.turnos = new Lista<Turno>();
        // TODO: implementar ListaCircular , y hacer turnos una ListaCircular
        this.turnos = new Vector<Turno>(jugadores.getLongitud(), null);
        for (int i = 0; i < jugadores.getLongitud(); i++) {
            Turno turno = new Turno(jugadores.obtener(i));
            this.turnos.agregar(i, turno);
        }
        this.dado = new Dado();
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * @throws Exception
     * post:
     */
    public void jugar() throws Exception {
    	boolean hayGanador = false;
        int posicion = 0;

        while(!hayGanador) {
        	dado.jugarDado();                       //tirar dado
        	int cartasALevantar = dado.getValor();  //Cartas a levantar
        	// Mostrar lo que sale por pantalla;
            // Levantar la cartas
            Turno turnoActual = turnos.obtener(posicion);
            mazo.levantarCartas(cartasALevantar, turnoActual.getJugador());

            Casillero<Ficha> casilleroDestino = null;
            turnoActual.iniciarTurno();
            if (turnoActual.estaBloqueado()) {
                while (turnoActual.haySubturnos()) {
                    turnoActual.utilizarSubturno();
                    if (!turnoActual.getJugador().tieneTodasLasFichasEnElTablero()) {
                        jugadaInicial(this.tablero, turnoActual.getJugador());
                    } else {
                        casilleroDestino = mover(this.tablero, turnoActual.getJugador());
                    }

                    //Si juega una carta
                    Carta cartaActual = Teclado.preguntarCarta(); //preguntar la carta del jugador
                    if (cartaActual != null) {
                        cartaActual.getJugada().jugar(this, turnoActual);
                    }
                }
            }

            turnoActual.terminarTurno();
            hayGanador = verificarGanador(casilleroDestino);

            posicion++;
            if (posicion == turnos.getLongitud()) {
                posicion = 0;
            }
        }
    }
    
    
    public void jugadaInicial(Tablero<Ficha> tablero, Jugador jugador) throws Exception {

        Ficha ficha = null; //crea
        int x = 0; //pregunta la posicion
        int y = 0;
        int z = 0;
        Casillero<Ficha> casillero = tablero.getCasillero(x, y, z);
        if (casillero.estaOcupado()) {
            throw new Exception("El casillero esta ocupado");
        }
        casillero.setDato(ficha);
    }

    /**
     * pre:
     *
     * @param tablero no puede ser nulo
     * @param jugador no puede ser nulo
     * @return mueve la ficha hacia el casillero en la direccion 'movimiento' ingresados por el jugador en el tablero,
     * y retorna el casillero donde se movio la ficha
     * @throws Exception si el tablero o jugador son nulos, o si el casillero en la direccion 'movimiento' no existe,
     *                   o esta ocupado
     */
    public Casillero<Ficha> mover(Tablero<Ficha> tablero, Jugador jugador) throws Exception {
        Validacion.validarSiEsNulo(tablero, "Tablero");
        Validacion.validarSiEsNulo(jugador, "Jugador");

        Ficha ficha = null; // TODO: preguntar posicion de la ficha al jugador
        int x = 0;          // posicion x
        int y = 0;          // posicion y
        int z = 0;          // posicion z
        Movimiento movimiento = null; // TODO: preguntar el movimiento al jugador
        Casillero<Ficha> casillero = tablero.getCasillero(x, y, z);

        if (!casillero.existeElVecino(movimiento)) {
            throw new Exception("No existe el movimiento");
        }
        if(casillero.getDato() == null) {
        	throw new Exception("No hay ficha en el casillero");
        }
        if (casillero.getCasilleroVecino(movimiento).estaOcupado()) {
            throw new Exception("El casillero al que se quiere mover esta ocupado");
        }
        tablero.mover(casillero, casillero.getCasilleroVecino(movimiento), ficha);
       
        return casillero.getCasilleroVecino(movimiento);
    }

    public boolean verificarGanador(Casillero<Ficha> casillero) throws Exception {
        Validacion.validarSiEsNulo(casillero, "Casillero");

        // FIXME: cantidad de fichas deberia estar fijo en 3, o debe ser dinamico??
        int cantidadDeFichas = 3;

        // TODO: esto seguramente contar las fichas seguidas en todas las direcciones, seguramente se pueda mejorar
        // Plano de atras
        int cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_ARRIBA,
                Direccion.ATRAS_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA,
                Direccion.ATRAS_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA_ARRIBA,
                Direccion.ATRAS_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA_ABAJO,
                Direccion.ATRAS_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        // Plano del centro
        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_ARRIBA,
                Direccion.CENTRO_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA,
                Direccion.CENTRO_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA_ARRIBA,
                Direccion.CENTRO_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA_ABAJO,
                Direccion.CENTRO_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        // Plano de adelante
        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_ARRIBA,
                Direccion.ADELANTE_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA,
                Direccion.ADELANTE_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA_ARRIBA,
                Direccion.ADELANTE_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA_ABAJO,
                Direccion.ADELANTE_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
        	return true; // Hay ganador!!!!!
        }
        return false;
    }

    /**
     * pre:
     *
     * @param casillero          no puede ser nulo
     * @param direccion          no puede ser nulo
     * @param direccionContraria no puede ser nulo
     * @return la cantidad de fichas seguidas del valor de la ficha, en la direccion respecto del casillero pasados por
     * parametro, y la direccion contraria
     * @throws Exception si alguno de los parametros es nulo
     */
    public int contarFichasSeguidasEnDireccion(Casillero<Ficha> casillero, Direccion direccion, Direccion direccionContraria) throws Exception {
        Validacion.validarSiEsNulo(casillero, "Casillero");
        Validacion.validarSiEsNulo(direccion, "Direccion");
        Validacion.validarSiEsNulo(direccionContraria, "Direccion");

        return this.contarFichasSeguidas(casillero, direccion, casillero.getDato()) +
                this.contarFichasSeguidas(casillero, direccionContraria, casillero.getDato());
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
        Validacion.validarSiEsNulo(casillero, "Casillero");
        Validacion.validarSiEsNulo(direccion, "Direccion");
        Validacion.validarSiEsNulo(ficha, "Ficha");

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
        Validacion.validarSiEsNulo(jugador, "Jugador");

        for (int i = 0; i < turnos.getLongitud(); i++) {
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
