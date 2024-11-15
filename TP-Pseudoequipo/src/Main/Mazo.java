package Main;

import Estructuras.Lista;
import Estructuras.Pila;

import Cartas.Carta;
import Cartas.CartaAnularCasillero;
import Cartas.CartaBloquearFicha;
import Cartas.CartaCambiarColorFicha;
import Cartas.CartaDobleTurno;
import Cartas.CartaEliminarCartasDelJugador;
import Cartas.CartaPerderTurno;
import Cartas.CartaVolverJugadaAnterior;

public class Mazo {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------

	// FIXME: el enunciado del TP, dice que el mazo debe ser una cola de cartas, no una pila de cartas
	public Pila <Carta> cartas = null;
    private int maximoDeCartasEnMazo = 0;

	//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre: --
     * pos: crea un mazo de cartas con 50 cartas de todos los tipos. No hay un maximo de cartas por tipo
     */
    public Mazo(int maximoCartas) throws Exception {
    	this.maximoDeCartasEnMazo = maximoCartas;
    	this.cartas = new Pila<Carta>();
    	Carta cartaASumar = null;
    	for (int i = 0; i < maximoDeCartasEnMazo; i++) {
    		cartaASumar = generarCartaAleatoria();
			this.cartas.apilar(cartaASumar);
    	}
    }

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------

	/**
	 * pre: --
	 * @return una cadena mostrando todas las cartas del mazo
	 */
	@Override
    public String toString() {
    	return "El mazo posee las siguientes cartas: " + cartas;
    }

	/**
	 * pre: --
	 * @return el hashCode del mazo
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    
    /**
     * 
     * @return cartaActual: de tipo carta, elegida aleatoriamente para sumar al mazo
     */
    public Carta generarCartaAleatoria() throws Exception {
    	Carta cartaActual = null;
    	switch(TipoDeCarta.generarTipoDeCartaAleatorio()) {
			case TipoDeCarta.CAMBIAR_COLOR_FICHA:
    			cartaActual = new CartaCambiarColorFicha();
    			break;
			case TipoDeCarta.ANULAR_CASILLERO:
    			cartaActual = new CartaAnularCasillero();
    			break;
			case TipoDeCarta.BLOQUEAR_FICHA:
    			cartaActual = new CartaBloquearFicha();
    			break;
			case TipoDeCarta.DOBLE_TURNO:
    			cartaActual = new CartaDobleTurno();
    			break;
			case TipoDeCarta.ELIMINAR_CARTAS_DEL_JUGADOR:
    			cartaActual = new CartaEliminarCartasDelJugador();
    			break;
			case TipoDeCarta.PERDER_TURNO:
    			cartaActual = new CartaPerderTurno();
    			break;
			case TipoDeCarta.VOLVER_JUGADA_ANTERIOR:
    			cartaActual = new CartaVolverJugadaAnterior(); 		
    			break;
			default:
				throw new Exception("El tipo de carta no esta creada todavia!");
    	}
		return cartaActual;
    }
    
    /**
     * pre:
     * @param cantidadDeCartasALevantar: numero entero de cartas a levantar por el jugador y el dado. No puede ser nulo
     * @param jugador: jugador que levanta las cartas. No puede ser nulo
     * @throws Exception: si no hay suficientes cartas en el mazo, crear un nuevo mazo
     * pos: a la mano del jugador se agregan las cartas solicitadas
     */
    public void levantarCartas(int cantidadDeCartasALevantar, Jugador jugador) throws Exception {
    	ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
		ValidacionesUtiles.validarSiNumeroEsMenorAUno(cantidadDeCartasALevantar, "Cartas a levantar");

		if (!jugador.getMano().estaVacia()) {
			agregarManoDelJugador(jugador);
		}
    	if (cantidadDeCartasALevantar > cartas.contarElementos()) {
    		throw new Exception("No hay suficientes cartas para levantar");
    	}
    	for (int i = 0; i < cantidadDeCartasALevantar; i++) {
    		jugador.agregarCartaALaMano(cartas.obtener());
    		cartas.desapilar();
        }
    }
    
    /**
     * pre: --
     * @param jugador no puede ser nulo
     * @throws Exception si el jugador no tiene cartas
     * post: devuelve todas las cartas de un jugador al mazo
     */
    public void agregarManoDelJugador(Jugador jugador) throws Exception {
		ValidacionesUtiles.validarSiEsNulo(jugador, "Jugador");
		Lista<Carta> manoDelJugador = jugador.getMano();
		if (manoDelJugador.estaVacia()) {
			throw new Exception("\nEl jugador " + jugador + " no tiene mas cartas en el mazo!");
		}

    	manoDelJugador.iniciarCursor();
    	while(manoDelJugador.avanzarCursor()) {
			agregarCarta(manoDelJugador.obtenerCursor());
			jugador.quitarCartaDeLaMano(manoDelJugador.obtenerCursor());
    	}
    }

	/**
	 * pre:
	 * @param carta no puede ser nula
	 * @throws Exception si la carta es nula
	 * post: agrega la carta al mazo
	 */
	public void agregarCarta(Carta carta) throws Exception {
		ValidacionesUtiles.validarSiEsNulo(carta, "Carta");
		this.cartas.apilar(carta);
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return la pila de cartas
     */
	public Pila<Carta> getCartas() {
		// FIXME: esto viola el encapsulamiento, devolver una pila asi, hay que devolver una copia
		return cartas;
	}

	/**
	 * pre: --
	 * @return el maximo de cartas del mazo
	 */
	public int getMaximoDeCartasEnMazo() {
		return maximoDeCartasEnMazo;
	}

	//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
