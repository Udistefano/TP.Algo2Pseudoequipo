package Main;

import Cartas.Carta;
import Estructuras.ListaSimple;

import java.util.Objects;

public class Jugador {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private String nombre = null;
    private int cantidadDeFichasJugadas = 0;
    private int cantidadDeFichasMaximasPermitidas = 0;
    private Color color = null;
    private ListaSimple<Carta> mano = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param nombre no puede ser vacio
     * @param numeroDeColor no puede ser menor a 1
     * @throws Exception si alguno de los parametros es vacio
     * post: inicializa un jugador con el simbolo, nombre y color pasados por parametro, y con fichas y mano
     *       vacias
     */
    public Jugador(String nombre, int numeroDeColor, int cantidadDeFichasMaximasPermitidas) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(nombre, "Nombres");
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(numeroDeColor, "Numero de color");
        ValidacionesUtiles.validarSiNumeroEsMenorAUno(cantidadDeFichasMaximasPermitidas, "Cantidad de fichas maximas permitidas");
        this.nombre = nombre;
        this.color = Color.getColorJugador(numeroDeColor);
        this.cantidadDeFichasMaximasPermitidas = cantidadDeFichasMaximasPermitidas;
        this.mano = new ListaSimple<Carta>();
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
                (color.equals(other.color));
    }

    /**
     * pre: --
     * @return el hashCode del jugador actual
     */
    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    /**
     * pre: --
     * @return una cadena mostrando el nombre del jugador y simbolo
     */
    public String toString() {
        return nombre + " (" + color + ")";
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

    /**
     * pre:
     * @param carta no puede ser nula
     * @throws Exception si carta es nula
     * post: agrega la carta a la mano del jugador
     */
    public void agregarCartaALaMano(Carta carta) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(carta, "Carta");
        this.mano.agregar(carta);
    }

    /**
     * pre: --
     * post: Metodo que suma una ficha cuando el jugador utiliza la carta de doble turno
     */
    public void sumarFicha() {
    	this.cantidadDeFichasMaximasPermitidas++;
    }

    /**
     * pre:
     * @param carta no puede ser nula
     * @throws Exception si la carta es nula
     * post: quita la carta de la mano del jugador
     */
    public void quitarCartaDeLaMano(Carta carta) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(carta, "Carta");
        this.mano.remover(carta);
    }

    /**
     * pre: --
     * post: muestra la mano del jugador
     */
    public void mostrarMano() {
        this.mano.iniciarCursor();
        for(int i = 1; i <= this.mano.getLongitud(); i++){
            this.mano.avanzarCursor();
            System.out.println(i + " - " + this.mano.obtenerCursor());
        }
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el nombre del jugador
     */
    public String getNombre() {
        String copiaDeNombre = this.nombre;
        return copiaDeNombre;
    }

    /**
     * pre: --
     * @return el color del jugador
     */
    public Color getColor() {
        Color copiaDeColor = this.color;
        return copiaDeColor;
    }

    /**
     * pre: --
     * @return la mano de cartas del jugador
     * @throws Exception si hubo un error interno haciendo una copia de la mano
    */
    public ListaSimple<Carta> getMano() throws Exception {
        ListaSimple<Carta> copiaDeMano = new ListaSimple<Carta>();

        this.mano.iniciarCursor();
        while (this.mano.avanzarCursor()) {
            copiaDeMano.agregar(this.mano.obtenerCursor());
        }

        return copiaDeMano;
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
    
    /**
     * pre: --
     * @return la cantidad de fichas que le quedan al jugador
     */
    public int getCantidadDeFichasRestantes() {
        return cantidadDeFichasMaximasPermitidas - cantidadDeFichasJugadas;
    }

    //SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * post: vacia la mano del jugador
     */
    public void vaciarMano() {
        this.mano = new ListaSimple<Carta>();
    }
}
