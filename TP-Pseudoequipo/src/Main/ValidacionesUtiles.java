package Main;

public class ValidacionesUtiles {
    /**
     * pre:
     * @param objeto no debe ser nulo
     * @param nombre no debe ser vacio
     * post: valida que el objeto con nombre pasado por parametro no sea nulo
     */
    public static void validarSiEsNulo(Object objeto, String nombre) throws Exception {
        if (nombre == null) {
            throw new Exception("El nombre del objeto a validar no puede ser nulo");
        }
        if (objeto == null) {
            throw new Exception("El objeto " + nombre + " no puede ser nulo");
        }
    }

    /**
     * pre:
     * @param cadena no debe ser nulo ni vacio
     * post: valida que cadena no sea nulo ni vacio
     */
    public static void validarSiEsUnaCadenaVacia(String cadena, String nombre) throws Exception {
        if ((cadena == null)
                || (cadena.isEmpty())) {
            throw new Exception("La cadena " + nombre + " no puede ser vacia");
        }
    }

    /**
     * pre:
     * @param caracter no debe estar vacio
     * post: valida que el caracter no este vacio
     */
    public static void validarSiEsCaracterVacio(char caracter) throws Exception {
        if (caracter == ' ') {
            throw new Exception("El caracter no puede ser vacio");
        }
    }

    /**
     * pre:
     * @param caracter debe ser una letra del abecedario
     * post: valida que el caracter sea una letra del abecedario
     */
    public static void validarSiEsUnaLetra(char caracter) throws Exception {
        int valorDecimal = Character.toLowerCase(caracter);
        // 97 = a, 122 = z, 164 = ñ
        if ((valorDecimal < 97) ||
                (valorDecimal > 122 && valorDecimal != 164)) {
            throw new Exception("El caracter no es un letra");
        }
    }
    
    /**
     * pre: debe existir la ficha en casillero
     * @param ficha no debe ser nulo
     * post: valida si la ficha esta bloqueada, sino, se puede utilizar normalmente
     */
    public static void validarSiFichaEstaBloqueada(Ficha ficha) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(ficha, "Ficha");
        if (ficha.estaBloqueada()) {
        	throw new Exception("\nNo se puede realizar la jugada, la ficha " + ficha + " esta bloqueada");
        }
    }

    /**
     * pre: numero
     * post: valida que numero no sea menor a 1
     */
    public static void validarSiNumeroEsMenorAUno(int numero, String nombre) throws Exception {
        if (numero < 1) {
            throw new Exception("El numero " + nombre + " no puede ser menor a uno");
        }
    }
    public static void validarSiEsUnico(int color, lista jugadores) {
        if (!jugadores.estaVacia()) {
    		jugadores.iniciarCursor();
    		while(jugadores.avanzarCursor()) {
    			if (jugadores.obtenerCursor().getColor == color) {
    				throw new Exception ("El color " + color + "ya esta asociado a otro jugador")
    			}
    		}
    	}
    }
}
