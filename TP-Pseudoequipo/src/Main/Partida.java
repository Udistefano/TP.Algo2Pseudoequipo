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
    private Bitmap bitmap = null;
    // FIXME: en Partida, turnos deberia ser un Vector, una Lista, o una Pila ???????
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
        Validacion.validarSiEsNulo(tablero, "Tablero");
        Validacion.validarSiEsNulo(jugadores, "Lista de Jugadores");
        Validacion.validarSiEsNulo(mazo, "Mazo");

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

        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("Inicio de partida");
        System.out.println("--------------------------------------------------------------------------------");

        while(!hayGanador) {
            if (posicion > turnos.getLongitud()) {
                posicion = 1;
            }

            Turno turnoActual = turnos.obtener(posicion);
            jugadorActual = turnoActual.getJugador();
            int numeroDelDado = dado.jugarDado();

            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("Turno del jugador " + jugadorActual);
            System.out.println("--------------------------------------------------------------------------------");

            System.out.println("\n" + jugadorActual + " tira el dado! Dio el numero " + numeroDelDado);
            System.out.println(jugadorActual + " levanta " + numeroDelDado + " cartas del mazo");

            mazo.levantarCartas(numeroDelDado, jugadorActual);
//            System.out.println("\nMano de " + jugadorActual + " es: " + jugadorActual.getMano());
            Casillero<Ficha> casilleroDestino = jugarTurno(turnoActual);
            hayGanador = verificarGanador(casilleroDestino);
            posicion++;
        }

        // TODO: quiza crear clase UtilesVarios y agregar esto de imprimir asi con 2 lineas
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("Fin de partida");
        System.out.println("--------------------------------------------------------------------------------");

        return jugadorActual;
    }


    /**
     * pre:
     * @param turno no puede ser nulo
     * post: juega el turno (si no tiene bloqueos), y retorna el casillero al que se movio o jugo una ficha
     * @throws Exception si turno es nulo
     */
    public Casillero<Ficha> jugarTurno(Turno turno) throws Exception {
        Validacion.validarSiEsNulo(turno, "Turno");
        Casillero<Ficha> casilleroDestino = null;

        turno.iniciarTurno();
        if (!turno.estaBloqueado()) {
            while (turno.haySubturnos()) {
                turno.utilizarSubturno();
                Jugador jugador = turno.getJugador();

                if (!jugador.tieneTodasLasFichasEnElTablero()) {
                    casilleroDestino = jugadaInicial(this.tablero, jugador);
                } else {
                    casilleroDestino = mover(this.tablero, jugador);
                }
                Bitmap.escribirFichaAlBitmap(casilleroDestino, jugador.getColor());

                Carta cartaActual = Teclado.preguntarCarta(jugador.devolverMano());
                if (cartaActual != null) {
                    cartaActual.getJugada().jugar(this, turno);
                }
                Bitmap.escribirArchivo();
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
        Validacion.validarSiEsNulo(tablero, "Tablero");
        Validacion.validarSiEsNulo(jugador, "Jugador");
        if (jugador.tieneTodasLasFichasEnElTablero()) { // Validacion quiza redundante, pero por si acaso
            throw new Exception("\nAl jugador no le quedan mas fichas para jugar");
        }

        Ficha ficha = new Ficha(jugador.getColor());
        System.out.println("\n" + jugador + " tiene " + jugador.getCantidadDeFichasRestantes() + " fichas");
        System.out.println("\nIngrese las coordenadas del casillero donde jugar una ficha:\n");
        Casillero<Ficha> casillero = preguntarCasillero();
        if (casillero.estaOcupado()) {
            throw new Exception("\nEl casillero esta ocupado");
        }
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
        Validacion.validarSiEsNulo(tablero, "Tablero");
        Validacion.validarSiEsNulo(jugador, "Jugador");

        System.out.println("\n" + jugador + " no le quedan mas fichas para jugar");
        System.out.println("Tendra que mover una ficha del tablero.");
        System.out.println("\nIngrese las coordenadas del casillero del cual mover su ficha");
        Casillero<Ficha> casillero = preguntarCasillero();
        Movimiento movimiento = Teclado.preguntarMovimiento();
        Ficha fichaAMover = casillero.getDato();

        Validacion.validarSiEsNulo(casillero.getDato(), "Ficha");
        Validacion.validarSiFichaEstaBloqueada(fichaAMover);
        if (!casillero.existeElVecino(movimiento)) {
            throw new Exception("\nNo existe el movimiento " + movimiento);
        }
        if (casillero.getCasilleroVecino(movimiento).estaOcupado()) {
            throw new Exception("\nEl casillero en direccion " + movimiento + " al que se quiere mover esta ocupado");
        }
        tablero.mover(casillero, casillero.getCasilleroVecino(movimiento), fichaAMover);
       
        return casillero.getCasilleroVecino(movimiento);
    }

    /**
     * pre: --
     * @throws Exception si no existe el casillero con las coordenadas ingresadas por el jugador
     * post: le pregunta al jugador las coordenadas de un casillero, valida que exista ese casillero, y devuelve
     *       la ficha en ese casillero
     */
    public Ficha preguntarFicha() throws Exception {
        return preguntarCasillero().getDato();
    }

    /**
     * pre: --
     * @throws Exception si no existe el casillero con las coordenadas ingresadas por el jugador
     * post: le pregunta al jugador las coordenadas de un casillero, valida que exista ese casillero, y lo devuelve
     */
    public Casillero<Ficha> preguntarCasillero() throws Exception {
        // TODO: ?¿?¿?permitir que si el jugador ingresa coordenadas invalidas, pueda volver a ingresar de vuelta
        int x = Teclado.preguntarCoordenada('x');
        int y = Teclado.preguntarCoordenada('y');
        int z = Teclado.preguntarCoordenada('z');

        if (!tablero.existeElCasillero(x, y, z)) {
            throw new Exception("Coordenadas de casillero invalidas");
        }

        return tablero.getCasillero(x, y, z);
    }

    public boolean verificarGanador(Casillero<Ficha> casillero) throws Exception {
        Validacion.validarSiEsNulo(casillero, "Casillero");
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
        Validacion.validarSiEsNulo(casillero, "Casillero");
        Validacion.validarSiEsNulo(direccion, "Direccion");
        Validacion.validarSiEsNulo(direccionContraria, "Direccion");

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
    
   /**
    * pre: --
    * @return el bitmap de la partida
    */
    public Bitmap getBitmap() {
    	return this.bitmap;
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
