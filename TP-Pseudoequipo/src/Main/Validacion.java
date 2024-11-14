package Main;

public class Validacion {
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
     * @param casillero
     * @throws Exception
     * post: valida si la ficha esta bloqueada, sino, se puede utilizar normalmente
     */
    public static void validarFichaBloqueada(Casillero<Ficha> casillero) throws Exception {
    	Ficha ficha = casillero.getDato();
        if (ficha.isEstaBloqueada() == true) {
        	throw new Exception("La ficha "+ ficha +" esta bloqueada");
        }
    }
}