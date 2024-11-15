package Main;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.ObjectRowListProcessor;

import Estructuras.Lista;
import Estructuras.Vector;

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
	 * @return
	 * @throws Exception
	 * post: devuelve una lista con todos los enums
	 */
	public static Lista<Color> listaColores() throws Exception{
		Lista<Color> coloresLista = new Lista<Color>();
		for (Color color : Color.values()) {
            coloresLista.agregar(color);;
        }
		return coloresLista;
	}
	
	/**
	 * pre: --
	 * @param color
	 * @return devuelve la ruta de la imagen según el enum
	 * @throws Exception
	 */
	public static String getRutaDeImagen(Color color) throws Exception {
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
	 * 
	 * @param numero
	 * @return
	 * @throws Exception
	 */
	public static Color getColorJugador(int numero) throws Exception {
		if((numero < 1) || (numero > 7)) {
			throw new Exception("El numero debe estar entre 1 y 7");
		}
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
    		throw new Exception("El color no se encuentra disponible");
    	}
    	return colorJugador;
	}
}
