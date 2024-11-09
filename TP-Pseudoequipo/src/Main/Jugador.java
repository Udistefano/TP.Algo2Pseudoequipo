package Main;

import Cartas.Carta;
import Estructuras.Lista;

import java.util.Objects;

public class Jugador {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private String nombre = null;
    private char simbolo = 0;
    private String color = null;
    public Lista<Carta> mano = null;
    private Lista<Ficha> fichas = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    /**
     * pre:
     * @param nombre no puede ser vacio
     * @param simbolo no puede ser vacio
     * @param color no puede ser vacio
     * @throws Exception si alguno de los parametros es vacio
     * post: inicializa un jugador con el simbolo, nombre y color pasados por parametro, y con fichas y mano
     *       vacias
     */
    public Jugador(String nombre, char simbolo, String color) throws Exception {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.color = color;
        this.fichas = new Lista<Ficha>();
        this.mano = new Lista<Carta>(); // FIXME: la mano de cartas del jugador, no tendria que ser una pila?
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------

    /**
     * pre:
     * @param obj con el cual comparar a la ficha actual
     * @return verdadero si obj equivale a la ficha actual, falso si no
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof String)
            return this.nombre.equals((String) obj);
        if (getClass() != obj.getClass())
            return false;
        Jugador other = (Jugador) obj;
        return (this.nombre.equals(other.nombre)) &&
                (this.simbolo == other.simbolo) &&
                (this.color.equals(other.color));
    }

    /**
     * pre: --
     * @return el hashCode del jugador actual
     */
    @Override
    public int hashCode() {
        return Objects.hash(simbolo, color);
    }

    /**
     * pre: --
     * @return una cadena mostrando el nombre del jugador y simbolo
     */
    public String toString() {
        return this.nombre + " " + this.simbolo;
    }

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * @return verdadero si el jugador tiene todas las fichas en el tablero, falso si no
     */
    // TODO: el profe agrego este metodo en su TDA Jugador en la clase 20, pero no lo implemento
    //       hay que implementarlo y ver donde usarlo
    public boolean tieneTodasLasFichasEnElTablero() {
        return false;
    }

    /**
     * pre:
     *
     * @param cacillero casillero
     * @throws post:
     */
    public void jugarFicha(Casillero<Ficha> casillero) throws Exception {
        if (casillero.estaOcupado()) {
            throw new Exception("Casillero ocupado");
        } else {
            Ficha nuevaFicha = new Ficha(this);
            casillero.setDato(nuevaFicha);

        }
    }

    /**
     * pre:
     *
     * @param tablero:   tablero de juego
     * @param casillero:
     * @throws Exception
     * @throws post:
     */
    public void moverFicha(Casillero<Ficha> casillero) throws Exception {
        if (casillero.getDato().getjugador() == this) {
            //mover a vecino
            Movimiento movimiento = null;//solicitar movimiento?
            if (casillero.existeElVecino(movimiento) && !casillero.getCasilleroVecino(movimiento).estaOcupado()) {
                casillero.setCasilleroVecino(casillero.setCasilleroVecino(movimiento), simbolo, simbolo, simbolo);
            }


        }
    }

    /**
     * pre:
     *
     * @param
     * @throws post:
     */
    public void usarCarta() {

    }

    /**
     * pre:
     *
     * @param
     * @throws post:
     */
    public void tomar_cartas(Carta carta) throws Exception {
        mano.agregar(carta);
    }

    /**
     * pre: --
     * @return
     */
    public TipoDeCarta devolveCartas() {
        //implementar seleccionar carta
        return this.mano.obtenerCursor().getTipo();

    }

    // Soy ulises, creo que esto esta bien implementado, cualquier cosa mandenme

    /**
     * pre: metodo para agregar a su lista fichas cuando se usa la carta para cambiar color de su ficha
     * @param ficha no puede ser nulo
     * @throws Exception si ficha es nulo
     * post: agrega la ficha pasada por parametro, a la lista de fichas del jugador
     */
    public void agregarFicha(Ficha ficha) throws Exception {
        if (ficha == null) {
            throw new Exception("La ficha no puede ser null");
        }
        this.fichas.agregar(ficha);
    }


    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * pre: --
     * @return el simbolo del jugador
     */
    public char getSimbolo() {
        return this.simbolo;
    }

    /**
     * pre: --
     * @return el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * pre: --
     * @return el color del jugador
     */
    public String getColor() {
        return color;
    }
}
