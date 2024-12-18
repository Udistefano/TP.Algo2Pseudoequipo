package Main;

public enum Color {
    ROJO,
    AZUL,
    BLANCO,
    CELESTE,
    NARANJA, 
    ROSA,
    VERDE;
	
	/**
	 * pre:
	 * @param color no puede ser nulo
	 * @return devuelve la ruta de la imagen según el enum
	 * @throws Exception si color es nulo
	 */
	public static String getRutaDeImagen(Color color) throws Exception {
		ValidacionesUtiles.validarSiEsNulo(color, "Color");
		String imagen = null;
		switch(color) {
		case ROJO:
			imagen = "colorRojo.bmp";
			break;
		case AZUL:
			imagen = "colorAzul.bmp";
    		break;
    	case BLANCO:
    		imagen = "colorBlanco.bmp";
    		return imagen;
    	case CELESTE:
    		imagen = "colorCeleste.bmp";
    		return imagen;
    	case NARANJA:
    		imagen = "colorNaranja.bmp";
    		return imagen;
    	case ROSA:
    		imagen = "colorRosa.bmp";
    		return imagen;
    	case VERDE:
    		imagen = "colorVerde.bmp";
    		return imagen;
    	default:
    		throw new Exception("El color no se encuentra disponible");
    	}
    	return imagen;
    }
	
	
	/**
	 * pre:
	 * @param numero debe estar entre 1 y 7
	 * @return el color que corresponde al numero
	 * @throws Exception si el numero no esta entre 1 y 7
	 */
	public static Color getColorJugador(int numero) throws Exception {
		Color colorJugador = null;
		switch(numero) {
		case 1:
			colorJugador = ROJO;
			break;
		case 2:
			colorJugador = AZUL;
    		break;
    	case 3:
    		colorJugador = BLANCO;
    		break;
    	case 4:
    		colorJugador = CELESTE;
    		break;
    	case 5:
    		colorJugador = NARANJA;
    		break;
    	case 6:
    		colorJugador = ROSA;
    		break;
    	case 7:
    		colorJugador = VERDE;
    		break;
    	default:
    		throw new Exception("El numero debe estar entre 1 y 7, el color no se encuentra disponible");
    	}
    	return colorJugador;
	}
}
