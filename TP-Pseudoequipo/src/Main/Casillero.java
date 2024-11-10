package Main;

public class Casillero<T> {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    public static int CANTIDAD_DE_VECINOS = 3; // Esto no se valido todavia
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    private int x = 0;
    private int y = 0;
    private int z = 0;
    private T dato = null;
    private Casillero<T>[][][] vecinos = null;

//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     *
     * @param x debe ser mayor o igual a 1
     * @param y debe ser mayor o igual a 1
     * @param z debe ser mayor o igual a 1
     * @throws Exception si alguno de los parametros es menor a 1
     *                   <p>post: crea un casillero con las coordenadas pasadas por parametros y sus vecinos
     */
    @SuppressWarnings("unchecked")
    public Casillero(int x, int y, int z) throws Exception {
        validarCoordenadas(x, y, z);

        this.x = x;
        this.y = y;
        this.z = z;
        this.vecinos = new Casillero[CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS][CANTIDAD_DE_VECINOS];
        for (int i = 0; i < this.vecinos.length; i++) {
            for (int j = 0; j < this.vecinos.length; j++) {
                for (int k = 0; k < this.vecinos.length; k++) {
                    this.vecinos[i][j][k] = null;
                }
            }
        }
        this.vecinos[1][1][1] = this; // Definir el casillero actual como vecino de sí mismo
    }

//METODOS DE CLASE ----------------------------------------------------------------------------------------

    /**
     * pre:
     *
     * @param x debe ser mayor o igual a 1
     * @param y debe ser mayor o igual a 1
     * @param z debe ser mayor o igual a 1
     * @throws Exception si alguna de las coordenadas es menor a 1
     *                   <p>post: valida que las coordenadas sean mayores o igual a 1
     */
    public static void validarCoordenadas(int x, int y, int z) throws Exception {
        validarCoordenada(x);
        validarCoordenada(y);
        validarCoordenada(z);
    }

    /**
     * pre:
     *
     * @param coordenada debe ser mayor o igual a 1
     * @throws Exception si la coordenada es menor a 1
     *                   <p>post: Se valida que las coordenada sea mayor o igual a 1
     */
    public static void validarCoordenada(int coordenada) throws Exception {
        if (coordenada < 1) {
            throw new Exception("La coordenada " + coordenada + " debe ser mayor a 1");
        }
    }

    /**
     * pre:
     *
     * @param x debe ser una de las siguientes: -1, 0, 1
     * @param y debe ser una de las siguientes: -1, 0, 1
     * @param z debe ser una de las siguientes: -1, 0, 1
     * @throws Exception si alguna de las coordenadas es invalida
     *                   <p>post: valida que (x, y, z) sean coordenadas validas, si alguna es invalida tira Exception
     */
    public static void validarCoordenadasDeVecino(int x, int y, int z) throws Exception {
        validarCoordenadaDeVecino(x);
        validarCoordenadaDeVecino(y);
        validarCoordenadaDeVecino(z);
    }

    /**
     * pre:
     *
     * @param coordenada debe ser una de las siguientes: -1, 0, 1
     * @throws Exception si es una coordenada invalida
     *                   <p>post: valida que sea una coordenada valida, si es invalida tira Exception
     */
    public static void validarCoordenadaDeVecino(int coordenada) throws Exception {
        if ((coordenada < -1)
                || (coordenada > 1)) {
            throw new Exception("La coordenada " + coordenada + " es invalida para utilizarla con los casilleros" +
                    "vecinos, debe ser una de las siguientes -1, 0, 1");
        }
    }

    public static int invertirCoordenadaDeVecino(int i) {
        return -i;
    }

//METODOS GENERALES ---------------------------------------------------------------------------------------

    /**
     * pre: -
     *
     * @return una cadena mostrando las coordenadas del casillero
     */
    @Override
    public String toString() {
        return "Casillero (" + x + ", " + y + ", " + z + ")";
    }

//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: --
     * <p>post: elimina el dato del casillero
     */
    public void vaciar() {
        setDato(null);
    }

    /**
     * pre: --
     *
     * @return verdadero si hay un dato en casillero, falso si el casillero esta vacio
     */
    public boolean estaOcupado() {
        return dato != null;
    }

    /**
     * pre:
     *
     * @param dato no puede ser nulo
     * @return verdadero si el dato del casillero equivale al dato pasado por parametro, falso no si equivale
     * @throws Exception si el dato es nulo
     */
    public boolean tiene(T dato) throws Exception {
        Validacion.validarSiEsNulo(dato, "Dato");
        if (this.dato == null) {
            return false;
        }
        return this.dato.equals(dato);
    }

    /**
     * pre:
     *
     * @param movimiento un movimiento en 3D, no puede ser nulo
     * @return verdadero si existe el casillero vecino que este en la direccion 'movimiento', falso si no existe
     * @throws Exception si no es un movimiento valido o si es nulo
     */
    public boolean existeElVecino(Movimiento movimiento) throws Exception {
        switch (movimiento) {
            case ABAJO:
                return vecinos[2][1][1] != null;
            case ARRIBA:
                return vecinos[0][1][1] != null;
            case DERECHA:
                return vecinos[1][2][1] != null;
            case IZQUIERDA:
                return vecinos[1][0][1] != null;
            case ADELANTE:
                return vecinos[1][1][0] != null;
            case ATRAS:
                return vecinos[1][1][2] != null;
            default:
                throw new Exception("No se encontro el casillero vecino para chequear su existencia");
        }
    }

//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: -
     *
     * @return la coordenada x
     */
    public int getX() {
        return x;
    }

    /**
     * pre: -
     *
     * @return la coordenada y
     */
    public int getY() {
        return y;
    }

    /**
     * pre: -
     *
     * @return la coordenada z
     */
    public int getZ() {
        return z;
    }

    /**
     * pre: -
     *
     * @return el dato de tipo T
     */
    public T getDato() {
        return dato;
    }

    /**
     * pre:
     *
     * @param dato <p>post: setea el dato del casillero al dato pasado por parametro
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * pre:
     *
     * @param movimiento un movimiento en 3D, no puede ser nulo
     * @return el casillero vecino que este en la movimiento pasada por parametro
     * @throws Exception si no es un movimiento valido o si es nulo
     */
    public Casillero<T> getCasilleroVecino(Movimiento movimiento) throws Exception {
        return getCasilleroVecino(Direccion.valueOf(movimiento.toString()));
    }

    // TODO: crear enum Plano, que pueda ser ADELANTE, ATRAS, CENTRO, asi no escribimos tanto codigo repetido
    /**
     * pre:
     *
     * @param direccion un direccion en 3D, no puede ser nulo
     * @return el casillero vecino que este en la direccion pasada por parametro
     * @throws Exception si no es un direccion valido o si es nulo
     */
    public Casillero<T> getCasilleroVecino(Direccion direccion) throws Exception {
        switch (direccion) {
            case ATRAS_ABAJO:
                return vecinos[2][1][0];
            case ATRAS_ARRIBA:
                return vecinos[0][1][0];
            case ATRAS_DERECHA:
                return vecinos[1][2][0];
            case ATRAS_IZQUIERDA:
                return vecinos[1][0][0];
            case ATRAS_DERECHA_ARRIBA:
                return vecinos[0][2][0];
            case ATRAS_DERECHA_ABAJO:
                return vecinos[2][2][0];
            case ATRAS_IZQUIERDA_ARRIBA:
                return vecinos[0][0][0];
            case ATRAS_IZQUIERDA_ABAJO:
                return vecinos[2][0][0];
            case CENTRO_ABAJO:
                return vecinos[2][1][1];
            case CENTRO_ARRIBA:
                return vecinos[0][1][1];
            case CENTRO_DERECHA:
                return vecinos[1][2][1];
            case CENTRO_IZQUIERDA:
                return vecinos[1][0][1];
            case CENTRO_DERECHA_ARRIBA:
                return vecinos[0][2][1];
            case CENTRO_DERECHA_ABAJO:
                return vecinos[2][2][1];
            case CENTRO_IZQUIERDA_ARRIBA:
                return vecinos[0][0][1];
            case CENTRO_IZQUIERDA_ABAJO:
                return vecinos[2][0][1];
            case ADELANTE_ABAJO:
                return vecinos[2][1][2];
            case ADELANTE_ARRIBA:
                return vecinos[0][1][2];
            case ADELANTE_DERECHA:
                return vecinos[1][2][2];
            case ADELANTE_IZQUIERDA:
                return vecinos[1][0][2];
            case ADELANTE_DERECHA_ARRIBA:
                return vecinos[0][2][2];
            case ADELANTE_DERECHA_ABAJO:
                return vecinos[2][2][2];
            case ADELANTE_IZQUIERDA_ARRIBA:
                return vecinos[0][0][2];
            case ADELANTE_IZQUIERDA_ABAJO:
                return vecinos[2][0][2];
            default:
                throw new Exception("No se encontro el casillero vecino");
        }
    }

//SETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * pre: --
     *
     * @return Devuelve una matriz 3D con los vecinos, y el casillero actual en el centro
     */
    @SuppressWarnings("unchecked")
    public Casillero<T>[][][] getCasillerosVecinos() {
        Casillero<T>[][][] matriz = new Casillero[3][3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.arraycopy(vecinos[i][j], 0, matriz[i][j], 0, 3);
            }
        }

        return matriz;
    }

    /**
     * pre:
     *
     * @param casillero: el casillero vecino
     * @param x:         -1, 0 o 1 para indicar izquierda, centro o derecha respectivamente
     * @param y:         -1, 0 o 1 para indicar arriba, centro o abajo respectivamente
     * @param z:         -1, 0 o 1 para indicar delante, centro o detrás respectivamente
     */
    public void setCasilleroVecino(Casillero<T> casillero, int x, int y, int z) {
        vecinos[x + 1][y + 1][z + 1] = casillero;
    }
}
