package Main;

import Cartas.Carta;
import Estructuras.Lista;

public class Partida {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private Tablero tablero = null;
    private Lista<Jugador> jugadores = null;
    private Mazo mazo = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Partida(Tablero tablero, Lista<Jugador> jugadores, Mazo mazo) throws Exception {
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

    public void jugar() throws Exception {
        //while x turno
        //Levantar la carta

        Turno turnoActual = null;
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
                Carta cartaActual = null; //preguntar la carta del jugador
                if (cartaActual != null) {
                    cartaActual.getJugada().jugar(this, turnoActual);
                }
            }
        }
        turnoActual.terminarTurno();
        verificarGanador(casilleroDestino);
    }

    public void jugadaInicial(Tablero<Ficha> tablero, Jugador jugador) throws Exception {

        Ficha ficha = null; //crea
        int x = 0; //pregunta la posicion
        int y = 0;
        Casillero<Ficha> casillero = tablero.getCasillero(x, y);
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
        if (tablero == null) {
            throw new Exception("El tablero no puede ser nulo");
        }
        if (jugador == null) {
            throw new Exception("El jugador no puede ser nulo");
        }
        Ficha ficha = null; // TODO: preguntar la ficha al jugador
        Movimiento movimiento = null; // TODO: preguntar el movimiento al jugador
        Casillero<Ficha> casillero = tablero.getCasillero(ficha);

        if (!casillero.existeElVecino(movimiento)) {
            throw new Exception("No existe el movimiento");
        }
        if (casillero.getCasilleroVecino(movimiento).estaOcupado()) {
            throw new Exception("El casillero al que se quiere mover esta ocupado");
        }
        tablero.mover(casillero, casillero.getCasilleroVecino(movimiento), ficha);

        return casillero.getCasilleroVecino(movimiento);
    }

    public void verificarGanador(Casillero<Ficha> casillero) throws Exception {
        if (casillero == null) {
            throw new Exception("El casillero no puede ser nulo");
        }
        // FIXME: cantidad de fichas deberia estar fijo en 3, o debe ser dinamico??
        int cantidadDeFichas = 3;

        // TODO: esto seguramente contar las fichas seguidas en todas las direcciones, seguramente se pueda mejorar
        // Plano de atras
        int cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_ARRIBA,
                Direccion.ATRAS_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // TODO: implementar que hacer cuando hay ganador, en verificarGAnador
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA,
                Direccion.ATRAS_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA_ARRIBA,
                Direccion.ATRAS_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ATRAS_IZQUIERDA_ABAJO,
                Direccion.ATRAS_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        // Plano del centro
        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_ARRIBA,
                Direccion.CENTRO_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA,
                Direccion.CENTRO_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA_ARRIBA,
                Direccion.CENTRO_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.CENTRO_IZQUIERDA_ABAJO,
                Direccion.CENTRO_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        // Plano de adelante
        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_ARRIBA,
                Direccion.ADELANTE_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA,
                Direccion.ADELANTE_DERECHA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA_ARRIBA,
                Direccion.ADELANTE_DERECHA_ABAJO);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }

        cantidadDeFichasSeguidas = this.contarFichasSeguidasEnDireccion(casillero, Direccion.ADELANTE_IZQUIERDA_ABAJO,
                Direccion.ADELANTE_DERECHA_ARRIBA);
        if (cantidadDeFichas <= cantidadDeFichasSeguidas) {
            // Hay ganador!!!!!, falta implementar que hacer cuando hay ganador
        }
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
        if (casillero == null) {
            throw new Exception("El casillero no puede ser nulo");
        }
        if ((direccion == null) || (direccionContraria == null)) {
            throw new Exception("La direccion no puede ser nula");
        }

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
        if (casillero == null) {
            throw new Exception("El casillero no puede ser nulo");
        }
        if (direccion == null) {
            throw new Exception("La direccion no puede ser nula");
        }
        // TODO: hacer clase de ValidacionesUtiles que cheque si algo es nulo
        if (ficha == null) {
            throw new Exception("La ficha no puede ser nula");
        }

        if (!casillero.getDato().equals(ficha)) {
            return 0;
        }
        return 1 + contarFichasSeguidas(casillero.getCasilleroVecino(direccion), direccion, ficha);
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    public Tablero getTablero() {
        return tablero;
    }

    public Lista<Jugador> getJugadores() {
        return jugadores;
    }

    public Mazo getMazo() {
        return mazo;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
