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
        ValidacionesUtiles.validarSiEsUnaCadenaVacia(nombre, "Nombre");
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
    
    /**
	 * pre:
	 * @param numero debe estar entre 1 y 6
	 * @return el movimiento de la ficha
	 * @throws Exception si el numero no esta entre 1 y 6
	 */
	public static Movimiento getMovimientoFicha(int numero) throws Exception {
		Movimiento movimientoFicha = null;
		switch(numero) {
		case 1:
			movimientoFicha = ARRIBA;
			break;
		case 2:
			movimientoFicha = ABAJO;
    		break;
    	case 3:
    		movimientoFicha = IZQUIERDA;
    		break;
    	case 4:
    		movimientoFicha = DERECHA;
    		break;
    	case 5:
    		movimientoFicha = ADELANTE;
    		break;
    	case 6:
    		movimientoFicha = ATRAS;
    		break;
    	default:
    		throw new Exception("El numero debe estar entre 1 y 7, el color no se encuentra disponible");
    	}
    	return movimientoFicha;
	}
}
