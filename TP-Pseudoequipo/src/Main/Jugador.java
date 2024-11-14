package Main;

import Cartas.Carta;
import Estructuras.Lista;

import java.util.Objects;


public class Jugador {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private String nombre = null;
    private int cantidadDeFichasJugadas = 0;
    private int cantidadDeFichasMaximasPermitidas = 0;
    private char simbolo = 0;
    private String color = null;
    public Lista<Carta> mano = null;

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
    public Jugador(String nombre, char simbolo, String color, int cantidadDeFichasMaximasPermitidas) throws Exception {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.color = color;
        this.cantidadDeFichasMaximasPermitidas = cantidadDeFichasMaximasPermitidas;
        this.mano = new Lista<Carta>();
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
            return nombre.equals((String) obj);
        if (getClass() != obj.getClass())
            return false;

        Jugador other = (Jugador) obj;
        return (nombre.equals(other.nombre)) &&
                (simbolo == other.simbolo) &&
                (color.equals(other.color));
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
        return nombre + " " + simbolo;
    }

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * @return verdadero si el jugador tiene todas las fichas en el tablero, falso si no
     */
    public boolean tieneTodasLasFichasEnElTablero() {
        return cantidadDeFichasMaximasPermitidas == cantidadDeFichasJugadas;
    }

    /**
     * pre: --
     * @throws Exception si al jugador no le quedan mas fichas
     * post: juega una ficha del jugador
     */
    public void jugarFicha() throws Exception {
        if (tieneTodasLasFichasEnElTablero()) {
            throw new Exception("Al jugador no le quedan mas fichas para jugar");
        }
        this.cantidadDeFichasJugadas++;
    }

    // TODO: limpiar metodos que no se usen en TDA Jugador
    /**
     * pre:
     * @param cacillero casillero:
     * @throws post:
     */
    public void jugarFicha(Casillero<Ficha> casillero) throws Exception {
        if (casillero.estaOcupado()) {
            throw new Exception("Casillero ocupado");
        } else {
            //Ficha nuevaFicha = new Ficha();
            //casillero.setDato(nuevaFicha);
        }
    }
 
    /**
     * pre:
     *
     * @param
     * @throws post:
     */
    public Lista<Carta> devolverMano() throws Exception{
        return mano;
    }
    /**
     * pre:
     *
     * @param
     * @throws post:
     */
    public void tomarCartas(Carta carta) throws Exception {
        mano.agregar(carta);
    }

    /**
     * pre: --
     * @return
     */
    public Carta devolverCartas() throws Exception{
        //implementar seleccionar carta
        //Validacion.cartaValida();
        Carta carta = mano.obtenerCursor();
        mano.remover(carta);
        return carta;

    }

    // Soy ulises, creo que esto esta bien implementado, cualquier cosa mandenme

//    /**
//     * pre: metodo para agregar a su lista fichas cuando se usa la carta para cambiar color de su ficha
//     * @param ficha no puede ser nulo
//     * @throws Exception si ficha es nulo
//     * post: agrega la ficha pasada por parametro, a la lista de fichas del jugador
//     */
//    public void agregarFicha(Ficha ficha) throws Exception {
//        Validacion.validarSiEsNulo(ficha, "Ficha");
//        fichas.agregar(ficha);
//    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el simbolo del jugador
     */
    public char getSimbolo() {
        return simbolo;
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

    /**
     * pre: --
     * @return la mano de cartas del jugador
     */
	public Lista<Carta> getMano() {
		return mano;
	}

    /**
     * pre: --
     * @return la cantidad de fichas jugadas del jugador
     */
    public int getCantidadDeFichasJugadas() {
        return cantidadDeFichasJugadas;
    }

    /**
     * pre: --
     * @return la cantidad de fichas maximas permitidas del jugador
     */
    public int getCantidadDeFichasMaximasPermitidas() {
        return cantidadDeFichasMaximasPermitidas;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
