package Main;

/*
 * Este tablero esta implementado en 2 dimensiones,
 * hay que implementarlo en 3 dimensiones.
 * Faltan los metodos tambien.
 */

import Estructuras.Lista;

public class Tablero<T> {
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private Lista<Lista<Lista<Casillero<T>>>> tablero = null;
    private int ancho = 0;
    private int alto = 0;
    private int profundidad = 0;

//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     *
     * @param ancho       debe ser mayor o igual a 1
     * @param alto        debe ser mayor o igual a 1
     * @param profundidad debe ser mayor o igual a 1
     * @throws Exception si alguna de las coordenadas es menor a 1
     *                   post: crea un tablero de ancho 'ancho' contando de 1 a ancho inclusive
     */
    public Tablero(int ancho, int alto, int profundidad) throws Exception {
        Casillero.validarCoordenadas(ancho, alto, profundidad);

        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = profundidad;
        this.tablero = new Lista<Lista<Lista<Casillero<T>>>>();

        for (int x = 1; x <= ancho; x++) {
            Lista<Lista<Casillero<T>>> plano = new Lista<Lista<Casillero<T>>>();
            this.tablero.agregar(plano);

            for (int y = 1; y <= alto; y++) {
                Lista<Casillero<T>> fila = new Lista<Casillero<T>>();
                plano.agregar(fila);

                for (int z = 1; z <= profundidad; z++) {
                    Casillero<T> nuevoCasillero = new Casillero<T>(x, y, z);
                    fila.agregar(nuevoCasillero);


                    // Relacionar vecinos, este está en 2 dimensiones para tomar referencia.
                    // Estoy en (i, j), tengo que asignar (i-1, j+1), (i-1, j+0), (i-1, j-1), (i, j-1) --> 2D
                    // En 3D deberia ser lo mismo pero agregandole el eje z variando desde -1 a +1
                    for (int i = -1; i <= 1; i++) {  // sería el eje z
                        for (int j = -1; j <= 1; j++) {
                            if (this.existeElCasillero(x - 1, j, i)) { // seria el eje y para un plano
                                relacionarCasillerosVecinos(this.getCasillero(x - 1, j, i), nuevoCasillero, -1, j, i);
                            }
                        }
                        if (this.existeElCasillero(x, y - 1, i)) {
                            relacionarCasillerosVecinos(this.getCasillero(x, y - 1, i), nuevoCasillero, 0, -1, i);
                        }
                    }
                }

                // Avanzo a siguiente fila para la busqueda de vecinos
                this.tablero.avanzarCursor();
            }
        }
    }

//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre:
     *
     * @param casillero1: no puede ser nulo
     * @param casillero2: no puede ser nulo
     * @param x:          rango entre -1, 0 y 1
     * @param y:          rango entre -1, 0 y 1
     * @param z:          rango entre -1, 0 y 1
     *                    post: relaciona los dos vecinos en sus matrices de vecinos, en el casillero1 como x, y, z, y en casillero2
     *                    como el opuesto
     */
    public void relacionarCasillerosVecinos(Casillero<T> casillero1, Casillero<T> casillero2, int x, int y, int z) throws Exception {
//        if (!this.existeElCasillero(x, y, z)) {
//            throw new Exception("Coordenadas de casillero vecino invalidas");
//        }
        if ((casillero1 == null) ||
                (casillero2 == null)) {
            throw new Exception("El casillero no puede ser nulo");
        }

        casillero2.setCasilleroVecino(casillero1, x, y, z);
        casillero1.setCasilleroVecino(casillero2, Casillero.invertirCoordenadaDeVecino(x),
                Casillero.invertirCoordenadaDeVecino(y),
                Casillero.invertirCoordenadaDeVecino(z));
    }

    /**
     * pre:
     *
     * @param x     no puede ser menor a 1 ni mayor al ancho del tablero
     * @param y     no puede ser menor a 1 ni mayor al alto del tablero
     * @param z     no puede ser menor a 1 ni mayor a la profundidad del tablero
     * @param ficha no puede ser nula
     * @throws Exception si no existe el casillero o si la ficha es nula
     */
    public void agregar(int x, int y, int z, T ficha) throws Exception {
        if (!this.existeElCasillero(x, y, z)) {
            throw new Exception("Coordenadas de casillero invalidas");
        }
        if (ficha == null) {
            throw new Exception("La ficha no puede ser nula");
        }

        Casillero<T> casillero = this.getCasillero(x, y, z);
        casillero.setDato(ficha);
    }

    /**
     * pre:
     *
     * @param x no puede ser menor a 1 ni mayor al ancho del tablero
     * @param y no puede ser menor a 1 ni mayor al alto del tablero
     * @param z no puede ser menor a 1 ni mayor a la profundidad del tablero
     * @return el casillero en las coordenadas pasadas por parametro
     * @throws Exception si alguna de las coordenadas es invalida
     */
    public Casillero<T> getCasillero(int x, int y, int z) throws Exception {
        if (!this.existeElCasillero(x, y, z)) {
            throw new Exception("Coordenadas de casillero invalidas");
        }
        return this.tablero.obtener(x).obtener(y).obtener(z);
    }

    /**
     * pre:
     *
     * @param x no puede ser menor a 1 ni mayor al ancho del tablero
     * @param y no puede ser menor a 1 ni mayor al alto del tablero
     * @param z no puede ser menor a 1 ni mayor a la profundidad del tablero
     * @return el dato del casillero en las coordenadas pasadas por parametro
     * @throws Exception si alguna de las coordenadas es invalida
     */
    public T obtener(int x, int y, int z) throws Exception {
        if (!this.existeElCasillero(x, y, z)) {
            throw new Exception("Coordenadas de casillero invalidas");
        }
        return this.getCasillero(x, y, z).getDato();
    }

    /**
     * pre:
     *
     * @param x no puede ser menor a 1 ni mayor al ancho del tablero
     * @param y no puede ser menor a 1 ni mayor al alto del tablero
     * @param z no puede ser menor a 1 ni mayor a la profundidad del tablero
     * @return verdadero si existe el casillero vecino, falso si no existe
     * @throws Exception si alguna de las coordenadas es invalida
     */
    public boolean existeElCasillero(int x, int y, int z) throws Exception {
        if ((x < 1) ||
                (y < 1) ||
                (z < 1)) {
            return false;
        }
        return (x <= this.tablero.getLongitud()) &&
                (y <= this.tablero.obtener(x).getLongitud()) &&
                (z <= this.tablero.obtener(x).obtener(y).getLongitud());
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     *
     * @return el ancho del tablero
     */
    public int getAncho() {
        return this.ancho;
    }

    /**
     * pre: --
     *
     * @return el alto del tablero
     */
    public int getAlto() {
        return this.alto;
    }

    /**
     * pre: --
     *
     * @return la profundidad del tablero
     */
    public int getProfundidad() {
        return this.profundidad;
    }

//SETTERS SIMPLES -----------------------------------------------------------------------------------------	
}