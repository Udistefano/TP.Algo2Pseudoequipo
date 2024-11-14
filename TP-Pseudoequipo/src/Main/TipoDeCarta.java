package Main;

import Cartas.*;

import java.util.Random;

public enum TipoDeCarta {
	ANULAR_CASILLERO,
	BLOQUEAR_FICHA,
	CAMBIAR_COLOR_FICHA,
	DOBLE_TURNO,
	ELIMINAR_CARTAS_DEL_JUGADOR,
	PERDER_TURNO,
	VOLVER_JUGADA_ANTERIOR;

	private static Random random = new Random();
	private static int CANTIDAD_DE_CARTAS = 7;

	// TODO: quiza hacer una clase de Utiles, y ahi generar numeros aleatorios?

	/**
	 * pre: --
	 * @return un numero aleatorio entre 1 y CANTIDAD_DE_CARTAS
	 */
	private static int generarNumeroAleatorio() {
		// TODO: verificar si generarNumeroAleatorio contempla todas las cartas
		return random.nextInt(1, CANTIDAD_DE_CARTAS + 1);
	}

	/**
	 * pre: --
	 * @return un tipo de carta aleatorio
	 */
	public static TipoDeCarta generarTipoDeCartaAleatorio() throws Exception {
		TipoDeCarta tipoDeCarta = null;
		switch(generarNumeroAleatorio()) {
			case 1:
				tipoDeCarta = CAMBIAR_COLOR_FICHA;
				break;
			case 2:
				tipoDeCarta = ANULAR_CASILLERO;
				break;
			case 3:
				tipoDeCarta = BLOQUEAR_FICHA;
				break;
			case 4:
				tipoDeCarta = DOBLE_TURNO;
				break;
			case 5:
				tipoDeCarta = ELIMINAR_CARTAS_DEL_JUGADOR;
				break;
			case 6:
				tipoDeCarta = PERDER_TURNO;
				break;
			case 7:
				tipoDeCarta = VOLVER_JUGADA_ANTERIOR;
				break;
			default:
				throw new Exception("El tipo de carta no esta creada todavia!");
		}
		return tipoDeCarta;
	}
}

