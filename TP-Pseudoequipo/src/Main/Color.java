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
	 * pre: --
	 * @param color
	 * @return devuelve la ruta de la imagen según el enum
	 * @throws Exception
	 */
	public String getImagen(Color color) throws Exception {
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
}
