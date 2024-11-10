package Main;

// Para indicar los movimientos de la ficha (por ejemplo para mover una ficha)
// No se puede mover en diagonal la ficha (CREO?¿)
public enum Movimiento {
    ARRIBA,
    ABAJO,
    IZQUIERDA,
    DERECHA,
    ADELANTE,
    ATRAS;

    /**
     * pre:
     * @param nombre no puede ser nulo
     * @return verdadero si existe un movimiento con el nombre pasado por parametro
     * @throws Exception si nombre es nulo
     */
    public static boolean existe(String nombre) throws Exception {
        if (nombre == null) {
            throw new Exception("El movimiento no puede ser nulo");
        }
        try {
            Movimiento.obtener(nombre);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * pre:
     * @param nombre no puede ser nulo
     * @return el movimiento con el nombre pasado por parametro
     * @throws Exception si el nombre es nulo, o si no existe ningun movimiento con ese nombre
     */
    public static Movimiento obtener(String nombre) throws Exception {
        return Movimiento.valueOf(nombre.toUpperCase());
    }
}
