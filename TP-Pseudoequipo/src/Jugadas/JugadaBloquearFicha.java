package Jugadas;

import Cartas.Carta;
import Main.Casillero;
import Main.Ficha;
import Main.Partida;
import Main.Teclado;
import Main.Turno;
import Main.UtilesVarios;
import Main.ValidacionesUtiles;

public class JugadaBloquearFicha extends Jugada {
	 //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * pre:
     * @param carta no puede ser nulo
     * @throws Exception si carta es nulo
     *                   post: inicializa la jugada anular casillero, con la carta pasada por parametro
     */
    public JugadaBloquearFicha(Carta carta) throws Exception {
        super(carta);
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * pre: El casillero debe estar en el tablero
     * @param partida no puede ser nulo
     * @param turnoActual no puede ser nulo
     * @throws Exception si alguno de los parametros es nulo, o si la ficha en el casillero a jugar esta bloqueada
     * post: anula el casillero seleccionado
     */
    @Override
    public void jugar(Partida partida, Turno turnoActual) throws Exception {
        ValidacionesUtiles.validarSiEsNulo(partida, "Partida");
        ValidacionesUtiles.validarSiEsNulo(turnoActual, "Turno");

		try {
			ValidacionesUtiles.validarSiHayFichasHabilitadasEnElTablero(partida.getTablero(), turnoActual.getJugador());	
		} catch (Exception e) {
			UtilesVarios.mostrarError(e);
			return;
		}
        
        boolean esCasilleroInvalido = true;
        do {
            try {
                System.out.println("\nIngrese las coordenadas del casillero del cual se bloqueara a la ficha en el:");
                Casillero<Ficha> casillero = Teclado.preguntarCasillero(partida.getTablero());
                ValidacionesUtiles.validarSiCasilleroEstaOcupado(casillero, partida.getTablero());
                ValidacionesUtiles.validarSiFichaEstaBloqueada(casillero.getDato());
        
                casillero.getDato().bloquear();
                System.out.println("\nSe ha bloqueado la ficha en el " + casillero);
                esCasilleroInvalido = false;
            } catch (Exception e) {
                UtilesVarios.mostrarError(e);
            }
        } while (esCasilleroInvalido);
    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}

