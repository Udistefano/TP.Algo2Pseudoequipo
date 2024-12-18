package Main;

/*
 * Este tablero esta implementado en 2 dimensiones,
 * hay que implementarlo en 3 dimensiones.
 * Faltan los metodos tambien.
 */

import Estructuras.ListaSimple;

public class Tablero<T> {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    private static int TAMAÑO_MAXIMO_DE_COORDENADA = 5;
    private static int TAMAÑO_MINIMO_DE_COORDENADA = 3;

//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private ListaSimple<ListaSimple<ListaSimple<Casillero<T>>>> tablero = null;
    private int ancho = 0;
    private int alto = 0;
    private int profundidad = 0;

//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param ancho       debe ser mayor o igual a 1
     * @param alto        debe ser mayor o igual a 1
     * @param profundidad debe ser mayor o igual a 1
     * @throws Exception si alguna de las coordenadas es menor a 1
     * post: crea un tablero de ancho 'ancho' contando de 1 a ancho inclusive
     */
    public Tablero(int ancho, int alto, int profundidad) throws Exception {
        Casillero.validarCoordenadas(ancho, alto, profundidad);

        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = profundidad;
        this.tablero = new ListaSimple<ListaSimple<ListaSimple<Casillero<T>>>>();

        crearTablero();
        relacionarTodosLosCasillerosVecinosDelTablero();
    }

//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * @throws Exception si alguna de las coordenadas es invalida
     * post: crea un tablero de 3 dimensiones
     */
    public void crearTablero() throws Exception {
        for (int x = 1; x <= getAncho(); x++) {
            ListaSimple<ListaSimple<Casillero<T>>> plano = new ListaSimple<ListaSimple<Casillero<T>>>();
            tablero.agregar(plano);

            for (int y = 1; y <= getAlto(); y++) {
                ListaSimple<Casillero<T>> fila = new ListaSimple<Casillero<T>>();
                plano.agregar(fila);

                for (int z = 1; z <= getProfundidad(); z++) {
                    Casillero<T> nuevoCasillero = new Casillero<T>(x, y, z);
                    fila.agregar(nuevoCasillero);
                }
            }
        }
    }

    /**
     * pre: --
     * @throws Exception si alguna de las coordenadas es invalida
     * post: relaciona todos los casilleros vecinos, de todos los casilleros del tablero
     */
    public void relacionarTodosLosCasillerosVecinosDelTablero() throws Exception {
        for (int x = 1; x <= getAncho(); x++) {
            for (int y = 1; y <= getAlto(); y++) {
                for (int z = 1; z <= getProfundidad(); z++) {
                    relacionarTodosLosCasillerosVecinosDelCasilleroActual(x, y, z);
                }
            }
        }
    }

    /**
     * pre:
     * @param x no puede ser menor a 1 ni mayor a getAncho()
     * @param y no puede ser menor a 1 ni mayor a getAlto()
     * @param z no puede ser menor a 1 ni mayor a getProfundidad()
     * @throws Exception si alguna de las coordenadas es invalida
     * post: Relaciona todos los vecinos del casillero actual
     * En 2D si estamos en las coordenadas (x, y) debemos asignar los casilleros: (x-1, y-1),
     *                   (x-1, y), (x-1, y+1), (x, y-1)
     * En 3D es similar, pero le agregamos de coordenada z variando desde -1 a +1, y tambien asignando
     *                   (x, y, z-1), (x, y, z+1)
     */
    public void relacionarTodosLosCasillerosVecinosDelCasilleroActual(int x, int y, int z) throws Exception {
        Casillero.validarCoordenadas(x, y, z);
        Casillero<T> casillero = getCasillero(x, y, z);

        for (int i = -1; i <= 1; i++) {  // sería el eje z
            for (int j = -1; j <= 1; j++) {
                if (existeElCasillero(x - 1, y + j, z + i)) { // seria el eje y para un plano
                    relacionarCasillerosVecinos(getCasillero(x - 1, y + j, z + i), casillero, -1, j, i);
                }
            }

            if (existeElCasillero(x, y - 1, z + i)) {
                relacionarCasillerosVecinos(getCasillero(x, y - 1, z + i), casillero, 0, -1, i);
            }

            if ((existeElCasillero(x, y, z + i))
                    && (i != 0)) {
                relacionarCasillerosVecinos(getCasillero(x, y, z + i), casillero, 0, 0, i);
            }
        }
    }

    /**
     * pre:
     * @param casillero1: no puede ser nulo
     * @param casillero2: no puede ser nulo
     * @param x:          rango entre -1, 0 y 1
     * @param y:          rango entre -1, 0 y 1
     * @param z:          rango entre -1, 0 y 1
     * post: relaciona los dos vecinos en sus matrices de vecinos, en el casillero1 como x, y, z, y en casillero2
     *                    como el opuesto
     */
    public void relacionarCasillerosVecinos(Casillero<T> casillero1, Casillero<T> casillero2, int x, int y, int z) throws Exception {
        Casillero.validarCoordenadasDeVecino(x, y, z);
        ValidacionesUtiles.validarSiEsNulo(casillero1, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(casillero2, "Casillero");

        casillero2.setCasilleroVecino(casillero1, x, y, z);
        casillero1.setCasilleroVecino(casillero2, Casillero.invertirCoordenadaDeVecino(x),
                Casillero.invertirCoordenadaDeVecino(y),
                Casillero.invertirCoordenadaDeVecino(z));
    }

    /**
     * pre:
     * @param x     no puede ser menor a 1 ni mayor a getAncho()
     * @param y     no puede ser menor a 1 ni mayor a getAlto()
     * @param z     no puede ser menor a 1 ni mayor a getProfundidad()
     * @param ficha no puede ser nula
     * @throws Exception si no existe el casillero o si la ficha es nula
     * post: Agrega la ficha al casillero en las coordenadas pasadas por parametro
     */
    public void agregar(int x, int y, int z, T ficha) throws Exception {
        if (!existeElCasillero(x, y, z)) {
            throw new Exception("Coordenadas de casillero invalidas");
        }
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");

        Casillero<T> casillero = getCasillero(x, y, z);
        casillero.setDato(ficha);
    }
    
    /**
     * pre: El casillero vecino no puede estar ocupado y debe existir el movimiento
     * @param origen no puede ser nulo
     * @param destino no puede ser nulo
     * @param ficha no puede ser nulo, ni estar bloqueado
     * post: Mueve la ficha al casillero de destino
     * @throws Exception asi alguno de los parametros es nulo, o si ficha esta bloqueada
     */
    public void mover(Casillero<Ficha> origen, Casillero<Ficha> destino, Ficha ficha) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(origen, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(destino, "Casillero");
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");
        
        origen.setDato(null);
        destino.setDato(ficha);
    }
    

    /**
     * pre:
     * @param x no puede ser menor a 1 ni mayor a getAncho()
     * @param y no puede ser menor a 1 ni mayor a getAlto()
     * @param z no puede ser menor a 1 ni mayor a getProfundidad()
     * @return el casillero en las coordenadas pasadas por parametro
     * @throws Exception si alguna de las coordenadas es invalida
     */
    public Casillero<T> getCasillero(int x, int y, int z) throws Exception {
        if (!existeElCasillero(x, y, z)) {
            throw new Exception("Coordenadas de casillero invalidas");
        }
        Casillero<T> copiaDeCasillero = tablero.obtener(x).obtener(y).obtener(z);
        return copiaDeCasillero;
    }
    

    /**
     * pre:
     * @param x no puede ser menor a 1 ni mayor a getAncho()
     * @param y no puede ser menor a 1 ni mayor a getAlto()
     * @param z no puede ser menor a 1 ni mayor a getProfundidad()
     * @return el dato del casillero en las coordenadas pasadas por parametro
     * @throws Exception si alguna de las coordenadas es invalida
     */
    public T obtener(int x, int y, int z) throws Exception {
        if (!existeElCasillero(x, y, z)) {
            throw new Exception("Coordenadas de casillero invalidas");
        }
        T copiaDeDato = getCasillero(x, y, z).getDato();
        return copiaDeDato;
    }

    /**
     * pre:
     * @param x no puede ser menor a 1 ni mayor a getAncho()
     * @param y no puede ser menor a 1 ni mayor a getAlto()
     * @param z no puede ser menor a 1 ni mayor a getProfundidad()
     * @return verdadero si existe el casillero vecino, falso si no existe
     * @throws Exception si alguna de las coordenadas es invalida
     */
    public boolean existeElCasillero(int x, int y, int z) throws Exception {
        if ((x < 1) ||
                (y < 1) ||
                (z < 1)) {
            return false;
        }
        return (x <= getAncho()) &&
                (y <= getAlto()) &&
                (z <= getProfundidad());
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     * @return el ancho del tablero
     */
    public int getAncho() {
        return this.ancho;
    }

    /**
     * pre: --
     * @return el alto del tablero
     */
    public int getAlto() {
        return this.alto;
    }

    /**
     * pre: --
     * @return la profundidad del tablero
     */
    public int getProfundidad() {
        return this.profundidad;
    }

    /**
     * pre: --
     * @return el tamaño maximo permitido que puede tener una coordenada
     */
    public static int getTamañoMaximoDeCoordenada() {
        return TAMAÑO_MAXIMO_DE_COORDENADA;
    }

    /**
     * pre: --
     * @return el tamaño minimo permitido que puede tener una coordenada
     */
    public static int getTamañoMinimoDeCoordenada() {
        return TAMAÑO_MINIMO_DE_COORDENADA;
    }

//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}